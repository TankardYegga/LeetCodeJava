package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 11/22/2022
 * Time: 12:43 PM
 * Email: levinforward@163.com
 */
public class q38_nimGambling {

    public static int whoWin(int[] coins){
        int xorSum = 0;
        for(int coinValue: coins){
            xorSum ^= coinValue;
        }
        return xorSum == 1? 0: 1;
    };

}
