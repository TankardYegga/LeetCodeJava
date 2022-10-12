package java_basic;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/6/2022
 * Time: 5:00 PM
 * Email: levinforward@163.com
 */
public interface MyInterface{

    public static final String name = "";
    String age = "";

    public abstract void hello();
    public abstract void good();
    int writeCode();
}


interface MyInterface2 extends MyInterface{

}

class MyClass implements MyInterface{

    @Override
    public void hello() {
        System.out.println("hello");
    }

    @Override
    public void good() {
        System.out.println("good");
    }

    @Override
    public int writeCode() {
        return 0;
    }
}