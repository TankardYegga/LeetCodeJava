package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/15/2022
 * Time: 10:11 AM
 * Email: levinforward@163.com
 */
public class q16_MemoryCoin {

    //moneyValue是无重复币值的纪念币的币值数组
    public static int[][] getDpArbitraryCoin(int[] moneyValue, int money){
        if(moneyValue == null || moneyValue.length == 0){
            return null;
        }

        int[][] dp = new int[moneyValue.length][money + 1];

        //init
        for(int coinTh = 0; coinTh < moneyValue.length; coinTh++){
            dp[coinTh][0] = 0;
        }

        for (int j = 1; j * moneyValue[0] <= money; j++){
            dp[0][j * moneyValue[0]] = 1;
        }

        for(int i = 1; i < moneyValue.length; i++){
            for(int j = 1; j <= money; j++){
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += (j - moneyValue[i] >= 0) ? dp[i][j - moneyValue[i]]: 0;
            }
        }

        return dp;
    }

    public static int[][] getDpOnlyOneCoin(int[] moneyValue, int money){
        if(moneyValue == null || moneyValue.length == 0){
            return null;
        }

        int[][] dp = new int[moneyValue.length][money + 1];

        for(int i = 0; i < moneyValue.length; i++){
            dp[i][0] = 1;
        }
        dp[0][moneyValue[0]] = 1;

        for(int i = 1; i < moneyValue.length; i++){
            for(int j = 1; j <= money; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - moneyValue[i] >= 0){
                    dp[i][j] += dp[i - 1][j - moneyValue[i]];
                }
            }
        }

        return dp;
    }

    public static int momeyWays(int[] arbitraryCoinValue, int[] onlyOneCoinValue, int money){
        if(money < 0){
            return 0;
        }

        if((arbitraryCoinValue == null || arbitraryCoinValue.length == 0) &&
                (onlyOneCoinValue == null || onlyOneCoinValue.length == 0)){
            return money == 0? 1: 0;
        }

        int[][] dpArbitrary = getDpArbitraryCoin(arbitraryCoinValue, money);
        int[][] dpOnlyOne = getDpOnlyOneCoin(onlyOneCoinValue, money);

        if(arbitraryCoinValue == null){
            return dpOnlyOne[onlyOneCoinValue.length - 1][money];
        }

        if(onlyOneCoinValue == null){
            return dpArbitrary[arbitraryCoinValue.length - 1][money];
        }

        int res = 0;
        for (int i = 0; i <= money; i++){
            res += dpArbitrary[arbitraryCoinValue.length - 1][i] * dpOnlyOne[onlyOneCoinValue.length - 1][money - i];
        }

        return res;
    }

}

