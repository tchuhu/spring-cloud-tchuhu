package com.tchuhu.common.core.exception;

/**
 * <b> BusinessException </b>
 * <p>
 * 功能描述:业务异常
 * </p>
 *
 * @author tchuhu
 * @date 2019/9/29
 * @time 23:05
 * @path com.shiyou.common.core.exception.BusinessException
 */
public class BusinessException extends RuntimeException {


	public BusinessException(String msg) {
		super(msg);
	}
}
