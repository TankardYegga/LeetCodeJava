package leetcode;

/**
 * Description:
 * Creator: levin
 * Date: 10/22/2022
 * Time: 10:26 AM
 * Email: levinforward@163.com
 */
public class q32_KTimesOneTimes {

    //利用了K进制数中每个位置的数值范围都在0-K-1这个事实
    // 只要一个数重复了K次
    // 那么这K个数 在每个K进制位Xk上的和 = K * Xk
    // 其对K取余数 必然 为0
    // 而只出现一次的那个数
    // 在每个k进制位上的和  取余数  必然还等于原数

    // 1. 通过 位数组 来存储 数组中每个数 在对应K进制上 的和, 对k求mod的操作可以顺带一起完成
    // 2. 从  位数组 中 获取 只出现一次的那个数的数值
    public static int getOneTimeNumber(int[] arr, int K){
        int[] kBits = new int[32];


        for(int i = 0; i < arr.length; i++){
            setExclusiveOr(arr[i], K, kBits);
//            setExclusiveOr2(arr[i], K, kBits);
        }
        int ans = getOneTimeNumberFromBitSumArray(kBits, K);
        return ans;
    }

    public static void setExclusiveOr(int value, int K, int[] kBits){
        if(value == 0){
            return;
        }

        int comparedIndex = 0;
        while (Math.pow(K, comparedIndex) <= Math.abs(value)){
            comparedIndex++;
        }
        comparedIndex -= 1;

        if(Math.pow(K, comparedIndex) == value){
            kBits[comparedIndex] = (kBits[comparedIndex] + 1) % K;
            return;
        }

        int res = 0;
        int divider = (int) Math.pow(K, comparedIndex);
        while ( value != 0 ){
            res = value / divider;
            kBits[comparedIndex] = (kBits[comparedIndex] + res) % K;
            value %= divider;

            if(value != 0){
                comparedIndex -= 1;
                divider = (int) Math.pow(K, comparedIndex);
                while (value / divider == 0){
                    comparedIndex -= 1;
                    divider = (int) Math.pow(K, comparedIndex);
                }
            }
        }
    }

    public static void setExclusiveOr2(int value, int K, int[] kBits){
        int[] curKBitMap = getKBitMap(value, K);
        for(int i = 0; i < kBits.length; i++) {
            kBits[i] = (kBits[i] + curKBitMap[i]) % K;
        }
    }

    public static int[] getKBitMap(int value, int K){
        int[] kBitMap = new int[32];
        int index = 0;
        while (value != 0){
            kBitMap[index++] = value % K;
            value = value / K;
        }
        return kBitMap;
    }

    public static int getOneTimeNumberFromBitSumArray(int[] arr, int K){
        int ans = 0;
        for(int i = arr.length - 1; i >= 0; i--){
            ans = ans * K + arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int K = 3;
        int[] test1 = {1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9};
        System.out.println(getOneTimeNumber(test1, 3)); //9

        int[] test2 = {-1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2};
        System.out.println(getOneTimeNumber(test2, 5)); //4
//        System.out.println(-10 / 5);
//        System.out.println(-9 / 4);
    }
}
