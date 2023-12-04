package com.pacemrc.javasec.deserialize.jackson.vul;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

class Payload2{
    public Object payload;
}
public class vul2 {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        Payload2 payload = new Payload2();
        payload.payload = new File("/etc/passwd");
        // serialize
        String jsonPayload = objectMapper.writeValueAsString(payload);
        System.out.println(jsonPayload);
    }
}