package leetcode;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Arrays;

/**
 * Description: JVM
 * Creator: levin
 * Date: 8/14/2022
 * Time: 2:46 PM
 * Email: levinforward@163.com
 */
// 找绳子在坐标轴上怎么放置能覆盖最多的点数
// 代码注意事项如下：
// 1）特别注意nearestIndex1函数里面的value是arr[i] - ropeLen, 而不是i - ropeLen
// 2）注意二分法的终止条件是不等于，L<=R中的是有等于号的
// 3）注意这里的二分法可以处理的有序数组中是可以包含重复元素的，因为这里的index是会不断更新的，重复的元素不会导致递归结束；
//   但是一般的二分法里面 如果在数组中能查找到待查找值时，且该待查找值是重复时，就只会查找到一个位置；待查找的值如果是非重复值的话，应该没有影响
// 4） 注意数组下标加加减减时要防止越界
public class q1 {

    //  方法1：绳子的右端点固定在点上
    public static int maxPoint1(int[] arr, int ropeLen){
        int ans = 1;
        for(int i = 0; i < arr.length; i++){
            int index = nearestIndex1(arr, i, ropeLen);
            ans = Math.max(ans, i - index + 1);
        }
        return ans;
    }

    public static int nearestIndex1(int[] arr, int i, int ropeLen){
        int L = 0;
        int R = i;
        int value = arr[i] - ropeLen;
        int index = i;
        while ( L <= R ){
            int mid = L + ((R - L) >> 1);
            if( arr[mid] >= value){
                index = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return index;
    }


    //  方法2：绳子的左端点固定在点上
    public static int maxPoint2(int[] arr, int ropeLen){
        int ans = 1;
        for(int i = 0; i < arr.length; i++){
            int index = nearestIndex2(arr, i, ropeLen);
            ans = Math.max(ans, index - i + 1);
        }
        return ans;
    }

    public static int nearestIndex2(int[] arr, int i, int ropeLen){
        int L = i;
        int R = arr.length - 1;
        int index = i;
        int value = arr[i] + ropeLen;
        while(L <= R){
            int mid = L + ((R - L) >> 1);
            if(arr[mid] <= value){
                index = mid;
                L = mid + 1;
            }else{
                R = mid - 1;
            }
        }
        return index;
    }

    //  方法3：利用窗口法从前往后滑动
    public static int maxPoint3(int[] arr, int ropeLen){
        int left = 0;
        int right = 0;
        int ans = 0;
        while(left <= right){
            while(right < arr.length && (arr[right] - arr[left] <= ropeLen)){
                right++;
            }
            ans = Math.max(ans, right - (left++));
        }
        return ans;
    }

    //  方法4：利用窗口法从后往前滑动
    public static int maxPoint4(int[] arr, int ropeLen){
        int left = arr.length - 1;
        int right = arr.length - 1;
        int ans = 0;
        while (left <= right){
            while (left >= 0 && (arr[right] - arr[left] <= ropeLen)){
                left--;
            }
            ans = Math.max(ans, (right--) - left);
        }
        return ans;
    }

    //  生成满足两个给定条件的有序的随机数组
    //  一个是数组的长度为给定值，另一个是数组中的元素有最大值要求
    // 这里为了使得生成的随机数随机性更强一点，可以使得数组的长度是1-给定值之间的任意一个值就行了
    // Math.random() * arrLen是[0, arrLen), 但是取整后却只能是[0, arrLen - 1]
    public static int[] generateRandomArray(int arrLen, int valMax){
        int[] arr = new int[(int) (Math.random() * arrLen) + 1];
        //允许重复值
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int) (Math.random() * valMax);
        }
        Arrays.sort(arr);
        return arr;
    }

    public static int maxPoint5(int[] arr, int L){
        int res = 1;
        for(int i = 0; i < arr.length; i++){
            int nearest = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    public static int nearestIndex(int[] arr, int R, int value){
        int L = 0;
        int index = R;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if(arr[mid] >= value){
                index = mid;
                R = mid - 1;
            }else{
                L = mid + 1;
            }
        }
        return index;
    }

    // 进行辅助测试的代码
    public static void main(String[] args){
        int arrLen;
        int valMax;
        int ropeLen;
        int testTime = 1;
        int[] arr;

        System.out.println();
        for(int i = 0; i < testTime; i++){
            valMax = (int) (Math.random() * 1000);
            arrLen = (int) (Math.random() * valMax);
            ropeLen = (int) (Math.random() * valMax);
            arr = generateRandomArray(arrLen, valMax);
            int ans5 = maxPoint5(arr, ropeLen);
            int ans1 = maxPoint1(arr, ropeLen);
            int ans2 = maxPoint2(arr, ropeLen);
            int ans3 = maxPoint3(arr, ropeLen);
            int ans4 = maxPoint4(arr, ropeLen);
            System.out.println(ans1);
            System.out.println(ans2);
            System.out.println(ans3);
            System.out.println(ans4);
            System.out.println(ans5);
            System.out.println(ans1 == ans2);
        }
    }
}
