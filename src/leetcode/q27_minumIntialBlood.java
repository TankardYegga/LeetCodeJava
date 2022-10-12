package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/7/2022
 * Time: 2:29 PM
 * Email: levinforward@163.com
 */
public class q27_minumIntialBlood {

    public static int getMinInitialBlood(int[][] bloodMap) {
        return process(bloodMap, bloodMap.length, bloodMap[0].length, 0, 0);
    }

    public static int process(int[][] bloodMap,
                              int N, int M,
                              int row, int col) {
        if (row == N - 1 && col == M - 1) {
            // -3  4
            // -5  6
            // 0  如果当前位置为非负的话，说明当前位置有血瓶，且血瓶的血量必然 >=0,
            // 而前面位置遗留下来的血量必然是 >=1, 加上当前血瓶的血量，最终得到的血量也必然是 >= 1
            // 2
            // 这里的返回值的含义是 走到最后一个位置时，其至少要具备的血量值
            return bloodMap[row][col] < 0 ? -bloodMap[row][col] + 1 : 1;
        }

        int rightNeedBlood = 0;
        int downNeedBlood = 0;

        //返回的是走入当前位置时 至少要具备的血量
        if (row == N - 1) {
            rightNeedBlood = process(bloodMap, N, M, row, col + 1);
            // x + 1 + bloodMap[row][col] >= rightNeedBlood
            // if rightNeedBlood - bloodMap[row][col] > 1:
            // 就说明走入当前位置时  血量必须要大于1
            // if rightNeedBlood - bloodMap[row][col] = 1:
            // 就说明走入当前位置时  血量刚好等于1 能刚刚好满足条件
            // 因为这里都是整数，不存在差为小数的情况
            // if rightNeedBlood - bloodMap[row][col] <= 0:
            // 就说明走入当前位置时  血量刚好等于1时  能充分满足条件
            return (rightNeedBlood - bloodMap[row][col] > 1) ? (rightNeedBlood - bloodMap[row][col]) : 1;
        }

        if (col == M - 1) {
            downNeedBlood = process(bloodMap, N, M, row + 1, col);
            return (downNeedBlood - bloodMap[row][col] > 1) ? (downNeedBlood - bloodMap[row][col]) : 1;
        }

        rightNeedBlood = process(bloodMap, N, M, row, col + 1);
        downNeedBlood = process(bloodMap, N, M, row + 1, col);
//        System.out.println("right:" + rightNeedBlood);
//        System.out.println("down:" + downNeedBlood);

        int minNeedBlood = Math.min(rightNeedBlood, downNeedBlood);
        return (minNeedBlood - bloodMap[row][col] > 1) ? (minNeedBlood - bloodMap[row][col]) : 1;
    }

    public static int getMinInitialBlood2(int[][] bloodMap) {
        return process2(bloodMap, bloodMap.length, bloodMap[0].length, 0, 0);
    }

    public static int process2(int[][] bloodMap,
                               int N, int M,
                               int row, int col
    ) {
        if (row == N - 1 && col == M - 1) {
            return bloodMap[row][col] >= 0 ? 1 : 1 - bloodMap[row][col];
        }

        if (row == N - 1) {
            int rightNeed = process2(bloodMap, N, M, row, col + 1);
            if (bloodMap[row][col] <= 0) {
                return -bloodMap[row][col] + rightNeed;
            } else if (bloodMap[row][col] >= rightNeed) {
                return 1;
            } else {
                return rightNeed - bloodMap[row][col];
            }
        }

        if (col == M - 1) {
            int downNeed = process2(bloodMap, N, M, row + 1, col);
            if (bloodMap[row][col] <= 0) {
                return -bloodMap[row][col] + downNeed;
            } else if (bloodMap[row][col] >= downNeed) {
                return 1;
            } else {
                return downNeed - bloodMap[row][col];
            }
        }

        int rightNeed = process2(bloodMap, N, M, row, col + 1);
        int downNeed = process2(bloodMap, N, M, row + 1, col);
//        System.out.println("right2:" + rightNeed);
//        System.out.println("down2:" + downNeed);
        int minNeed = Math.min(rightNeed, downNeed);

        if (bloodMap[row][col] <= 0) {
            return -bloodMap[row][col] + minNeed;
        } else if (bloodMap[row][col] >= minNeed) {
            return 1;
        } else {
            return minNeed - bloodMap[row][col];
        }
    }

    public static void main(String[] args) {
        int[][] bloodMap = {
                {-2, -3, 3},
                {-5, -10, 1},
                {0, 30, -5}
        };
        System.out.println(getMinInitialBlood(bloodMap));
        System.out.println(getMinInitialBlood2(bloodMap));
    }

}
