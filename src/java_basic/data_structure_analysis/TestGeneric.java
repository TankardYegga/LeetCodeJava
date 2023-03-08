import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 测试泛型
 * Creator: levin
 * Date: 1/19/2023
 * Time: 3:13 PM
 * Email: levinforward@163.com
 */
public class TestGeneric {

    public static void main(String[] args) {

        MyCollection mc = new MyCollection();
        mc.set("Ada", 0);
        mc.set(222, 1);
        int a = (Integer) mc.get(1);
        System.out.println(a);

        MyCollectionWithGeneric<String> mcG = new MyCollectionWithGeneric<String>();
        mcG.set("Zita", 0);
        String name = (String) mcG.get(0);
        System.out.println(name);

        List l = new ArrayList();
        Map m = new HashMap<>();
    }


}

class MyCollection{

    Object[] objs = new Object[5];

    public Object get(int index){
        return objs[index];
    }

    public void set(Object obj, int index){
        objs[index] = obj;
    }
}

class MyCollectionWithGeneric<E>{

    Object[] objs = new Object[5];

    public void set(E e, int index){
        objs[index] = e;
    }

    public Object get(int index){
        return (E) objs[index];
    }
}