package cn.gdsdxy.myrestaurant.config;


import cn.gdsdxy.myrestaurant.util.FwConstants;
import cn.gdsdxy.myrestaurant.util.FwResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常c处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理参数校验异常
     * */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public FwResult<Object> handleParameterInvalidException(HttpServletRequest req, HttpServletResponse res, BindException  e) {
        String message = e.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("；"));
        log.error("Exception:",e);
        return FwResult.failed(FwConstants.FAIL,message);
    }

    /**
     * 处理其他异常
     * @param request
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public FwResult<Object> handler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        log.error("Exception:",e);
        return FwResult.failed(FwConstants.FAIL,e.getMessage());
    }

}
