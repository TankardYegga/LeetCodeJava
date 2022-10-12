package leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/30/2022
 * Time: 9:24 AM
 * Email: levinforward@163.com
 */
public class q18_LongestConsolidableSubArray {

    public static int getMaxLengthOfConsolidableSubArr(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }

        int len = 0;
        for(int L = 0; L < arr.length; L++){
            for(int R = L; R < arr.length; R++){
                if(isConsolidableArr(arr, L, R)){
                    len = Math.max(len, R - L + 1);
                }
            }
        }
        return len;
    }

    public static boolean isConsolidableArr(int[] arr, int left, int right){
        //使用Arrays函数库来拷贝数组和排序数组
        int[] arrCopy = Arrays.copyOfRange(arr, left, right + 1);
        System.out.println("arrCopy length:" + arrCopy.length);
        System.out.println("length2: " + (right - left));
        Arrays.sort(arrCopy);
        for(int i = 1; i < arr.length; i++){
            if(arr[i] - arr[i - 1] != 1){
                return false;
            }
        }
        return true;
    }

    public static int getMaxLengthOfConsolidableSubArr2(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }

        int len = 0;
        int maxVal = 0;
        int minVal = 0;
        HashSet<Integer> set = new HashSet<>();

        for(int L = 0; L < arr.length; L++){

            maxVal = Integer.MIN_VALUE;
            minVal = Integer.MAX_VALUE;
            set.clear();

            for(int R = L; R < arr.length; R++){

                if(set.contains(arr[R])){
                    break;
                }

                set.add(arr[R]);
                maxVal = Math.max(maxVal, arr[R]);
                minVal = Math.min(minVal, arr[R]);

                if(maxVal - minVal == R - L){
                    len = Math.max(len, R - L + 1);
                }
            }
        }

        return len;
    }
}
