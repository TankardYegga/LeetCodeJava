package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Creator: levin
 * Date: 10/25/2022
 * Time: 11:35 PM
 * Email: levinforward@163.com
 */
public class q33_findMoreThanHalfElem {

    public static int getMoreThanHalfElem(int[] arr){
        int times = 0;
        int candidateVal = 0;
        for(int i = 0; i < arr.length; i++){
            if(times == 0){
                times++;
                candidateVal = arr[i];
            }else if(arr[i] == candidateVal){
                times++;
            }else {
                times--;
            }
        }

        if(times == 0){
            return -1;
        }

        times = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == candidateVal){
                times++;
            }
        }

        if(times > arr.length / 2){
            return candidateVal;
        }else {
            return -1;
        }
    }


    //获得K个次数大于num.length / K 的数
    public static void printKMajor(int[] arr, int K){
        if(K < 2){
            System.out.println(K + "is not a invalid number");
            return;
        }

        HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>();
        //遍历一遍数组，统计次数最多的K个数（遍历的过程中会动态排查是否满足次数的这个条件，
        // 所以hashmap中最终剩余的元素）
        for(int i = 0; i < arr.length; i++){
            if(cands.containsKey(arr[i])){
                cands.put(arr[i], cands.get(arr[i]) + 1);
            }else{
                if(cands.size() == K){
                    allCandsMinusOne(cands);
                }else{
                    cands.put(arr[i], 1);
                }
            }
        }

        //计算已计算得到的HashMap中所有key在原数组中真正出现的次数
        HashMap<Integer, Integer> realOccurenceNum = getRealOccurenceNum(arr, cands);
        boolean isReallySatisfied = false;

        for(Map.Entry<Integer, Integer> entry: realOccurenceNum.entrySet()){
            Integer entryKey = entry.getKey();
            if(realOccurenceNum.get(entryKey) > arr.length / K){
                isReallySatisfied = true;
                System.out.println(entryKey + ",");
            }
        }

        System.out.println( isReallySatisfied == true ? "" : "no numbers");
    }

    public static HashMap<Integer, Integer> getRealOccurenceNum(int[] arr, HashMap<Integer, Integer> cands){
        HashMap<Integer, Integer> realOccurenceNum = new HashMap<Integer, Integer>();
        for(int i = 0; i < arr.length; i++){
            if(cands.containsKey(arr[i])){
                if(!realOccurenceNum.containsKey(arr[i])){
                    realOccurenceNum.put(arr[i], 1);
                }else{
                    realOccurenceNum.put(arr[i], realOccurenceNum.get(arr[i]) + 1);
                }
            }
        }
        return realOccurenceNum;
    }

    public static void allCandsMinusOne(HashMap<Integer, Integer> cands){
        for(Map.Entry<Integer, Integer> set: cands.entrySet()){
            cands.put(set.getKey(), set.getValue() - 1);
        }
    }
}
