package java_basic.webserver;

/**
 * Description:
 * Creator: levin
 * Date: 10/19/2022
 * Time: 4:37 PM
 * Email: levinforward@163.com
 */
public class Person {

    private String name;
    private int age;

    public Person(){

    }

    public Person(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
