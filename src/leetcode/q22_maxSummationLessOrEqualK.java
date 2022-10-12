package leetcode;

import java.util.TreeSet;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/2/2022
 * Time: 10:48 AM
 * Email: levinforward@163.com
 */
public class q22_maxSummationLessOrEqualK {

    public static int getMaxSummationLessOrEqualK(int[] arr, int K){

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);

        int ans = Integer.MIN_VALUE;
        int sum = 0;

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            //举出一个下面条件不成立的例子
            // 比如 K = 10
            // 而 arr = { 13, -12, 11}
            // 第一个数13 set = {0}
            //  13 - 10 = 3，set.ceiling(3) = null
            // 第二个数-12  set = {0, 13}
            //  1 - 10 = -9, set.ceiling(-9) = 0
            // 第一个满足条件的累加和 1 - 0 = 1
            // 第三个数 11 set = {0, 13, 1}
            // 12 - 10 = 2， set.ceiling(2) = 13
            // 第二个满足条件的累计和  12 - 13 = -1
            if(set.ceiling(sum - K) != null){
                ans = Math.max(ans, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }
        return ans;
    }

    public static int getMaxSummationLessOrEqualK2(int[] arr, int K){
        if(arr == null || arr.length == 0){
            return 0;
        }

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0;
        int ans = Integer.MIN_VALUE;

        for(int i = arr.length - 1; i >= 0; i--){
            sum += arr[i];
            if(set.ceiling(sum - K) != null){
                ans = Math.max(ans, sum - set.ceiling(sum - K));
            }
            set.add(sum);
        }

        return ans;
    }
}
