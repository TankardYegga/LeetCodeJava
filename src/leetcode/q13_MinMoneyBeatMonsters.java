package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/10/2022
 * Time: 10:52 PM
 * Email: levinforward@163.com
 */
public class q13_MinMoneyBeatMonsters {

    public static int beatCurMonster(int[] d, int[] p, int ability, int index){
        if(index == d.length){
            return 0;
        }

        if(ability < d[index]){
            return p[index] + beatCurMonster(d, p, ability + d[index], index + 1);
        }else{
            return Math.max(beatCurMonster(d, p, ability, index + 1),
                    p[index] + beatCurMonster(d, p, ability + d[index], index + 1));
        }
    }

    public static int minMoneyNeededToBeatAllMonsters(int[] d, int[] p){
        if(d == null || p == null || d.length == 0 || p.length == 0){
            return 0;
        }

        return beatCurMonster(d, p, 0, 0);
    }

    public static int minMoneyNeededToBeatAllMonsters2(int[] d, int[] p){
        if(d == null || p == null || d.length == 0 || p.length == 0){
            return 0;
        }

        int n = d.length;

        int priceSum = 0;
        for(int i = 0; i < p.length; i++){
            priceSum += p[i];
        }

        int[][] dp = new int[d.length][priceSum];

        // init
        for(int row = 0; row < d.length; row++){
            dp[row][0] = -1;
        }

        for(int col = 0; col < priceSum; col++) {
            if(col == p[0]){
                dp[0][col] = d[0];
            }else{
                dp[0][col] = -1;
            }
        }

        for(int row = 1; row < d.length; row++){
            for(int col = 1; col < priceSum; col++){
                int notBribeCur = dp[row - 1][col];
                int bribeCur = dp[row - 1][col - p[row]];

                int notBribeAns = -1;
                if(notBribeCur == -1){
                    notBribeAns = -1;
                }else if(notBribeCur >= d[row]){
                    notBribeAns = notBribeCur;
                }else{
                    notBribeAns = -1;
                }

                int bribeAns = -1;
                if(bribeCur == -1){
                    bribeAns = -1;
                }else{
                    bribeAns = bribeCur + d[row];
                }

                dp[row][col] = Math.max(bribeAns, notBribeAns);
            }
        }

        int ans = -1;
        for(int col = 0; col < priceSum; col++){
            if(dp[n-1][col] != -1){
                ans = dp[n-1][col];
            }
        }
        return ans;
    }

    public static int minMoneyNeededToBeatAllMonsters3(int[] d, int[] p){
        if(d == null || p == null || d.length == 0 || p.length == 0){
            return 0;
        }

        int n = d.length;

        int abilitySum = 0;
        for (int monsterTh = 0; monsterTh < n; monsterTh++){
            abilitySum += d[monsterTh];
        }

        int[][] monsterToAbility = new int[n][abilitySum];

        //init
        for (int row = 0; row < n; row++){
            monsterToAbility[row][0] = -1;
        }

        for (int col = 0; col < abilitySum; col++){
            if(col == d[0]){
                monsterToAbility[0][col] = p[0];
            }else{
                monsterToAbility[0][col] = -1;
            }
        }

        for(int row = 1; row < n; row++){
            for(int col = 1; col < abilitySum; col++){
                int notBribeCur = monsterToAbility[row - 1][col];
                int bribeCur = monsterToAbility[row - 1][col - d[row]];

                int notBrideAns = -1;
                if(col >= d[row] && notBribeCur != -1){
                    notBrideAns = monsterToAbility[row][col];
                }else{
                    notBrideAns = -1;
                }

                int bribeAns = -1;
                if(bribeCur != -1){
                    bribeAns = bribeCur + p[row];
                }else {
                    bribeAns = -1;
                }

                int curAns = Math.min(bribeAns, notBrideAns);
                monsterToAbility[row][col] = curAns;
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int col = 0; col < abilitySum; col++){
            if(monsterToAbility[n-1][col] != - 1){
                ans = Math.min(ans, monsterToAbility[n-1][col]);
            }
        }

        return ans;
    }
}
