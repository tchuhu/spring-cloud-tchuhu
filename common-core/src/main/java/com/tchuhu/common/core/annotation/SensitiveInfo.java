package com.tchuhu.common.core.annotation;



import com.tchuhu.common.core.config.SensitiveInfoSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tchuhu.common.core.constant.enums.SensitiveType;
import org.springframework.boot.jackson.JsonComponent;

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
@JsonComponent
@JsonSerialize(using = SensitiveInfoSerialize.class)
public @interface SensitiveInfo {

    /**
     * 脱敏类型
     * @return 手机号/身份证号/邮箱/银行卡号
     */
    SensitiveType value();
}
