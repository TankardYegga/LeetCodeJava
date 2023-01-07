package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 12/6/2022
 * Time: 12:20 AM
 * Email: levinforward@163.com
 */
public class q40_longestParlindrome {

    public static int getMaxLengthOfParlindrome(String str){
        char[] chars = str.toCharArray();

        int n = chars.length;

        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            dp[i][i] = 1;

            if(i + 1 < n){
                dp[i][i+1] = (chars[i] == chars[i + 1]) ? 2: 1;
            }
        }

        int candidateVal1 = Integer.MIN_VALUE;
        int candidateVal2 = Integer.MIN_VALUE;
        int candidateVal3 = Integer.MIN_VALUE;

        for(int i = n - 3; i >= 0; i--){
            for(int j = i + 3; j < n; j++){
                candidateVal1 = dp[i + 1][j];
                candidateVal2 = dp[i][j - 1];
                candidateVal3 = dp[i + 1][j - 1];
                if(chars[i] == chars[j]){
                    candidateVal3 += 2;
                }
                dp[i][j] = Math.max(Math.max(candidateVal1, candidateVal2), candidateVal3);
            }
        }

        return dp[0][n-1];
    }
}
