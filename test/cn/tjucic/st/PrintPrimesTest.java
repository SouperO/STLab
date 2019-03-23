package cn.tjucic.st;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import org.junit.Test;

public class PrintPrimesTest {
    private PrintPrimes p;
    ByteArrayOutputStream bytes = null;//缓存从控制台重定向的字符流
    
    @Test
    public void testResult() throws Exception {
    	p = new PrintPrimes();//初始化
        bytes = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bytes));//将输出到控制台C的字符流重定向到bytes
        String str = new String("Prime: 2" + '\r'+'\n');//构造测试用例生成的字符串
        str += "Prime: 3" + '\r'+'\n';
        str += "Prime: 5" + '\r'+'\n';
        str += "Prime: 7" + '\r'+'\n';
        str += "Prime: 11" + '\r'+'\n';
        
        Class<? extends PrintPrimes> p2 = p.getClass();//通过反射调用私有方法
        Method method = p2.getDeclaredMethod("printPrimes", new Class[]{int.class});
        method.setAccessible(true);
        method.invoke(p, 5);
        assertEquals(str, bytes.toString());
    }
}