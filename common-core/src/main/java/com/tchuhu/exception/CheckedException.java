package com.tchuhu.exception;

import lombok.NoArgsConstructor;

/**
 * @ClassName CheckedException
 * @Description Hello World!
 * @Author tchuhu
 * @Date 2021/7/26 18:36
 * @Version 1.0
 */
@NoArgsConstructor
public class CheckedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
