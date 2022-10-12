package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/6/2022
 * Time: 4:10 PM
 * Email: levinforward@163.com
 */
public class q26_removePatternsNum {

    public static int getRemovePatternsNumber(String s, String t){
        if(s == null || t == null){
            return 0;
        }

        if(s.length() < t.length()){
            return 0;
        }

        if(s.length() == t.length()){
            return s.equals(t) ? 1: 0;
        }

        int[][] dp = new int[s.length()][t.length()];

        dp[0][0] = s.charAt(0) == t.charAt(0) ? 1: 0;

        for(int row = 1; row < s.length(); row++){
            dp[row][0] = dp[row - 1][0] + (s.charAt(row) == t.charAt(0) ? 1 : 0);
        }

        for(int row = 1; row < dp.length; row++){
            for(int col = 1; col < dp[0].length; col++){
                dp[row][col] = dp[row - 1][col] +
                        (s.charAt(row) == t.charAt(col) ? dp[row - 1][col - 1] : 0);
            }
        }

        return dp[s.length() - 1][t.length() - 1];
    }
}
