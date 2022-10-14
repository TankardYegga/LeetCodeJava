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
                waysNum += process(str, set, end + 1);
            }
        }

        return waysNum;
    }

    //上述方法中遍历过程的复杂度是O(N^2),
    // 而每次判断前缀是否存在于单词列表的复杂度是O(1)
    // 但是每次获取子串相当于是将对应区间范围进行了一份拷贝
    // 所以考虑这部分由函数库辅助实现的子串功能
    // 实际判断前缀是否存在于单词列表的复杂度是O(N)
    // 所以实际的总复杂度是O(N^3)

    //利用前缀树可以将这个过程缩短至O(1)
    public class PrefixTreeNode{
        public PrefixTreeNode[] next;
        public boolean end;

        public PrefixTreeNode(){
            next = new PrefixTreeNode[26];
            end = false;
        }
    }

    public static int getCombinationWaysWithPrefixTree(String str, String[] arr){

        if(str == null || str.length() == 0 || arr == null || arr.length == 0){
            return 0;
        }

        //构造前缀树
        //前缀树本身就具有去重的功能
        PrefixTreeNode head = new PrefixTreeNode();
        for(String wordStr: arr){
            PrefixTreeNode cur = head;
            for(int i = 0; i < wordStr.length(); i++){
                char ch = wordStr.charAt(i);
                int chIndex = ch - 'a';
                if(cur.next[chIndex] == null){
                    cur.next[chIndex] = new PrefixTreeNode();
                }
                cur = cur.next[chIndex];
            }
            cur.end = true;
        }

        //利用构造好的前缀树来进行递归
        return process2(str, head, 0);
    }

    public static int process2(String str, PrefixTreeNode head, int i){

        if(i == str.length() - 1){
            return 1;
        }

        PrefixTreeNode cur = head;
        int waysNum = 0;

        for(int end = i; end < str.length(); i++){
            int charIdx = str.charAt(end) - 'a';
            if(cur.next[charIdx] == null){
                break;
            }
            cur = cur.next[charIdx];
            if(cur.end){
                waysNum += process2(str, head, end + 1);
            }
        }

        return waysNum;
    }
}
