package java_basic.webserver;

import java.lang.reflect.InvocationTargetException;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/18/2022
 * Time: 7:40 PM
 * Email: levinforward@163.com
 */
public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Class c1 = new RandomClass().getClass();
        Class c2 = RandomClass.class;
        Class c3 = Class.forName("java_basic.webserver.RandomClass");

        RandomClass obj = (RandomClass) c1.newInstance(); //java9中已经过时

        RandomClass obj2 = (RandomClass) c1.getConstructor().newInstance();
        System.out.println(obj2);
    }
}

class RandomClass{

    // java9 要求必须有显示的空构造方法
    public RandomClass(){

    }
}
