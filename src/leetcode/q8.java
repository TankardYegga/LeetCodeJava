package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/1/2022
 * Time: 10:57 PM
 * Email: levinforward@163.com
 */
public class q8 {

    //
    public static int minMoves(int[] goodsNumList){
        if(goodsNumList == null || goodsNumList.length == 0){
            return 0;
        }

        int sum = 0;
        for(int i = 0; i < goodsNumList.length; i++){
            sum += goodsNumList[i];
        }

        if( sum % goodsNumList.length != 0){
            return -1;
        }

        int avg = sum / goodsNumList.length;

        int leftSum = 0;

        int bottleneck = 0;
        for(int i = 0; i < goodsNumList.length; i++){
            leftSum += goodsNumList[i];
            int leftMoves = leftSum - i * avg;
            int rightMoves = sum - leftSum - goodsNumList[i] - (goodsNumList.length - i) * avg;
            if(leftMoves < 0 && rightMoves < 0){
                bottleneck = Math.max(bottleneck, Math.abs(leftMoves) + Math.abs(rightMoves));
            }else{
                bottleneck = Math.max(bottleneck, Math.max(Math.abs(leftMoves), Math.abs(rightMoves)));
            }
        }

        return bottleneck;
    }


    public static void main(String[] args){

    }
}
