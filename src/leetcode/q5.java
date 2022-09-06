package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 8/17/2022
 * Time: 10:28 PM
 * Email: levinforward@163.com
 */
public class q5 {
    // 获取矩阵所能构成的所有正方形的个数
    public static int getNumOfSquares(int[][] matrix){
        int numOfSquares = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                int curNumOfSquares = Math.min(matrix.length - 1 - i, matrix[0].length - 1 - j);
                numOfSquares += curNumOfSquares;
            }
            System.out.println("row " + i + ":" + numOfSquares);
        }
        return numOfSquares;
    }

    // 获取矩阵所能构成的所有长方形的个数
    public static int getNumOfRecs(int[][] matrix){
        int numOfRecs = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                int rowsRemained = matrix.length - 1 - i;
                int colsRemained = matrix[0].length - 1 - j;
                if( rowsRemained > 0 && colsRemained > 0){
                    int curNumOfRecs = colsRemained * rowsRemained - Math.min(colsRemained, rowsRemained);
//                    System.out.println("row " + i + "col " + j + ":" + curNumOfRecs);
                    numOfRecs += curNumOfRecs;
                }
            }
        }
        return numOfRecs;
    }

    public static void printArray(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0 ; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("**********************************************************");
    }

    public static int maxOneBorderSquareSize(int[][] matrix){
        int[][] right = new int[matrix.length][matrix[0].length];
        int[][] bottom = new int[matrix.length][matrix[0].length];

        for(int row = 0; row < matrix.length; row++){
            right[row][matrix[0].length - 1] = matrix[row][matrix[0].length - 1];
        }
        for(int col = matrix[0].length - 2; col >= 0; col--){
            for(int row = 0; row < matrix.length; row++){
                right[row][col] = matrix[row][col] == 1 ? right[row][col + 1] + 1: 0;
            }
        }
        printArray(right);

        for(int col = 0; col < matrix[0].length; col++){
            bottom[matrix.length - 1][col] = matrix[matrix.length - 1][col];
        }
        for(int row = matrix.length - 2; row >= 0; row--){
            for(int col = 0; col < matrix[0].length; col++){
                bottom[row][col] = matrix[row][col] == 1 ? bottom[row + 1][col] + 1: 0;
            }
        }
        printArray(bottom);

        int maxSquareSize = Integer.MIN_VALUE;
        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                int possibleSquareSize = Math.max(matrix.length - row, matrix[0].length - col);
                for(int l = 2; l <= possibleSquareSize; l++){
                    if(right[row][col] >= l && bottom[row][col] >= l && right[row + l - 1][col] >= l && bottom[row][col + l - 1] >= l){
//                        System.out.println("maxPossibleSize:" + possibleSquareSize);
                        maxSquareSize = Math.max(maxSquareSize, l);
//                        System.out.println("row:" + row);
//                        System.out.println("col:" + col);
//                        System.out.println("size:" + l);
//                        System.out.println("-----------------------------------");
                    }
                }
            }
        }

        return maxSquareSize;
    }

    public static void main(String[] args){
        int[][] matrix = {
                {0,1,1,1,1,1},
                {0,1,0,0,1,1},
                {0,1,0,1,1,1},
                {0,1,0,1,1,1},
                {0,1,0,1,1,1},
                {0,0,0,0,0,0}
                         };
        System.out.println(maxOneBorderSquareSize(matrix));
        System.out.println(getNumOfSquares(matrix));
        System.out.println(getNumOfRecs(matrix));

    }
}
