package com.pacemrc.javasec.rcecho;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

public class EchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = req.getParameter("msg");
        resp.getWriter().print(msg);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String baseStr = req.getParameter("base64Str").replaceAll("-","+");
//        System.out.println();
//        byte[] bytes = Base64.getDecoder().decode(baseStr);
//        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//        ObjectInputStream ois = new ObjectInputStream(bis);
//        try {
//            ois.readObject();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        ois.close();
//        bis.close();
//    }
}
