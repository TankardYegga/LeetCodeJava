package leetcode;

import java_basic.webserver.Mapping;

/**
 * Description:
 * Creator: levin
 * Date: 11/3/2022
 * Time: 8:27 PM
 * Email: levinforward@163.com
 */
public class q35_findKthSmallNumber {

    //依据长度为偶数还是奇数来返回offset
    public static int getOffset(int len){
        //注意 ^ 是异或操作符
        //只有当两个位一个是1另一个是0时，运算的结果才是1
        //当len为奇数时，len & 1的结果是1， 1^1的结果是0;
        //当len为偶数时，len & 1的结果是0, 0^1的结果是1
        //因此可以简单地将这个函数的作用概括为：奇数时返回0，偶数时返回1，
        // 拓展思考：
        // 【1】也可以使用 num | 0， 当num为奇数时，结果为1，当num为偶数时，结果为0
        // num & 0 或者  num | 1 这两个表达式是没有意义的，因为其输出结果与num本身无关
        // 【2】可以发现 num ^ 1　能够进行０－１反转操，当然0-1翻转可以直接通过 1 - num 来实现；
        // num ^ 0, 则是 一个不变操作符, 原数是什么，结果就是什么
        // 【3】同或操作符 在java中没有直接提供封装好的操作符，
        // 但是其可以通过异或运算符来实现
        return (len & 1) ^ 1;
    }

    // 同或 = (num1 ^ num2) ^ (111...)
    // num1有多少个bit位，后面的1就有多少位
    //
    public static int exclusiveNOR(int num1, int num2, int numberOfBits){
        System.out.println("num1 ^ num2:" + (num1 ^ num2));
        int continuousOneNum = getContinuousOne(numberOfBits);
        return (num1 ^ num2) ^ continuousOneNum;
    }

    public static int getContinuousOne(int numberOfBits) {
        StringBuilder continuousOneStrBuilder = new StringBuilder();
        for (int i = 0; i < numberOfBits; i++) {
            continuousOneStrBuilder.append("1");
        }
        String continuousOneStr = continuousOneStrBuilder.toString();
        System.out.println("continuousOneStr:" + continuousOneStr);
        int continuousOne = (int) Long.parseLong(continuousOneStr, 2);
        System.out.println("continuousOne:" + continuousOne);
        return continuousOne;
    }


    //获取上中位数
    public static int getUpMedian(int[] a, int s1, int e1, int[] b, int s2, int e2){
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;

        while(s1 < e1){
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ( (e1 - s1 + 1) & 1 ) ^ 1;
            if(a[mid1] > b[mid2]){
                e1 = mid1;
                s2 = mid2 + offset;
            }else if(a[mid1] < b[mid2]){
                s1 = mid1 + offset;
                e2 = mid2;
            }else {
                return a[mid1];
            }
        }
        //这种情况应该是递归的数组长度为1了，也就是s1=e1,s2=e2
        //
        return Math.min(a[s1], b[s2]);
    }

    //对奇偶数分开讨论，不进行形式上的合并
    public static int getUpMedian2(int[] a, int s1, int e1, int[] b, int s2, int e2){
        int mid1 = 0;
        int mid2 = 0;
        boolean isEven = false;

        while (s1 < e1){
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            isEven = ((e1 - s1 + 1) ^ 1) == 0;

            if(a[mid1] == b[mid2]){
                return a[mid1];
            }

            if(isEven){
                //若是偶数
                if(a[mid1] > b[mid2]){
                    e1 = mid1;
                    s2 = mid2 + 1;
                }else {
                    s1 = mid1 + 1;
                    e2 = mid2;
                }
            }else{
                //若是奇数
                if(a[mid1] > b[mid2]){
                    // 判断b[mid2]是否为解
                    if(b[mid2] > a[mid1 - 1]){
                        return b[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                }else {
                    if(a[mid1] > b[mid2 - 1]){
                        return a[mid1];
                    }
                    s1 = mid1 + 1;
                    e2 = mid2 - 1;
                }
            }
        }
        return Math.min(a[s1], b[s2]);
    }

    //求解等长有序数组arr1 和 arr2 的 第K小
    //arr1 和 arr2 都是从小到大排列
    public static int findKthNum(int[] arr1, int[] arr2, int kth){
        if(arr1 == null || arr2 == null){
            return -1;
        }

        if(kth < 1 || kth > arr1.length + arr2.length){
            return -1;
        }

        int[] longArr = arr1.length >= arr2.length ? arr1: arr2;
        int[] shortArr = arr1.length < arr2.length ? arr1: arr2;

        if(kth <= shortArr.length){
            return getUpMedian(shortArr, 0, kth - 1, longArr, 0, kth - 1);
        }else if(kth <= longArr.length){
            if(longArr[kth - shortArr.length - 1] > shortArr[shortArr.length - 1]){
                return longArr[kth - shortArr.length - 1];
            }
            return getUpMedian(shortArr, 0, shortArr.length - 1,
                    longArr, kth - shortArr.length, longArr.length - 1);
        }else{
            // 判断 短数组 中的 K - |长数组| 这个位置
            if(shortArr[kth - longArr.length - 1] > longArr[longArr.length - 1]){
                return shortArr[kth - longArr.length - 1];
            }
            // 判断 长数组 中的 K - |短数组| 这个位置
            if(longArr[kth - shortArr.length - 1] > shortArr[shortArr.length - 1]){
                return longArr[kth - shortArr.length - 1];
            }
            return getUpMedian(shortArr, kth - longArr.length,  shortArr.length - 1,
                    longArr, kth - shortArr.length, longArr.length - 1);
        }
    }

    public static void main(String[] args) {
        int len = 10;
        System.out.println(getOffset(len));
        len = 11;
        System.out.println(getOffset(len));

        int num1 = 10; // 8 + 2 = 01010
        int num2 = 20; // 16 + 4 = 10100
        // 同或是：1111 1111    1111 1111   1111 1111   1110 0001
        System.out.println(exclusiveNOR(num1, num2, 32)); //
    }

}
