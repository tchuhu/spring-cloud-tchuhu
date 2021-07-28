package com.tchuhu.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tchuhu.common.core.annotation.SensitiveInfo;
import com.tchuhu.common.core.config.SensitiveInfoSerialize;
import com.tchuhu.common.core.constant.enums.SensitiveType;
import lombok.Data;

/**
 * @ClassName User
 * @Description Hello World!
 * @Author tchuhu
 * @Date 2021/7/27 18:29
 * @Version 1.0
 */
@Data
public class User {

    @SensitiveInfo(value = SensitiveType.MOBILE)
    private String phone;

    @JsonSerialize(using = SensitiveInfoSerialize.class)
    public String getPhone() {
        return phone;
    }
}
