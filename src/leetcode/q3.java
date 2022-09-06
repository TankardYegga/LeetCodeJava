package leetcode;


/**
 * Description: JVM
 * Creator: levin
 * Date: 8/16/2022
 * Time: 7:51 PM
 * Email: levinforward@163.com
 */
public class q3 {
    public static int validBracketsSubstrLength(String s){
        if(s == null || s.length() == 0){
            System.out.println("Empty String");
            return 0;
        }

        char[] ch = s.toCharArray();

        int[] dp = new int[ch.length];
        int maxLen = 0;

        for(int j = dp.length - 2; j >= 0; j--) {
            if(ch[j] == ')'){
                dp[j] = 0;
            }else {
                int cmpIndex = (j + 1 + dp[j + 1]);
                if(cmpIndex < dp.length && ')' == ch[cmpIndex]){
                    dp[j] = dp[j + 1] + 2;
                    if(cmpIndex + 1 < dp.length){
                        dp[j] += dp[cmpIndex + 1];
                    }
                }else if(cmpIndex < dp.length && '(' == ch[cmpIndex] ){
                    dp[j] = 0;
                }else{
                    continue;
                }
            }

            maxLen = Math.max(maxLen, dp[j]);
        }

        return maxLen;
    }

    public static void main(String[] args){
//        String s = "(())((()((";
        String s = "()()(())()()";
        System.out.println("MaxValidLen:" + validBracketsSubstrLength(s));
    }
}
