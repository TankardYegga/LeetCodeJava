package raw_datastructure;

import java.util.LinkedList;
import java.util.List;

/**
 * Description: JavaProjects
 * Creator: levin
 * Date: 2/5/2023
 * Time: 4:08 PM
 * Email: levinforward@163.com
 */
public class TestLinkedList {

    public static void main(String[] args) {
        List<String> l = new LinkedList<>();

        l.add("B");

        ((LinkedList<String>) l).addFirst("A");

        ((LinkedList<String>) l).addLast("C");

        l.remove("B");
    }
}
