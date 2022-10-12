package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/5/2022
 * Time: 9:21 AM
 * Email: levinforward@163.com
 */
public class q23_chainedMaxPath {

    public static int getChainedMaxPath(int[][] arr){
        int ans = Integer.MIN_VALUE;
        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr[0].length; col++){
                ans = Math.max(ans, process(arr, row, col));
            }
        }
        return ans;
    }

    public static int process(int[][] arr, int i, int j){
        if(i < 0 || i >= arr.length || j < 0 || j >= arr[0].length){
            return -1;
        }

        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        if(i - 1 >= 0 && arr[i - 1][j] > arr[i][j]){
            up = process(arr, i - 1, j);
        }
        if(i + 1 < arr.length && arr[i + 1][j] > arr[i][j]){
            down = process(arr, i + 1, j);
        }
        if(j - 1 >= 0 && arr[i][j - 1] > arr[i][j]){
            left = arr[i][j - 1];
        }
        if(j + 1 < arr[0].length && arr[i][j + 1] > arr[i][j]){
            right = arr[i][j + 1];
        }

        return  1 + Math.max(Math.max(up, down), Math.max(left, right));
    }

    public static int getChainedMaxPath2(int[][] arr){
        int ans = Integer.MIN_VALUE;

        int[][] dp = new int[arr.length][arr[0].length];

        for(int row = 0; row < arr.length; row++){
            for (int col = 0; col < arr[0].length; col++){
                ans = Math.max(ans, process2(arr, row, col, dp));
            }
        }

        return ans;
    }

    public static int process2(int[][] arr, int i, int j, int[][] dp){
        if(i < 0 || i > arr.length || j < 0 || j > arr[0].length){
            return -1;
        }

        if(dp[i][j] != 0){
            return dp[i][j];
        }

        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        if(i - 1 >= 0 && arr[i - 1][j] > arr[i][j]){
            up = arr[i - 1][j];
        }
        if(i + 1 < arr.length && arr[i + 1][j] > arr[i][j]){
            down = arr[i + 1][j];
        }
        if(j - 1 >= 0 && arr[i][j - 1] > arr[i][j]){
            left = arr[i][j - 1];
        }
        if(j + 1 < arr[0].length && arr[i][j + 1] > arr[i][j]){
            right = arr[i][j + 1];
        }

        dp[i][j] = 1 + Math.max(Math.max(up, down), Math.max(left, right));
        return dp[i][j];
    }
}
