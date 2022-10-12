package leetcode;

import java.util.Arrays;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/11/2022
 * Time: 10:33 PM
 * Email: levinforward@163.com
 */
public class q29_maxGap {

    public static int maxGap(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }

        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        int len = arr.length;

        for(int i = 0; i < len; i++){
            minVal = Math.min(minVal, arr[i]);
            maxVal = Math.max(maxVal, arr[i]);
        }

        if(minVal == maxVal){
            return 0;
        }

        //利用桶结构的几个特点：
        //1. 在计算出最小值和最大值之后，就可以在一遍遍历过程中将每个数放在对应的桶上去，
        //  某种程度上实现了排序
        //2. 利用了每个通的区间范围固定的这一特点，
        // 结合数值范围巧妙设计了桶的个数，从而确保至少有一个空桶
        // 被空桶间隔开的两个相邻元素的数值之差  必然 > 桶的区间长度
        // 而在同一个桶内的相邻元素的数值之差  必然 <= 桶的区间长度 - 1
        // 因此 我们可以得到不算差的平凡解：即空桶i的后面一个非空桶的最小值  - 前面一个非空桶内的最大值
        // 但是需要注意！：
        //  这个平凡解并不一定是最终的解，
        //  因为两个连续的非空桶之间的两个相邻数值之差 有可能比 中间隔开空桶的两个相邻数值之差要大
        // 所以需要比较所有非空桶之间的相邻数值，也就是前一个非空桶的最大值 和 后一个非空桶的最小值
        boolean[] hasNumber = new boolean[len + 1];
        int[] minNum = new int[len + 1];
        int[] maxNum = new int[len + 1];

        for(int i = 0; i < len; i++){
            int bucketId = getBucketId(arr[i], len, maxVal, minVal);
            minNum[bucketId] = hasNumber[bucketId]? Math.min(arr[i], minNum[bucketId]):arr[i];
            maxNum[bucketId] = hasNumber[bucketId]? Math.max(arr[i], maxNum[bucketId]):arr[i];
            hasNumber[bucketId] = true;
        }

        int ans = 0;
        int lastMax = maxNum[0];

        int ith = 1;

        for(;ith < len + 1; ith++){
            if(hasNumber[ith]){
                ans = Math.max(ans, minNum[ith] - lastMax);
                lastMax = maxNum[ith];
            }
        }

        return ans;
    }

    public static int getBucketId(int num, int len, int max, int min){
        return (int) ((num - min) * len / (max - min));
    }

    public static int Comparator(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }

        Arrays.sort(arr);

        int ans = 0;
        for(int i = 1; i < arr.length; i++){
            ans = Math.max(ans, arr[i] - arr[i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[20];


        for(int i = 0; i < 50; i++){
            for(int j = 0; j < arr.length; j++){
                arr[j] = 10 + (int)(Math.random() * (200 - 10 + 1));
            }
            int ans1 = maxGap(arr);
            int ans2 = Comparator(arr);

            if(ans1 != ans2){
                System.out.println("unequal answers");
                System.out.println("ans1:" + ans1);
                System.out.println("ans2:" + ans2);
            }
        }
    }
}
