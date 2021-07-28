package com.tchuhu.common.core.util.tree;

import java.lang.annotation.*;

/**
 * <b> TreeFieldPrefix </b>
 * <p>
 * 功能描述:树节点属性前缀
 * </p>
 *
 * @author tchuhu
 * @date 2019/9/7
 * @time 10:48
 * @path com.shiyou.common.core.util.tree.TreeFieldPrefix
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface TreeFieldPrefix {
	 String value();

	 String name() default "" ;
}
