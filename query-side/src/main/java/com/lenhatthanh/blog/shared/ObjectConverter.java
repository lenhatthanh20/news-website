package com.lenhatthanh.blog.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class ObjectConverter {
    private ObjectMapper objectMapper;

    public <T> T convertArrayByteToObject(byte[] content, Class<T> valueType) throws IOException {
        return this.objectMapper.reader().readValue(content, valueType);
    }

    public byte[] convertObjectToArrayByte(Object object) throws IOException {
        return this.objectMapper.writeValueAsBytes(object);
    }
}
