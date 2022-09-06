package leetcode;

import java.util.ArrayList;

/**
 * Description: 补充解决q10需要的知识
 * Creator: levin
 * Date: 9/2/2022
 * Time: 5:16 PM
 * Email: levinforward@163.com
 * https://blog.csdn.net/sinat_26811377/article/details/96584293
 */
public class q10_replenish {

    // 判断一个数是不是质数
    public static boolean isPrime(int n){
        if(n <= 3){
            return n > 1;
        }

        for(int i = 2; i <= (int) Math.sqrt(n); i++){
            if(n % i == 0){
                return false;
            }
        }

        return true;
    }

    public static boolean isPrime2(int n){
        if(n <= 3){
            return n > 1;
        }

        for(int i = 2; i <= Math.ceil(Math.sqrt(n)); i++){
            if(n % i == 0){
                return false;
            }
        }

        return true;
    }

    public static boolean isPrime3(int n){
        if(n <= 3){
            return n > 1;
        }

        if(n % 2 == 0){
            return false;
        }

        for(int i = 3; i <= (int) Math.sqrt(n); i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime4(int n){
        if(n <= 3){
            return n > 1;
        }

        if(n % 6 != 1 && n % 6 != 5){
            return false;
        }

        // 为啥是除以这些可能的质数来进行判断呢
        // 为啥可以直接跳过中间的这些数呢？
        // 应该是这样的
        //  这些跳过的数是6x, 6x + 2, 6x + 3, 6x + 4（x = 1, 2, 3, ...总之x为正整数）
        //  因为在执行循环之前 已经确定了 n模6的结果只能是1或者5，
        //  那么n可以形式化表示为 6Y + 1 或者 6Y + 5
        //  或者用减法可以表示为 6Y - 5 或者 6Y - 1
        //  如果n能被这些跳过的数整除，
        //  则n可以写成 6kx，6kx + 2k, 6kx + 3k, 6kx + 4k （k = 1, 2, ... 总之k为正整数）
        //  很明显 2k, 3k, 4k都不可能对应于2,3,4
        //  所以：因为确定了n模6的结果必然是1或者5，那么这些被跳过的数必然是不会被n整除的了，就相当于这部分的整除结果完全是可以预知的了
        for(int i = 5; i <= (int) Math.sqrt(n);  i += 6){
            if(n % i == 0 || n % (i + 2) == 0){
                return false;
            }
        }

        return true;
    }

    // 求一个正整数所包含的所有质数因子
    // 难点就在于因子范围遍历的时候因子都是质数呢？
    // 难不成还要现将所有的质数求解出来？这样会很麻烦，一方面求解质数过程就会耗费额外的时间复杂度，另一方面不好确定这个质数因子列表多长才合适
    // 这里采用了一个很奇妙的技巧：从小到大对因子进行遍历的时候，如果当前因子能够整除n，就一直让当前因子整除n，直到当前因子不能整除n为止
    // 这样更大的非质数因子就不再能够整除n了

    // 代码里面注意2点：一是因子遍历的范围从2到n本身，因为如果n本身就是质数的话，那么n的质数因子必然包括n本身
    // 二是注意边界条件，n = 1的时候是不存在质数因子的概念的，因为1本身不是质数
    public static ArrayList<Integer> getAllPrimeFactors(int n){

        ArrayList<Integer> primeFactors = new ArrayList<Integer>();

        if(n == 1){
            return primeFactors;
        }

        for(int i = 2; i <= n; i++){
            while(n % i == 0){
                n /= i;
                primeFactors.add(i);
            }
        }

        return primeFactors;
    }

    // 求一个正整数所包含的所有整数因子
    public static ArrayList<Integer> getAllIntegerFactors(int n){
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++){
            if(n % i == 0){
                factors.add(i);
            }
        }
        return factors;
    }

    //求最大公约数
    // 使用辗转相除法
    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args){
        System.out.println(isPrime4(4));

//        ArrayList<Integer> primeFactors = getAllPrimeFactors(2);
//        for(int i = 0; i < primeFactors.size(); i++){
//            System.out.print(primeFactors.get(i) + ", ");
//        }

//        ArrayList<Integer> factors = getAllIntegerFactors(20);
//        for(int i = 0; i < factors.size(); i++){
//            System.out.println(factors.get(i) + ", ");
//        }

        System.out.println(gcd(20, 15));
    }
}
