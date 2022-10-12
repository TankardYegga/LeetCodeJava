package java_basic;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/6/2022
 * Time: 4:48 PM
 * Email: levinforward@163.com
 */
abstract public class AbstractDemo {

    abstract public void  shout();

    public int id;
    public String name;

    //抽象类里可以有非抽象的普通方法
    public void read(){
        System.out.println("good");
    }

    public AbstractDemo(String name, int id){
        this.id = id;
        this.name = name;
    }
}


class Cat extends AbstractDemo{

    public Cat(String name, int id) {
        super(name, id);
    }

    @Override
    public void shout() {
        System.out.println("Miao Miao");
    }
}