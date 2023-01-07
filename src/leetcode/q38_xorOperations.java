package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description: JVM
 * Creator: levin
 * Date: 11/22/2022
 * Time: 9:08 AM
 * Email: levinforward@163.com
 */
public class q38_xorOperations {

    //不引入第三方变量来交换数值
    public static void exchangeNumbers(int a, int b){
        if(a == b){
            return ;
        }

        System.out.println("a: " + a + " b: " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a: " + a + " b: " + b);
    }

    public static void exchangeNumbers2(int a, int b){
        if(a == b){
            return;
        }

        System.out.println("a: " + a + " b: " + b);
        b = a ^ b;
        a = b ^ a;
        b = b ^ a;
        System.out.println("a: " + a + " b: " + b);
    }

    public static boolean isEven(int num){
        return (num ^ 1) == 1 ? true: false;
    }

    //计算连续的异或
    public static int calContinuousXor(List<Integer> nums){
        if(nums == null){
            return -1;
        }

        int ans = 0;
        for(Integer num: nums){
            ans ^= num;
        }
        return ans;
    }

    //找出成对的数
    public static int pairNum(Integer[] arr){
        List<Integer> nums = Arrays.asList(arr);
        List<Integer> nonRedundantNums = new ArrayList<Integer>();
        for(int i = 1; i <= nums.size() - 1; i++){
            nonRedundantNums.add(i);
        }
        int numsXor = calContinuousXor(nums);
        int nonRedundantNumsXor = calContinuousXor(nonRedundantNums);
        return numsXor ^ nonRedundantNumsXor;
    }

    //找到落单的两个数
    public static void getIsolatedTwoNum(int[] nums){
        int xorSum = 0;
        for(int num: nums){
            xorSum ^= num;
        }

        int pos = 0;
        while ( (xorSum&(1<<pos++)) == 0){

        }

        pos -= 1;

        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        for(int i = 0; i < nums.length; i++){
            if( (nums[i]&(1<<pos)) == 0){
                arr1.add(nums[i]);
            }else{
                arr2.add(nums[i]);
            }
        }

        int value1 = 0;
        int value2 = 0;
        for(int i = 0; i < arr1.size(); i++){
            value1 ^= arr1.get(i);
        }
        for(int i = 0; i < arr2.size(); i++){
            value2 ^= arr2.get(i);
        }

        System.out.println(value1 + ":" + value2);
    }

    //求1-K中丢掉的两个数
    public static void findDroppedTwoNum(int[] nums){
        int[] numsL = new int[nums.length * 2 + 2];
        int i = 0;
        for (i = 0; i < nums.length; i++){
            numsL[i] = nums[i];
        }

        for(int j = 1; j <= nums.length + 2; j++){
            numsL[i++] = j;
        }

        getIsolatedTwoNum(numsL);
    }


    //求绝对值 是移动31而不是移动32
    public static int getAbsolute(int value){
        return value^(value>>31) + (value>>31);
    }

    public static void main(String[] args) {
        int  a = 30;
        int  b = 45;
        exchangeNumbers(a, b);
        exchangeNumbers2(a, b);

        Integer[] arr = {1, 2, 2, 3, 4, 5, 6};
        System.out.println(pairNum(arr));

        int[] nums = {5,5, 6, 6, 18, 9,9, 10,10, 12};
        getIsolatedTwoNum(nums);

        int[] numsDrop = {1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13};
        findDroppedTwoNum(numsDrop);

        System.out.println(getAbsolute(-18));
        System.out.println(getAbsolute(25));
    }
}
