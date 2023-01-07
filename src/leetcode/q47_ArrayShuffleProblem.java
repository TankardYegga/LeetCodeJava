package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 12/28/2022
 * Time: 11:37 PM
 * Email: levinforward@163.com
 */
public class q47_ArrayShuffleProblem {

    public static int modifyIndex(int i, int len){
        if(i <= len / 2){
            return 2 * i;
        }else{
            return 2 * (i - len / 2) - 1;
        }
    }

    public static int modifyIndex2(int i, int len){
        return (2 * i) % (len + 1);
    }

    //对数组中的[L，M]和[M+1, R]进行位置互换
    public static void rotate(int[] arr, int L, int M, int R){
        reverseArr(arr, L, M);
        reverseArr(arr, M + 1, R);
        reverseArr(arr, L, R);
    }

    //对数组进行逆序调整
    public static void reverseArr(int[] arr, int left, int right){
        //双指针向数组中央移动
        //无论数组长度是偶数还是奇数，重点都是左右指针相遇，因为奇数长度的数组中心元素无需进行位置调整
        while (left < right){
            int temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }

    //在arr[L..R]上做完美洗牌
    public static void shuffle(int[] arr, int L, int R){
        int len = R - L + 1;

        //数组长度为奇数，无效洗牌问题
        if( (len & 1) == 1 ){
            return;
        }

        while (R - L + 1 > 0){

            //1. 计算当前可以划分的块的最大长度(满足 3^k - 1)
            int curLen = R - L + 1;
            //k 从1开始计算，也就是说最大长度从2开始计算
            int k = 1;
            int startLen = 3 * k;
            while(startLen - 1 <= curLen){
                k++;
                startLen = 3 * startLen;
            }
            k = k - 1;
            startLen /= 3;

            //2. 进行数组左右部分的元素置换
            int halfLen = startLen >> 1;
            int mid = (L + R) >> 1;
            // int mid = L + (curLen >> 1) - 1;

            rotate(arr, L + halfLen, mid, mid + halfLen);

            //3. 进行循环移位
            cycles(arr, k, L, startLen);

            //4. 更新进行递归的子问题的数组范围
            L = L + startLen;
        }
    }

    public static void cycles(int[] arr, int k, int start, int len){
        //从每个循环圈的起始位置开始循环移位，一共k个循环圈
        for(int cycleTh = 0, cycleTrigger = 1; cycleTh < k; cycleTh++, cycleTrigger *= 3){
            //获取当前循环圈的第一个元素的值
            int preValue = arr[start + cycleTrigger - 1];
            int curIndex = modifyIndex2(cycleTrigger, len);
            while (curIndex != cycleTrigger){
                int temp = arr[start + curIndex - 1];
                arr[start + curIndex - 1] = preValue;
                preValue = temp;
                curIndex = modifyIndex2(curIndex, len);
            }
            arr[start + curIndex - 1] = preValue;
        }
    }


    public static void shuffle2(int[] arr, int L, int R){
        int originalLen = R - L + 1;
        if((originalLen & 1) == 1){
            return;
        }

        while (R - L + 1 > 0){
            int len = R - L + 1;

            int k = 1;
            int base = 3;

            while (base <= (len + 1) / 3){
                base *= 3;
                k++;
            }

            int half = (base - 1) / 2;
            int mid = (L + R) / 2;

            rotate(arr, L + half, mid, mid + half);

            cycles(arr, k, L, base - 1);

            L = L + base - 1;
        }


    }
}
