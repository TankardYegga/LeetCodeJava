package leetcode;

import java.util.HashSet;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/12/2022
 * Time: 5:39 PM
 * Email: levinforward@163.com
 */
public interface q30_combinationWays {

    public static int getCombinationWays(String str, String[] arr){

        if(str == null || str.length() == 0 || arr == null || arr.length == 0){
            return 0;
        }

        //对arr里面的字符串进行去重
        HashSet<String> set = new HashSet<>();

        for(int i = 0; i < arr.length; i++){
            set.add(arr[i]);
        }

        return process(str, set, 0);
    }

    //返回从i位置开始到字符串末尾这一段的组合数目
    public static int process(String str, HashSet<String> set, int i){

        if(i == str.length()){
            return 1;
        }

        int waysNum = 0;

        for(int end = i; i < str.length(); i++){
            String predixStr = str.substring(i, end + 1);
            if(set.contains(predixStr)){
                
            }
        }
    }

}
