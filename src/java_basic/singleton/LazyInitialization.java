/**
 * Description: JavaProjects
 * Creator: levin
 * Date: 2/2/2023
 * Time: 8:50 PM
 * Email: levinforward@163.com
 */
public final class LazyInitialization {

    // 懒初始化：
    // 是一种 将对象的创建、某个值的计算以及其他等耗时昂贵的过程 延迟到 这个需求第一次实际被要求使用时
    // 也就是：将对象的创建过程 延迟到  第一次要求创建该对象
    //       将值的计算过程  延迟到  第一次需要该值的计算结果时

    private static volatile LazyInitialization instance = null;

    //私有构造器
    private LazyInitialization(){

    }

    //获取该类的单例的接口方法
    public static LazyInitialization getInstance(){
        // 如果该实例尚未创建，则：立马创建并返回
        if(instance == null){
            // 在同步锁内创建对象的单例
            // 为何要使用同步锁？
            // 确保instance得到的结果可以维持同步
            // 不然，若该instance还没有创建完成，则其他使用该instance的地方会报出空指针异常
            synchronized (LazyInitialization.class){
                // double check
                // 是为了避免两个不同的线程先后进入时, 都来到同步块里面创建出两个实例
                if(instance == null){
                    instance = new LazyInitialization();
                }
            }
        }
        return instance;
    }
}
