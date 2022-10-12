package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/2/2022
 * Time: 2:41 PM
 * Email: levinforward@163.com
 */
public class q10 {

    public static int largestComponentSize1(int[] arr){
        UnionFindSet set = new UnionFindSet(arr);
        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(gcd(i, j) != 1){
                    set.union(i, j);
                }
            }
        }
        return set.maxSize();
    }

    public static int largestComponentSize2(int[] arr){
        UnionFindSet set = new UnionFindSet(arr.length);

        HashMap<Integer, Integer> factorsMap = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int n = arr[i];
            int limit = (int) Math.sqrt(n);
            for(int j = 1; j <= limit; j++){
                if(n % j == 0){
                    if(!factorsMap.containsKey(j)){
                        factorsMap.put(j, i);
                    }else{
                        set.union(factorsMap.get(j), i);
                    }
                    int other = n / j;
                    if(!factorsMap.containsKey(other)){
                        factorsMap.put(other, i);
                    }else{
                        set.union(factorsMap.get(other), i);
                    }
                }
            }
        }
        return set.maxSize();
    }

    public static int gcd(int a, int b){
        return b == 0 ? a: gcd(b, a % b);
    }

    public static class UnionFindSet{
        public Map<Integer, Integer> parentMap;
        public Map<Integer, Integer> sizeMap;

        public UnionFindSet(int[] arr){
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for(int i = 0; i < arr.length; i++){
                parentMap.put(i, i);
                sizeMap.put(i, 1);
            }
        }

        // 以数组的下标作为索引
        public UnionFindSet(int size){
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for(int i = 0; i < size; i++){
                parentMap.put(i, i);
                sizeMap.put(i, 1);
            }
        }

        public int maxSize(){
            return sizeMap.size();
        }

        public int findParent(int x){
            Stack<Integer> s = new Stack<>();
            if(x != parentMap.get(x)){
                s.push(x);
                x = parentMap.get(x);
            }
            while (!s.isEmpty()){
                parentMap.put(s.pop(), x);
            }
            return x;
        }

        public boolean isSameSet(int a, int b){
            int aHead = findParent(a);
            int bHead = findParent(b);

            return aHead == bHead;
        }

        public void union(int a, int b){
            int aHead = findParent(a);
            int bHead = findParent(b);
            if(aHead == bHead){
                return;
            }

            int aSize = sizeMap.get(aHead);
            int bSize = sizeMap.get(bHead);

            int larger = aSize >= bSize ? aHead: bHead;
            int smaller = larger == aHead ? bHead: aHead;

            parentMap.put(smaller, larger);
            sizeMap.put(larger, sizeMap.get(smaller) + sizeMap.get(larger));
            sizeMap.remove(smaller);
        }
    }


}
