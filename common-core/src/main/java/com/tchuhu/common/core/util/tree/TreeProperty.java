package com.tchuhu.common.core.util.tree;

import java.lang.annotation.*;

/**
 * <b> TreeProperty </b>
 * <p>
 * 功能描述:树节点字段属性注解服务
 * </p>
 *
 * @author tchuhu
 * @date 2018/3/30
 * @time 14:58
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface TreeProperty {
    TreeField value() default TreeField.ID;
}
