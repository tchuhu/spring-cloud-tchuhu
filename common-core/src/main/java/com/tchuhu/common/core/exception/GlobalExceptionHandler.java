package com.tchuhu.common.core.exception;


import com.tchuhu.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author mark
 * @date 2019/2/1
 * 全局的的异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return R.failed("服务器异常,请联系管理员！");
    }

    /**
     * validation Exception
     *
     * @param exception
     * @return R
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        log.error(fieldErrors.get(0).getDefaultMessage());
        return R.failed(fieldErrors.get(0).getDefaultMessage());
    }

    /**
     * 401
     *
     * @param e
     * @return R
     */
    @ExceptionHandler({SysForbiddenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R unAuthorizedExceptionHandler(SysForbiddenException e) {
        log.error("访问未授权 ex={}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    /**
     * 401
     *
     * @param e
     * @return R
     */
    @ExceptionHandler({SysDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R forbiddenExceptionHandler(SysDeniedException e) {
        log.error("授权失败 ex={}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    @ExceptionHandler({AccountLockException.class})
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public R accountLockExceptionHandler(SysDeniedException e) {
        log.error("账户锁定 ex={}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.OK)
    public R businessExceptionHandler(BusinessException e) {
        log.error("业务异常 ex={}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    @ExceptionHandler({DuplicateKeyException.class, SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    public R duplicateKeyExceptionHandler(Exception e) {
        String message = e.getMessage();
        log.error("业务异常 ex={}", message, e);
        if (message.contains("Duplicate entry")) {
            return R.failed("重复记录");
        }
        return R.failed(e.getMessage());
    }
}

