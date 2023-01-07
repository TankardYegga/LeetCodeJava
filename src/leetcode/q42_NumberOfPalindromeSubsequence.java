package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 12/5/2022
 * Time: 10:26 PM
 * Email: levinforward@163.com
 */
public class q42_NumberOfPalindromeSubsequence {

    public static int calNum(String str){
        char[] chars = str.toCharArray();
        int n = chars.length;

        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            dp[i][i] = 1;
        }

        for(int i = 0; i < n - 1; i++){
            dp[i][i] = (chars[i] == chars[i+1]) ? 3: 2;
        }

        for(int i = n - 3; i >= 0; i--){
            for(int j = i + 2; j < n; j++){
                if(chars[i] == chars[j]){
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] + 1;
                }else {
                    dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1];
                }
            }
        }

        return dp[0][n-1];
    }

    public static int calNum2(String str){
        char[] chars = str.toCharArray();
        int n = chars.length;

        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){

            dp[i][i] = 1;

            if(i + 1 < n){
                dp[i][i+1] = chars[i] == chars[i+1] ? 3: 2;
            }
        }

        for(int col = 2; col < n; col++){
            for(int r = 0, h = col; h < n; r++, h++){
                dp[r][h] = dp[r + 1][h] + dp[r][h - 1] - dp[r + 1][h - 1];

                if(chars[r] == chars[h]){
                    dp[r][h] += (dp[r + 1][h - 1] + 1);
                }
            }
        }

        return dp[0][n-1];
    }


}
