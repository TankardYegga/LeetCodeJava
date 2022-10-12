package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/15/2022
 * Time: 2:06 PM
 * Email: levinforward@163.com
 */
public class q17_numOfOne {

    // 判断一个给定正整数中到底含有多少个1
    public static int getNumOfOneInInteger(int num){
        if("".equals(num + "")){
            return 0;
        }

        int numOfOne = 0;
        while (num / 10 != 0){
            if(num % 10 == 1){
                numOfOne += 1;
            }
            num /= 10;
        }
        numOfOne += num == 1 ? 1: 0;

        return numOfOne;
    }

    public static int getNumOfOneInInteger2(int num){
        if("".equals(num + "")){
            return 0;
        }

        if(num == 0){
            return 0;
        }

        int numOfOne = 0;
        while (num % 10 != 0){
            if(num % 10 == 1){
                numOfOne += 1;
            }
            num /= 10;
        }

        return numOfOne;
    }

    public static int getNumLen(int num){
        if(num == 0){
            return 1;
        }

        int len = 0;
        while(len % 10 != 0){
            len++;
            len /= 10;
        }

        return len;
    }

    public static int powerBaseOf10(int times){
        int powerRes = 1;
        while(times != 0){
            powerRes *= 10;
            times--;
        }
        return powerRes;
    }

    public static int getNumOfOne(int num){
        if(num < 1){
            return 0;
        }

        //获取数字的长度
        int len = getNumLen(num);

        if(len == 1){
            return 1;
        }

        //划分为两部分，需要先非递归计算当前的一部分中1的个数
        //当前这部分1个数的计算又可以拆分为两部分
        //1. 最高位处1个数的计算
        //2. 其他位置处1个数的计算
        //两部分的计算取决于数字最高位的数值是否为1

        //最高位对应的实际数值
        int firstBaseValue = powerBaseOf10(len - 1);
        int firstDigit = num / firstBaseValue;

        int firstOneSum = firstDigit == 1? 1 + num % firstBaseValue: firstBaseValue;
        // int otherOneSum = firstDigit == 1? (len - 1) * (firstBaseValue / 10): firstDigit * (len - 1) * (firstBaseValue / 10);
        int otherOneSum = firstDigit * (len - 1) * (firstBaseValue / 10);

        return firstOneSum + otherOneSum + getNumOfOne(num % firstBaseValue);
    }

    public static void main(String[] args){

        int num = 12415;
        System.out.println(getNumOfOneInInteger(num));
        System.out.println(getNumOfOneInInteger2(num));
    }
}
