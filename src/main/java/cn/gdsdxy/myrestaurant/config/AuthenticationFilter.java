package cn.gdsdxy.myrestaurant.config;

import cn.gdsdxy.myrestaurant.service.TokenService;
import cn.gdsdxy.myrestaurant.util.FwResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 认证过滤器，用于验证token是否有效
 */
@Configuration
public class AuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("当前的访问路径是：" + request.getRequestURI());
        /* 根据项目的实际需求设置白名单URL，即白名单中的URL不需要判断token是否正确，其它都要判断token是否正确*/
        List<String> WHITE_LIST = new ArrayList<String>() {{
            add("/swagger-ui");
            add("/api-docs");
            add("/doc");
            add("/images");
            add("/res/"); // 静态资源路径
            add("/register"); // 后台账号（注册）
            add("/login"); // 前台账号（登录）
        }};
        //是否开通鉴权
        boolean isOpen = false;
        if(!isOpen){
            filterChain.doFilter(request, response);
            return;
        }
        for (String url : WHITE_LIST) {
            if (request.getRequestURI().indexOf(url) >= 0) {
                filterChain.doFilter(request, response);    //继续执行原有的请求
                return;
            }
        }

        /* 获取header中的token信息 */
        String token = request.getHeader("Authorization");
        response.setCharacterEncoding("UTF-8");
        //如果不包含token，则显示“token格式出错”，直接返回
        if (StringUtils.isEmpty(token)) {
            response.setContentType("application/json;charset=utf-8;");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(FwResult.failedMsg("token格式出错"));
            response.getWriter().write(result);
            return;
        }

        //如果验证token失败则显示“token验证失败”，直接返回
        if (!tokenService.isExistToken(token)) {
            response.setContentType("application/json;charset=utf-8;");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(FwResult.failedMsg("token验证失败"));
            response.getWriter().write(result);
            return;
        }

        filterChain.doFilter(request, response);
    }
}

