import java.util.ArrayList;
import java.util.List;

/**
 * Description: JavaProjects
 * Creator: levin
 * Date: 1/19/2023
 * Time: 5:10 PM
 * Email: levinforward@163.com
 */
public class TestInterListsOperations {

    public static void main(String[] args) {
        List<String> list01 = new ArrayList<>();
        list01.add("Reach");
        list01.add("Mendacity");
        list01.add("Adorable");

        List<String> list02 = new ArrayList<>();
        list02.add("Caught");
        list02.add("Reach");
        list02.add("Gopher");

        List<String> list03 = new ArrayList<>();
        list03.addAll(list01);
        list03.add("John");

//        list01.addAll(list02); //不会进行自动去重
//        System.out.println(list01);

//        list01.addAll(list01); //自己和自己进行汇总,这是可以执行的
//        System.out.println(list01);

        Boolean hasCovered = list01.containsAll(list02);
        System.out.println(hasCovered);
        hasCovered = list03.containsAll(list01);
        System.out.println(hasCovered);

        list03.removeAll(list01);
        System.out.println(list03);

        list01.retainAll(list02);
        System.out.println(list01);

        List<String> list05 = new ArrayList<>();
        list05.add("M");
        list05.add("N");
        list05.add("0");
        list05.add("P");

        System.out.println(list05);
        list05.add(2, "Q");
        System.out.println(list05);

        list05.remove(2);
        System.out.println(list05);

        list05.set(2, "U");
        System.out.println(list05);

        System.out.println(list05.get(2));
    }
}
