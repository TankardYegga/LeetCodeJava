/**
 * Description: JavaProjects
 * Creator: levin
 * Date: 2/2/2023
 * Time: 8:36 PM
 * Email: levinforward@163.com
 */
public class EagerInitialization {

    //在JAVA中实现单例模式的几种方式

    //1. eager initialization
    // 渴望初始化：这个类的实例的创建 在它实际被需要之前 就早早地被创建了
    // 缺点也同样因此：因为无论这个实例是否在运行时被需要，其都会被创建；
    // 如果这个实例对象过大的话，那么在非运行时的长期存在将会影响运行效率
    private static volatile EagerInitialization instance = new EagerInitialization();

    //私有的构造器方法
    private EagerInitialization(){
        // 因为属性里面已经new过该类的实例了，所以这里只需提供一个空的构造方法就可以了
    }

    // 获取该类具有的这个单例的接口
    public static EagerInitialization getInstance(){
        return instance;
    }
}
