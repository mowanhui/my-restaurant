package cn.gdsdxy.myrestaurant.aop;

import cn.gdsdxy.myrestaurant.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author mowanhui
 * @version 1.0
 * @date 2024/11/30 13:39
 */
//标记为切面的注解
@Aspect
//标记为bean的注解
@Component
public class CheckTokenAspect {
    @Autowired
    private TokenService tokenService;

    @Pointcut("execution(* cn.gdsdxy.myrestaurant..*Controller.*(..))")
    public void login() {
    }

    @Around(value = "login()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //获取请求头是否存在token
        String token=request.getHeader("X-TOKEN");
        if(token==null||"".equals(token)){
            throw new RuntimeException("TOKEN不存在");
        }
        return joinPoint.proceed();
    }
}
