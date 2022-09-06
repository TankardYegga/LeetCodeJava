package leetcode;

import javax.lang.model.type.NullType;
import java.util.Scanner;
/**
 * Description: Q6
 * Creator: levin
 * Date: 8/29/2022
 * Time: 2:57 PM
 * Email: levinforward@163.com
 */

public class q6 {
    private static final long[] EMPTY_ARRAY = new long[0];

    public static long[] mergeLeftAndRight(long[] leftArray, long[] rightArray){
        long[] arr = new long[leftArray.length + rightArray.length];
        for(int i = 0; i < arr.length; i++){
            if(i < leftArray.length){
                arr[i] = leftArray[i];
            }else{
                arr[i] = rightArray[i - leftArray.length];
            }
        }
        return arr;
    }

    public static long[] getLeftArray(long[] halfArray){
        long[] leftArray = new long[halfArray.length];
        for(int i = 0; i < leftArray.length; i++){
            leftArray[i] = 2 * halfArray[i] - 1;
        }
        return leftArray;
    }

    public static long[] getRightArray(long[] halfArray, boolean remainLastElem){
        long[] arr;
        int rightArrLength = 0;

        if(remainLastElem){
            rightArrLength = halfArray.length;
        }else{
            rightArrLength = halfArray.length - 1;
        }

        arr = new long[rightArrLength];
        for(int i = 0; i < rightArrLength; i++){
            arr[i] = halfArray[i] * 2;
        }

        return arr;
    }

    public static long[] getArray(int len){
        if(len == 0){
            return EMPTY_ARRAY;
        }

        if(len == 1){
            return new long[]{1};
        }

        long[] arr = new long[len];

        System.out.println("halfLen:" + len / 2);
        System.out.println("halfLen:" + (double) len / 2);
        int halfLen = (int) Math.ceil((double) len / 2);
        long[] halfArray = getArray(halfLen);
        long[] leftArray = getLeftArray(halfArray);
        boolean remainLastElem = true;
        if(halfLen * 2 - 1 == len){
            remainLastElem = false;
        }
        long[] rightArray = getRightArray(halfArray, remainLastElem);
        arr = mergeLeftAndRight(leftArray, rightArray);

        return arr;
    }

    public static void printArray(long[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println("the " + i + "element: " + arr[i]);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the length of the array:");
        int len = sc.nextInt();
        long[] arr = getArray(len);
        printArray(arr);
    }
}
