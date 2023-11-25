package cn.atcat.exception;

import cn.atcat.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e) {
        log.info("全局异常处理：{}", e.getMessage());
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "系统异常");
    }
}
