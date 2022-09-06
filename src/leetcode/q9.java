package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/2/2022
 * Time: 11:09 AM
 * Email: levinforward@163.com
 */
public class q9 {
    public static int getMaxAbsDiff(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }

        int maxAbsDiff = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            maxAbsDiff = Math.max(maxAbsDiff, arr[i]);
        }

        return Math.max(maxAbsDiff - arr[0], maxAbsDiff - arr[arr.length - 1]);
    }

    public static void main(String[] args){
        int[] arr = {-5, 6, 12, 10, -5, -7, 6};
        System.out.println(getMaxAbsDiff(arr));
    }
}
