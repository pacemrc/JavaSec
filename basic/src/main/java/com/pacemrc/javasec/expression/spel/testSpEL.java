package com.pacemrc.javasec.expression.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.IOException;

public class testSpEL {

    public static void test1(){

        //创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        //使用解析器解析字符串表达式
        Expression expression = parser.parseExpression("('hello' + ',world').concat(#end)");
        //创建上下文对象设置表达式中的参数值
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end","!");
        //通过上下文获取表达式的值
        System.out.println(expression.getValue(context));
    }

    public static void test2(){
        //创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        //使用解析器解析字符串表达式
        Class<String> result = parser.parseExpression("T(java.lang.String)").getValue(Class.class);
        System.out.println(result);
    }

    public static void test3() throws InstantiationException, IllegalAccessException, IOException {
        //创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        //使用解析器解析字符串表达式
        Class<Runtime> result = parser.parseExpression("T(java.lang.Runtime).getRuntime().exec(\"calc\")").getValue(Class.class);

    }
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        //演示spel把字符串表达式进行运行
//        test1();
        //演示spel通过字符串表达式获取任意类
//        test2();
        //演示spel通过字符串表达式获取任意类并调用其方法执行
        test3();
    }
}
