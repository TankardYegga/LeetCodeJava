package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/30/2022
 * Time: 10:09 PM
 * Email: levinforward@163.com
 */
public class q19q20q21_maxStockProfit {

    public static int getMaxStockProfit(int[] stockPrice){
        if(stockPrice == null || stockPrice.length == 0){
            return 0;
        }

        int maxProfit = Integer.MIN_VALUE;

        for(int R = 0; R < stockPrice.length; R++){
            for(int L = 0; L <= R; L++){
                maxProfit = Math.max(maxProfit, stockPrice[R] - stockPrice[L]);
            }
        }

        return maxProfit;
    }

    public static int getMaxStockProfit2(int[] stockPrice){
        if(stockPrice == null || stockPrice.length == 0){
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int ans = Integer.MIN_VALUE;

        for(int i = 0; i < stockPrice.length; i++){
            minPrice = Math.min(minPrice, stockPrice[i]);
            ans = Math.max(ans, stockPrice[i] - minPrice);
        }

        return ans;
    }

    public static int getMaxStockProfit3(int[] stockPrice){
        if(stockPrice == null || stockPrice.length == 0){
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        int maxPrice = stockPrice[stockPrice.length - 1];

        for(int i = stockPrice.length - 1; i >= 0; i--){
            maxPrice = Math.max(maxPrice, stockPrice[i]);
            ans = Math.max(ans, maxPrice - stockPrice[i]);
        }

        return ans;
    }

    public static int getMaxProfitFromRandomDeals(int[] price){
        int ans = 0;
        for(int i = 1; i < price.length; i++){
            ans += Math.max(0, price[i] - price[i - 1]);
        }
        return ans;
    }

    public static int getMaxProfitInLimitedTrans(int[] price, int K){
        if(price == null || price.length == 0){
            return 0;
        }

        int N = price.length;

        if(K > N/2){
            return getMaxProfitFromRandomDeals(price);
        }

        int[][] dp = new int[N][K + 1];

        int ans = Integer.MIN_VALUE;

        for(int j = 1; j <= K; j++){

            int t = dp[0][j - 1] - price[0];

            for(int i = 1; i < N; i++){
                t = Math.max(t, dp[i][j - 1] - price[i]);
                dp[i][j] = Math.max(dp[i - 1][j], t + price[i]);
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }

    //股票问题的节省空间的写法
    public static int getMaxProfitInLimitedTrans2(int[] price, int K){
        if(price == null || price.length == 0){
            return 0;
        }

        int N = price.length;

        if(K > N / 2){
            return getMaxProfitFromRandomDeals(price);
        }

        int[] dp = new int[N];
        int ans = Integer.MIN_VALUE;

        for(int times = 1; times <= K; times++){
            int t = dp[0] - price[0];
            for(int i = 1; i < N; i++){
                t = Math.max(t, dp[i] - price[i]);
                dp[i] = Math.max(dp[i-1], t + price[i]);
                ans = Math.max(ans, dp[i]);
            }
        }

        return ans;
    }

    public static void main(String[] args){
        int[] test = {4, 1, 231, 21, 12, 312, 312, 3, 5, 2, 423, 43, 146};
        int K = 3;
        System.out.println(getMaxProfitInLimitedTrans(test, K));
        System.out.println(getMaxProfitInLimitedTrans2(test, K));
    }
}
