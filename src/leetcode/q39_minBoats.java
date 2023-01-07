package leetcode;

import java.util.Arrays;

/**
 * Description:
 * Creator: levin
 * Date: 11/22/2022
 * Time: 3:36 PM
 * Email: levinforward@163.com
 */
public class q39_minBoats {

    public static int getMinBoats(int[] weights, int limit){
        if(weights == null || weights.length == 0){
            return -1;
        }

        Arrays.sort(weights);

        if(weights[weights.length - 1] > limit){
            return -1;
        }


        int i = 0;
        while (weights[i] <= limit / 2){
            i++;
        }
        i -= 1;

        if(i == -1){
            return weights.length;
        }

        int L = i;
        int R = i + 1;

        int leftSkippedNum = 0;
        while ( L >= 0 && R < weights.length){
            while( L >= 0 && weights[L] + weights[R] > limit){
                L--;
                leftSkippedNum++;
            }
            if(L == -1){
                break;
            }

            int steps = 0;
            while (R < weights.length && weights[L] + weights[R] <= limit){
                R++;
                steps++;
            }

            L = Math.max(0, L - steps);

        }

        int pairBoatsNum = leftSkippedNum;
        int unpairedLessThanLimitNum = i + 1 - pairBoatsNum;
        int unpairedMoreThanLimitNum = weights.length - i - 1 - pairBoatsNum;

        return pairBoatsNum + ((unpairedLessThanLimitNum + 1) / 2) + unpairedMoreThanLimitNum;
    };


    public static int getMinBoats2(int[] weights, int limit){
        if(weights == null || weights.length == 0){
            return -1;
        }

        Arrays.sort(weights);

        if(weights[weights.length - 1] > limit){
            return -1;
        }

        int lessR = -1;
        for(int i = weights.length - 1; i >= 0; i--){
            if(weights[i] <= limit / 2){
                lessR = i;
                break;
            }
        }
        if(lessR == -1){
            return -1;
        }

        int L = lessR;
        int R = lessR + 1;

        int noUsed = 0;
        while (L >= 0){
            int solved = 0;
            while (R < weights.length && weights[L] + weights[R] <= limit){
                R++;
                solved++;
            }
            if(solved == 0){
                L--;
                noUsed++;
            }else {
                L = Math.max(-1, L - solved);
            }
        }

        int lessAll = lessR + 1;
        int lessUsed = lessAll - noUsed;
        int moreUnsolved = weights.length - lessAll - lessUsed;

        return lessUsed + ((noUsed + 1) >> 1) + moreUnsolved;
    }


    public static int getMinBoat3(int[] weights, int limit){
        if(weights == null || weights.length == 0){
            return -1;
        }

        Arrays.sort(weights);

        if(weights[weights.length - 1] > limit){
            return -1;
        }

        int L = 0;
        int R = weights.length - 1;

        int unusedMoreThanLimited = 0;
        int unusedLessThanLimited = 0;
        int pairsNum = 0;

        boolean hasSingleMid = false;

        while (L <= R){
            while ( L < weights.length && R >= 0 && weights[L] + weights[R] > limit){
                R--;
                if(weights[R] > limit / 2){
                    unusedMoreThanLimited++;
                }else {
                    unusedLessThanLimited++;
                }
            }
            if(weights[L] + weights[R] <= limit){
                pairsNum++;
            }
            L++;
            R--;
            if(L - R == -2){
                hasSingleMid = true;
            }
        }

        int pairsNum2 = 0;
        if(hasSingleMid){
            // 注意后面的（），不然会先计算1+再进行移位
            pairsNum2 = 1 + ((weights.length - unusedLessThanLimited - unusedMoreThanLimited - 1) >> 1);
        }else{
            pairsNum2 = (weights.length - unusedLessThanLimited - unusedMoreThanLimited) >> 1;
        }

        int pairNum3 = (weights.length - unusedLessThanLimited - unusedMoreThanLimited + 1) >> 1;

        // 三个方法计算的结果是一样的
        System.out.println("pair num 1:" + pairsNum);
        System.out.println("pair num 2:" + pairsNum2);
        System.out.println("pair num 3:" + pairNum3);

        return pairsNum + ((unusedLessThanLimited + 1) >> 1) + unusedLessThanLimited;
    }

    public static int getMinBoat4(int[] weights, int limit){
        if(weights == null || weights.length == 0){
            return -1;
        }

        Arrays.sort(weights);

        if(weights[weights.length - 1] > limit){
            return -1;
        }

        int lessR = -1;
        for(int i = weights.length - 1; i >= 0; i--){
            if(weights[i] <= limit / 2){
                lessR = i;
                break;
            }
        }

        if(lessR == -1){
            return -1;
        }

        int L = lessR;
        int R = lessR + 1;
        int leftUnused = 0;
        int pairsNum = 0;

        while (L >= 0 && R < weights.length){
            while (L >= 0 && (weights[L] + weights[R]) > limit){
                L--;
                leftUnused++;
            }
            if(L >= 0){
                pairsNum++;
                L--;
                R++;
            }
        }

        int rightUnused = weights.length - lessR - 1 - pairsNum;
        return pairsNum + ((leftUnused + 1) >> 1) + rightUnused;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 5};
        int limit = 6;
        System.out.println(getMinBoats2(weights, limit));
        System.out.println(getMinBoats(weights, limit));
        System.out.println(getMinBoat3(weights, limit));
        System.out.println(getMinBoat4(weights, limit));
    }
}
