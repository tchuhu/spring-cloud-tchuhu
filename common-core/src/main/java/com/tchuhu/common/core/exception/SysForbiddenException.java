package com.tchuhu.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * <b> ShiYouForbiddenException </b>
 * <p>
 * 功能描述:401 访问未授权
 * </p>
 *
 * @author MARK
 * @date 2019/9/10
 * @time 16:22
 * @path com.shiyou.common.core.exception.ShiYouForbiddenException
 */
@NoArgsConstructor
public class SysForbiddenException extends RuntimeException{
	private static final Long serialVersionUID = 1L;

	public SysForbiddenException(String message) {
		super(message);
	}

	public SysForbiddenException(Throwable cause) {
		super(cause);
	}

	public SysForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public SysForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
