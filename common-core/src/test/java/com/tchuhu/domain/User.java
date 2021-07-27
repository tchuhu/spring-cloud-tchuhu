package com.tchuhu.domain;

import com.tchuhu.annotation.SensitiveInfo;
import com.tchuhu.constant.enums.SensitiveType;
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
}
