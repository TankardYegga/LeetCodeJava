package leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Description: LearnJVM
 * Creator: levin
 * Date: 11/2/2021
 * Time: 2:14 PM
 * Email: levinforward@163.com
 */
public class test_array {

    public static void main(String[] args) {
        int[][] a = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};

        // 也就是说这个函数是根据后面的lambda来判断的
        // 应该这里理解 此函数默认按照比较的大小从小到大来排列
        // 这里如果m[0] - n[0] > 0, 那么m也就要比n大，m比n拍在后面
        // 如果要是实现逆序排列，应该改成 n[0] - m[0]
        // 也就是表示如果 n[0] - m[0] > 0, 也就是n比m大的话，m比n拍在后面
        // 这里并没有固定第一个数相同时，第二个数要怎么排，但是默认是按照大小升序排列
        Arrays.sort(a, (m, n) -> (m[0] - n[0]));

        // Arrays.sort(a, (m, n) -> (n[0] - m[0]));

        System.out.println(a);

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[i].length; j++) {
                System.out.println(a[i][j]);
            }
    }
}
