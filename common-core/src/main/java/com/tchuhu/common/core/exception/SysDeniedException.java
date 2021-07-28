package com.tchuhu.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * @author mark
 * @date 2018年06月22日16:22:03
 * 403 授权拒绝
 */
@NoArgsConstructor
public class SysDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SysDeniedException(String message) {
		super(message);
	}

	public SysDeniedException(Throwable cause) {
		super(cause);
	}

	public SysDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SysDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
