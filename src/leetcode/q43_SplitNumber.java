package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 12/12/2022
 * Time: 7:03 PM
 * Email: levinforward@163.com
 */
public class q43_SplitNumber {

    //求将n进行关于和的因子拆分到底有多少种不同的方法
    public static int ways(int n){
        if(n < 1){
            return 0;
        }

        return process(1, n);
    }

    public static int process(int pre, int rest){
        if(rest == 0){
            return 1;  //这说明sum已经完全被拆解完了，pre本身就是sum的原值了
        }

        // 为了避免在搜索时会找到重复值，要求sum拆解时后面的元素值一定要比前面的大
        // 所以这相当于是一个搜索的剪枝操作，这里也是搜索的一个终点方向
        if(pre > rest){
            return 0;
        }

        int waysNum = 0;
        for(int i = pre; i <= rest; i++){
            waysNum += process(i, rest - i);
        }

        return waysNum;
    }

    //使用dp过程来重写上述的递归过程
    public static int ways2(int n){
        //dp矩阵的大小：
        //行坐标：pre
        //纵坐标：res
        //pre的范围是：1-n
        //res的范围是：0-n
        //当pre=n, 则res=0，而当pre=1,res=n
        //为啥初始时要求pre为1，而不是为0呢？
        //当初始时pre=0，res=n, 那么在process函数内部就会从0-n开始遍历了
        //如果改成从pre+1开始遍历，那么当后面pre不等于0时，比如pre=1，res = n - 1时，
        //对 process(1, n - 1)进行递归时，那么就会从process(2, n - 3)到process(n-1, 0)
        //很明显会漏掉process(1, n - 2)
        //这里的pre不要理解为已经对这个数分配了多少了，而是可以理解为下一次处理的时候第一步必须分配的最小值

        if(n < 1){
            return 0;
        }

        int[][] dp = new int[n + 1][n + 1];

        for(int pre = 0; pre <= n; pre++){
            dp[pre][0] = 1;
        }

        for(int pre = 1; pre <= n; pre++){
            for(int res = 1; res < pre; res++){
                dp[pre][res] = 0;
            }
        }

        for(int pre = n; pre >= 1; pre--){
            for(int res = pre; res <= n; res++){

                int waysNum = 0;
                for(int i = pre; i <= res; i++){
                    waysNum += dp[i][res - i];
                }

                dp[pre][res] = waysNum;
            }
        }

        return dp[1][n];
    }


    //对求和过程进行时间复杂度优化
    //因为dp[i][j] = dp[i+1][j] + dp[i][j-i]
    //递归的顺序还是从下到上，从左到右
    public static int way3(int n){

        if(n < 0){
            return 0;
        }

        int[][] dp = new int[n + 1][n + 1];

        for(int pre = 1; pre <= n; pre++){
            dp[pre][0] = 1;
        }

        for(int pre = 1; pre <= n; pre++){
            for(int res = 1; res < pre; res++){
                dp[pre][res] = 0;
            }
        }

        for(int pre = n; pre >= 1; pre--){
            for(int res = pre; res <= n; res++){
                if(pre + 1 <= n){
                    dp[pre][res] = dp[pre + 1][res] + dp[pre][res - pre];
                }else{
                    dp[pre][res] = dp[pre][res - pre];
                }
            }
        }

        return dp[1][n];
    }
}
