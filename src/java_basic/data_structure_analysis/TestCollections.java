import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Description: JavaProjects
 * Creator: levin
 * Date: 1/19/2023
 * Time: 4:40 PM
 * Email: levinforward@163.com
 */
public class TestCollections {

    public static void main(String[] args) {
        Collection<String> c = new ArrayList();
        System.out.println(c.size());
        System.out.println(c.isEmpty());

        c.add("Good");
        c.add("bad");
        System.out.println(c);
        System.out.println(c.size());

        c.remove("bad"); //只是将这个对象的地址从集合中移除了，但是这个对象本身仍然存在
        System.out.println(c);
        System.out.println(c.size());

        Object[] arr = c.toArray();
        System.out.println(arr[0]);

        System.out.println(c.contains("Good"));

        c.clear();
        System.out.println(c.size());
    }
}
