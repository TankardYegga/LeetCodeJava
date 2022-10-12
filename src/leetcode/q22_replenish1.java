package leetcode;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/2/2022
 * Time: 10:48 AM
 * Email: levinforward@163.com
 */
public class q22_replenish1 {

    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(30);
        set.add(5);
        set.add(489);

        TreeSet<Integer> nums = new TreeSet<>();
        nums.add(1);
        nums.addAll(set);


        System.out.println("higher:" + nums.higher(5));
        System.out.println("ceiling:" + nums.ceiling(5));
        System.out.println("lower:" + nums.lower(30));
        System.out.println("floor:" + nums.floor(30));

        Iterator<Integer> it = nums.iterator();

        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
            System.out.println("");
        }

        System.out.println("first:" + nums.first());
        System.out.println("last:" + nums.last());

        boolean val1 = nums.remove(30);
        System.out.println(val1);

        Iterator<Integer> it2 = nums.iterator();
        while (it2.hasNext()){
            System.out.println(it2.next() + ", ");
        }

        nums.remove(5);
        nums.remove(489);

        boolean val2 = nums.removeAll(set);
        System.out.println(val2);
    }
}
