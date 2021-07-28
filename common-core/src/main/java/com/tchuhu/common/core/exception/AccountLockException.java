package com.tchuhu.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * <b> AccountLockException </b>
 * <p>
 * 功能描述:AccountLockException
 * </p>
 *
 * @author MARK
 * @date 2019/9/15
 * @time 21:38
 * @path com.shiyou.common.core.exception.AccountLockException
 */
@NoArgsConstructor
public class AccountLockException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccountLockException(String message) {
		super(message);
	}

	public AccountLockException(Throwable cause) {
		super(cause);
	}

	public AccountLockException(String message, Throwable cause) {
		super(message, cause);
	}
}
