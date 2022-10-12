package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/14/2022
 * Time: 4:57 PM
 * Email: levinforward@163.com
 */
public class q14_numberOfAddedChars {

    public static int getMinNumOfAddedChars(String s, int left, int right){
        if(left == right){
            return 0;
        }

        if(right - left == 1){
            return s.charAt(left) == s.charAt(right) ? 0: 1;
        }

        int possibleAddedCharsNum1 = getMinNumOfAddedChars(s, left, right - 1) + 1;
        int possibleAddedCharsNum2 = getMinNumOfAddedChars(s, left + 1, right) + 1;
        int possibleAddedCharsNum3 = Integer.MAX_VALUE;
        if(s.charAt(left) == s.charAt(right)){
            possibleAddedCharsNum3 = getMinNumOfAddedChars(s, left + 1, right - 1);
        }

        return Math.min(Math.min(possibleAddedCharsNum1, possibleAddedCharsNum2), possibleAddedCharsNum3);
    }

    public static int getMinNumOfAddedCharsByDp(String s){
        int[][] dp = new int[s.length()][s.length()];

        for(int i = 0; i < s.length(); i++){
            dp[i][i] = 0;
//            System.out.println(i);
            if( i < dp.length - 1) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i+1] = 0;
                }else{
                    dp[i][i] = 1;
                }
            }
        }

        for(int i = 0; i < dp.length; i++){
            for(int j = i + 2; j < dp.length; j++){
                int ans1 = dp[i+1][j] + 1;
                int ans2 = dp[i][j-1] + 1;
                int ans3 = Integer.MAX_VALUE;
                if(s.charAt(i) == s.charAt(j)){
                    ans3 = dp[i+1][j-1];
                }
                dp[i][j] = Math.min(Math.min(ans1, ans2), ans3);
            }
        }

        return dp[0][s.length() - 1];
    }

    public static void main(String[] args){
        String s = "baabacc";
        System.out.println(getMinNumOfAddedChars(s, 0, s.length() - 1));
        System.out.println(getMinNumOfAddedCharsByDp(s));
    }
}
