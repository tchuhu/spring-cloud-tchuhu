package com.tchuhu.annotation;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tchuhu.config.SensitiveInfoSerialize;
import com.tchuhu.constant.enums.SensitiveType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据脱敏
 * @author tchuhu
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = SensitiveInfoSerialize.class)
public @interface SensitiveInfo {

    /**
     * 脱敏类型
     * @return 手机号/身份证号/邮箱/银行卡号
     */
    SensitiveType value();
}
