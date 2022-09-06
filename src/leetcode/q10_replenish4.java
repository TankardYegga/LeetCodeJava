package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: map的value可以是list，写一个例子
 * Creator: levin
 * Date: 9/3/2022
 * Time: 7:05 PM
 * Email: levinforward@163.com
 */
public class q10_replenish4 {

    public static void main(String[] args){
        List<String> l1 = new ArrayList<String>();
        l1.add("Hello");
        l1.add("World");

        List<String> l2 = new ArrayList<String>();
        l2.add("Java");
        l2.add("Spring");

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put("A:", l1);
        map.put("B:", l2);

        for(String key: map.keySet()){
            List<String> l = new ArrayList<String>();
            l = map.get(key);
            for(int i = 0; i < l.size(); i++){
                System.out.println(key + "的第" + i + "个元素：" + l.get(i));
            }
        }
    }
}

