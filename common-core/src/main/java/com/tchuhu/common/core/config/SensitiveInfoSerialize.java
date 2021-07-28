package com.tchuhu.common.core.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.tchuhu.common.core.annotation.SensitiveInfo;
import com.tchuhu.common.core.constant.enums.SensitiveType;
import com.tchuhu.common.core.util.DesensitizationUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName SensitiveInfoSerialize
 * @Description Hello World!
 * @Author tchuhu
 * @Date 2021/7/26 16:31
 * @Version 1.0
 */
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveType type;

    public SensitiveInfoSerialize() {
    }

    public SensitiveInfoSerialize(SensitiveType type) {
        this.type = type;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        switch (this.type) {
            case MOBILE:
                jsonGenerator.writeString(DesensitizationUtil.desenCellphone(value));
                break;
            case EMAIL:
                jsonGenerator.writeString(DesensitizationUtil.desenEmail(value));
                break;
            case ID_CARD:
                jsonGenerator.writeString(DesensitizationUtil.desenIDCardNo(value));
                break;
            case BANK_CARD:
                jsonGenerator.writeString(DesensitizationUtil.desenBankCardNo(value));
                break;
            default:
                break;
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty property) throws JsonMappingException {
        // 为空直接跳过
        if (property != null) {
            // 非 String 类直接跳过
            if (Objects.equals(property.getType().getRawClass(), String.class)) {
                SensitiveInfo sensitiveInfo = property.getAnnotation(SensitiveInfo.class);
                if (sensitiveInfo == null) {
                    sensitiveInfo = property.getContextAnnotation(SensitiveInfo.class);
                }
                // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize
                if (sensitiveInfo != null) {
                    return new SensitiveInfoSerialize(sensitiveInfo.value());
                }
            }
            return serializerProvider.findValueSerializer(property.getType(), property);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
