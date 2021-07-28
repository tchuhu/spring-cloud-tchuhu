package com.tchuhu.common.core.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName DateTimeDeserializer
 * @Description Hello World!
 * @Author tchuhu
 * @Date 2021/7/28 17:36
 * @Version 1.0
 */
public class DateTimeDeserializer extends LocalDateTimeDeserializer {
    private LocalDateTimeDeserializer[] deserializers;

    public DateTimeDeserializer(DateTimeFormatter formatter) {
        super(formatter);
    }

    public DateTimeDeserializer(DateTimeFormatter ...formatter) {
        this(formatter[0]);
        deserializers = new LocalDateTimeDeserializer[formatter.length];
        for (int i = 0; i < formatter.length; i++) {
            deserializers[i] = new LocalDateTimeDeserializer(formatter[i]);
        }
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        IOException exp = null;
        for (LocalDateTimeDeserializer deserializer : deserializers) {
            try {
                return deserializer.deserialize(parser, context);
            } catch (IOException e) {
                exp = e;
            }

        }
        throw exp;
    }
}
