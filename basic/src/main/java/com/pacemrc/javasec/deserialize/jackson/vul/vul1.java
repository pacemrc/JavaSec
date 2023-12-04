package com.pacemrc.javasec.deserialize.jackson.vul;

/**
 * 演示jackson在反序列化过程中调用存在危险方法的setXX方法
 * (不可能存在的类)
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class Payload{
    public String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) throws IOException {
        Runtime.getRuntime().exec("calc");
        this.payload = payload;
    }
}
public class vul1 {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(new Payload());

        objectMapper.readValue(string,Payload.class);

    }
}
