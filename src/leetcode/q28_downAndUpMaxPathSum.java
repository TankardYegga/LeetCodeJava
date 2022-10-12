package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/11/2022
 * Time: 4:20 PM
 * Email: levinforward@163.com
 */
public class q28_downAndUpMaxPathSum {

    public static int getMaxDownAndUpPathSum(int[][] matrix){
        return process(matrix, 0, 0, 0);
    }

    public static int process(int[][] matrix,
                              int aRow, int aCol,
                              int bRow){
        int N = matrix.length;
        int M = matrix[0].length;

        int bCol = aRow + aCol - bRow;

        if(aRow == N - 1 && aCol == M - 1){
            return matrix[aRow][aCol];
        }

        int aDownbDown = Integer.MIN_VALUE;
        if(aRow + 1 < N && bRow + 1 < N){
            aDownbDown = process(matrix, aRow + 1, aCol, bRow + 1);
        }

        int aDownbRight = Integer.MIN_VALUE;
        if(aRow + 1 < N && bCol + 1 < M){
            aDownbRight = process(matrix, aRow + 1, aCol, bRow);
        }

        int aRightbDown = Integer.MIN_VALUE;
        if(aCol + 1 < M && bRow + 1 < N){
            aRightbDown = process(matrix,  aRow, aCol + 1, bRow + 1);
        }

        int aRightbRight = Integer.MIN_VALUE;
        if (aCol + 1 < M && bCol + 1 < M){
            aRightbRight = process(matrix, aRow, aCol + 1, bRow);
        }

        int nextMax = Math.max(Math.max(aDownbDown, aDownbRight), Math.max(aRightbDown, aRightbRight));
        // 判断a和b是否在同一位置
        if(aRow == bRow){
            return matrix[aRow][aCol] + nextMax;
        }

        return matrix[aRow][aCol] + matrix[bRow][bCol] + nextMax;
    }

    public static int getMaxDownAndUpPathSumUsingDp(int[][] mat){
        int[][][] dp = new int[mat.length][mat[0].length][mat.length];

        return process2(mat, dp, 0, 0, 0);
    }


    public static int process2(int[][] mat, int[][][] dp, int x, int y, int z){

        int N = mat.length;
        int M = mat[0].length;

//        System.out.println("x:" + x);
//        System.out.println("y:" + y);
//        System.out.println("z:" + z);

        if(dp[x][y][z] != 0){
            return dp[x][y][z];
        }

        if(x == N - 1 && y == M - 1){
            dp[x][y][z] = mat[x][y];
            return dp[x][y][z];
        }

        int w = x + y - z;

        int aDownBDown = Integer.MIN_VALUE;
        if(x + 1 < N && z + 1 < N){
            aDownBDown = process2(mat, dp, x + 1, y, z + 1);
        }

        int aDownBRight = Integer.MIN_VALUE;
        if(x + 1 < N && w + 1 < M){
            aDownBRight = process2(mat, dp, x + 1, y, z);
        }

        int aRightBDown = Integer.MIN_VALUE;
        if(y + 1 < M && z + 1 < N){
            aRightBDown = process2(mat, dp, x, y + 1, z + 1);
        }

        int aRightBRight = Integer.MIN_VALUE;
        if(y + 1 < M && w + 1 < M){
            aRightBRight = process2(mat, dp, x, y + 1, z);
        }

        int nextMax = Math.max(Math.max(aDownBDown, aDownBRight), Math.max(aRightBDown, aRightBRight));

        if(x == z){
            return mat[x][y] + nextMax;
        }

        return mat[x][y] + mat[z][w] + nextMax;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {1, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 1}
        };
        System.out.println(getMaxDownAndUpPathSum(mat));
        System.out.println(getMaxDownAndUpPathSumUsingDp(mat));
    }
}
