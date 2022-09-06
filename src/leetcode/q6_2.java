package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Description: 对q6的代码进行优化
 * Creator: levin
 * Date: 8/29/2022
 * Time: 5:46 PM
 * Email: levinforward@163.com
 */

/*
q6中代码可以优化的点在于：
【1】获取左边的数组、获取右边的数组、以及合并两个数组都不需要写单独的函数出来，
这三个操作中间会用到三次复制，也会用到三个数组空间，
因为三个函数会返回三个数组空间的引用，并不会立马释放空间。
这三个函数可以直接在原函数中完成，并且只会用到复制必须要用到的空间大小。
【2】对除以2的操作不必非要使用math库
   向上取整 （x+1）/2  (6+1)/2 = 3, (7+1)/2 = 4
   向下取整 X/2    6/2 = 3,  7 / 2 = 3
【3】空间复杂度也可以继续优化
   这里的空间复杂度毫无疑问是O(N)
   但是可以优化成O(1)
   其实就是对递归过程的非递归改写
   对于已经计算出来的特定长度的数值，先对其进行偶数变换填充在右半边位置，然后再对其进行奇数变换填充在左半边位置
 */

public class q6_2 {
    public static int[] makeNoArray(int m){
        if(m == 0){
            System.out.println("array is empty");
            return new int[]{};
        }

        if(m == 1){
            return new int[]{1};
        }

        int baseArrLen = (m + 1) / 2;
        int[] baseArr = makeNoArray(baseArrLen);
        int[] curArr = new int[baseArrLen];

        for(int i = 0; i < baseArrLen; i++){
            curArr[i] = baseArr[i] * 2 - 1;
        }
        for(int i = baseArrLen; i < m; i++){
            curArr[i] = baseArr[i - baseArrLen] * 2;
        }

        return curArr;
    }

    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println("the " + i + "element: " + arr[i]);
        }
    }


    // 非递归实现: 与makeNoArray的方法不同，长度为6的数组是长度为8的数组丢弃最后一个元素得到，而非由长度为3的数组得到
    public static int[] makeEfficientNo(int m){
        if(m == 0){
            return new int[]{};
        }

        if(m == 1){
            return new int[]{1};
        }

        int[] arr = new int[m];
        arr[0] = 1;

        // 难点就在于怎么确定每次作用的范围
        // 就拿7举例吧
        //  1
        //  1  2
        //  1  3  2  4
        //  1  5  3  7  2 6 4 8
        //  可以不必实现求解次数的
        //  直接按照这样的逻辑求
        //  当数组范围越过边界的时候就可以知道需要停止了
        //  这与递归法的结果不相同

        // 先需要明确当前的范围
        int start = 0;
        int end = 0;
        int len = 1;
        while((end + (end - start + 1)) <= m - 1){
            for(int i = start; i <= end; i++){
                arr[end + 1 + i - start] = arr[i] * 2;
            }
            for(int i = start; i <= end; i++){
                arr[i] = arr[i] * 2 - 1;
            }
            len = end - start + 1;
            end += len;
        }

        if((end + (end - start + 1)) > m - 1){
            for(int j = start + (end - start + 1); j <= m - 1; j++){
                arr[j] = arr[j - (end - start + 1)] * 2;
            }
            for(int i = start; i < start + (end - start + 1); i++){
                arr[i] = arr[i] * 2 - 1;
            }
        }

        return arr;
    }

    // 对makeEfficientNo函数的优化
    public static int[] makeEfficientNo2(int m){
        if(m == 0){
            return new int[]{};
        }

        if(m == 1){
            return new int[]{1};
        }

        int[] arr = new int[m];
        arr[0] = 1;

        int start = 0;
        int end = 0;
        int len = end - start + 1;
        while(end + len < m){
            for(int i = start; i <= end; i++){
                arr[i + len] = arr[i] * 2;
            }
            for(int i = start; i <= end; i++){
                arr[i] = arr[i] * 2 - 1;
            }
            end += len;
            len = end - start + 1;
        }

        if(end + len >= m){
            for(int j = start + len; j < m; j++){
                arr[j] = arr[j - len] * 2;
            }
            for(int i = start; i <= end; i++){
                arr[i] = arr[i] * 2 - 1;
            }
        }
        return arr;
    }

    // 与makeNoArray完全对应的非递归方式
    public static int[] makeNoArrayEfficient(int m){
        if(m == 0){
            return new int[]{};
        }

        if(m == 1){
            return new int[]{1};
        }

        // 长度为6
        // 1
        // 1 2
        // 1 3 2 4
        // 1 3 2  或者  3 2 4
        // 1 5 3 2 6 4 或者  5 3 7 6 4 8
        // 其实难点就在于哪一步的时候对数组的长度进行截断
        // 其实每一步拓展数组的过程跟makeEfficientNo2相同，但是需要对数组进行截断
        // 为了截断的方便，统一对最后一个元素进行丢弃
        // 正常情况下数组的长度变化是：【1,2，4，8】
        // 而在此种情况下的长度变化是：【1,2,3,6】
        // 正常情况下的长度变化非常好计算，数组拓展的次数乘以2倍就行
        // 必须得实现递归过程中的实际要求的长度变化

        // 长度为5的话
        //  1
        //  1  2
        //  1  3  2  4
        //  1  3  2
        //  1  5  3  2 6  4

        ArrayList<Integer> totalLenArr = new ArrayList<Integer>();
        ArrayList<Integer> cutLenArr = new ArrayList<Integer>();
        //[6, 4, 2]
        //[0, 1, 1]
        int curHalfLen = ((m + 1) / 2);
        int cutLen = curHalfLen * 2 - m;
        totalLenArr.add(curHalfLen * 2);
        cutLenArr.add(cutLen);
        while(curHalfLen != 1){
            int newCurHalfLen = (curHalfLen + 1) / 2;
            totalLenArr.add(newCurHalfLen * 2);
            cutLen = newCurHalfLen * 2 - curHalfLen;
            cutLenArr.add(cutLen);
            curHalfLen = newCurHalfLen;
        }
        Collections.reverse(totalLenArr);
//        Collections.reverse(cutLenArr);

        for(int i = 0; i < totalLenArr.size(); i++){
            System.out.print(totalLenArr.get(i) + ",");
        }
        System.out.println();

        for(int i = 0; i < cutLenArr.size(); i++){
            System.out.print(cutLenArr.get(i) + ",");
        }
        System.out.println();

        int times = totalLenArr.size();
        int[] arr = new int[m];
        arr[0] = 1;

        int start = 0;
        int end = 0;
        int len = end - start + 1;
        while(times > 0){
//            for(int i = start; i <= end; i++){
//                arr[i + len] = arr[i] * 2;
//            }
            // 因为这里的times帮忙确定了需要处理的次数
            //  原先以为就没有越界判断了
            //  但是长度是单独拿出来进行计算的，
            //  而并没有在数组的复制操作当中考虑
            //  所以仍然可能越界
            //  所以将上面的换了以下写法
            for(int i = start + len; i <= m - 1; i++){
                arr[i] = arr[i - len] * 2;
            }
            for(int i = start; i <= end; i++){
                arr[i] = arr[i] * 2 - 1;
            }
            end += (len - cutLenArr.get(times - 1));
            times -= 1;
            len  = end - start + 1;
        }

        return arr;
    }

    public static void main(String[] args){
        int m;
        Scanner sc = new Scanner(System.in);
        System.out.print("Input m:");
        m = sc.nextInt();
//        int[] arr = makeNoArray(m);
//        printArray(arr);
//        int[] arr2 = makeEfficientNo(m);
//        printArray(arr2);
//        int[] arr3 = makeEfficientNo2(m);
//        printArray(arr3);
        int[] arr4 = makeNoArrayEfficient(m);
        printArray(arr4);
    }
}

