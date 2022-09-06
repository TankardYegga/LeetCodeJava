package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 8/17/2022
 * Time: 11:22 AM
 * Email: levinforward@163.com
 */
public class q4 {
    public static int countNumOfDifferentColors(String str, String newStr){
        int numOfDifferentColors = 0;
        System.out.println("str is:" + str);
        System.out.println("new str is:" + newStr);
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != newStr.charAt(i)){
                numOfDifferentColors++;
            }
        }
        System.out.println("different number of colors:" + numOfDifferentColors);
        return numOfDifferentColors;
    }

    public static int minPaintTimes(String str){

        if(str == null || str.length() < 2){
            return 0;
        }

        char[] paints = str.toCharArray();

        int minPaints = Integer.MAX_VALUE;
        for(int i = 0; i <= paints.length; i++){
            String newStr = "";
            for(int j = 0; j < paints.length; j++){
                if(j < i){
                    newStr += 'R';
                }else{
                    newStr += 'G';
                }
            }
            minPaints = Math.min(minPaints, countNumOfDifferentColors(str, newStr));
        }
        return minPaints;
    }

    public static int minPaintTimes2(String str){

        if(str == null || str.length() < 2){
            return 0;
        }

        char[] chs = str.toCharArray();
        int[] countG = new int[str.length()];

        countG[0] = (chs[0] == 'G') ? 1: 0;

        for(int i = 1; i < chs.length; i++){
            if(chs[i] != 'G'){
                countG[i] = countG[i - 1];
            }else{
                countG[i] = countG[i - 1] + 1;
            }
        }

        int countR = 0;
        int minPaints = Integer.MAX_VALUE;

        for(int j = chs.length; j >= 0; j--){
            if(j == chs.length){
                minPaints = Math.min(minPaints, countG[chs.length - 1]);
                continue;
            }

            if(j == 0){
                minPaints = Math.min(minPaints, countR);
                continue;
            }

            if(chs[j] == 'R'){
                countR++;
            }

            minPaints = Math.min(minPaints, countR + countG[j - 1]);
        }

        return minPaints;
    }

    public static int minPaintTimes3(String s){
        if(s == null || s.length() < 2){
            return 0;
        }

        char[] chs = s.toCharArray();
        int[] countR = new int[chs.length];

        countR[chs.length - 1] = chs[chs.length - 1] == 'R' ? 1: 0;

        for(int i = chs.length - 2; i >= 0; i--){
            if(chs[i] == 'R'){
                countR[i] = countR[i + 1] + 1;
            }else {
                countR[i] = countR[i + 1];
            }
        }

        int minPaints = Integer.MAX_VALUE;
        int countG = 0;

        for(int j = 0; j <= chs.length; j++){
            if(j == 0){
                minPaints = Math.min(minPaints, countR[0]);
                continue;
            }

            if(chs[j - 1] == 'G'){
                countG++;
            }

            if(j != chs.length){
                minPaints = Math.min(minPaints, countG + countR[j]);
            }else{
                minPaints = Math.min(minPaints, countG);
            }
        }

        return minPaints;
    }


    public static void main(String[] args){
        String str = "RGRGR";
//        String str = "GRRRGGG";
        System.out.println(minPaintTimes(str));
        System.out.println(minPaintTimes2(str));
        System.out.println(minPaintTimes3(str));
    }
}
