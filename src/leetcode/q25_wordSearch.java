package leetcode;

import java.util.*;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/5/2022
 * Time: 2:46 PM
 * Email: levinforward@163.com
 */
public class q25_wordSearch {

    public static class TriNode{
        public TriNode[] nexts;
        public int pass; //通过该结点的字符串的数目或者说有多少个字符串包含该结点对应的字符
        public int end; //以该结点作为结尾的字符串的数目或者说有多少个字符串是以该结点对应的字符结尾的

        public TriNode(){
            nexts = new TriNode[26];
            pass = 0;
            end = 0;
        }
    }

    //将word添加到已有的前缀树中去
    public static void fillWordToPrefixTree(TriNode node, String word){
        node.pass++;
        char[] chars = word.toCharArray();
        for(int i = 0; i < chars.length; i++){
            int index = chars[i] - 'a';
            if(node.nexts[index] == null){
                node.nexts[index] = new TriNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public static List<String> findWords(char[][] board, String[] words){
        //依据给定的words列表来构造一棵前缀树
        //words列表当中可能有相同的重复单词，构造前缀树时需要进行去重
        TriNode head = new TriNode();

        HashSet<String> set = new HashSet<>();
        for(String word: words){
            if(!set.contains(word)){
                fillWordToPrefixTree(head, word);
                set.add(word);
            }
        }

        //对于board上的每一个位置去判断其能够找到的单词, 每个位置收集到的单词都扔进ans里面
        //使用list来收集所有能够找到的单词
        //使用linkedlist来记录搜索过程中的路径
        List<String> ans = new ArrayList<>();
        LinkedList<Character> path = new LinkedList<>();

        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){

            }
        }
        return ans;
    }

    public static String generatePathStr(LinkedList<Character> path){
        char[] chars = new char[path.size()];
        for(int i = 0; i < path.size(); i++){
            chars[i] = path.get(i);
        }
        String pathStr = String.copyValueOf(chars);
        return pathStr;
    }

    //row,col表示当前所考虑的矩阵位置
    //path表示目前已经行走过的路径所收集到的字符
    //cur表示目前已经行走过的路径与前缀树所对应的路径的最后一个结点
    //ans用于存储收集到的words中的单词
    public static int process(char[][] board,
                               int row, int col,
                               LinkedList<Character> path,
                               List<String> ans,
                               TriNode cur){

        //首先判断当前字符是否在cur结点的后缀字符集合中
        char curChar = board[row][col];
        if(curChar == 0){
            return 0;
        }
        int index = curChar - 'a';

        if(cur.nexts[index] == null || cur.nexts[index].pass == 0){
            return 0;
        }

        path.addLast(curChar);

        TriNode curNext = cur.nexts[index];

        // 如果当前结点在前缀树中且对应的单词目前没有被完全匹配到
        // 先判断当前的序列是否已经能够形成words列表中的一个单词了

        int fix = 0;
        // 因为这里的前缀树中是没有重复的单词的
        // 所以每个字符的end要么为0要么为1
        if(curNext.end == 1){
            fix += 1;
            String word = generatePathStr(path);
            ans.add(word);
            curNext.end = 0;
        }

        //将当前位置的字符设置为
        board[row][col] = 0;

        if(row - 1 >= 0){
            fix += process(board, row - 1, col, path, ans, cur);
        }
        if(row + 1 < board.length){
            fix += process(board, row + 1, col, path, ans, cur);
        }
        if(col - 1 >= 0){
            fix += process(board, row, col - 1, path, ans, cur);
        }
        if(col + 1 < board[0].length){
            fix += process(board, row, col + 1, path, ans, cur);
        }

        board[row][col] = curChar;
        path.pollLast();

        //如果从当前结点出发的后续所有单词（包括以当前结点结尾的单词）都被访问过了
        //那么这个结点就没有必要走下去了
        //fix统计的就是从当前结点出发的能够与words列表中的单词匹配上的单词的数目
        curNext.pass -= fix;

        // 返回：当path选择该结点之后，所能够找到的匹配的单词的数目
        return fix;
    }


    //从当前的点进行DFS搜索
    //hasSelected用于避免重复结点，也可以说是避免路径反向回溯
    public static void generateAllPathsFromGivenPosition(
            char[][] board, boolean[][] hasSelected,
            int row, int col,
            List<Character> path,
            List<String> res
    ){

        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length){

            char[] chars = new char[path.size()];
            for(int i = 0; i < path.size(); i++){
                chars[i] = path.get(i);
            }
            String s = String.valueOf(chars);
            System.out.println("Added a string:" + s);
            res.add(s);
            return;
        }


//        System.out.println("row:" + row);
//        System.out.println("col:" + col);

        if(hasSelected[row][col]){
            return;
        }

        path.add(board[row][col]);

        hasSelected[row][col] = true;

        generateAllPathsFromGivenPosition(board, hasSelected, row - 1, col, path, res);
        generateAllPathsFromGivenPosition(board, hasSelected, row + 1, col, path, res);
        generateAllPathsFromGivenPosition(board, hasSelected, row, col - 1, path, res);
        generateAllPathsFromGivenPosition(board, hasSelected, row, col + 1, path, res);

        hasSelected[row][col] = false;
        path.remove(path.size() - 1);

    }

    public static void main(String[] args){
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        boolean[][] hasSelected = new boolean[board.length][board[0].length];
        List<Character> path = new LinkedList<>();
        List<String> res = new ArrayList<>();
        generateAllPathsFromGivenPosition(board, hasSelected, 1, 1, path, res);

        List<String> newList = new LinkedList<>(new HashSet<String>(res));
        System.out.println("new List remove repeated:" + newList);

        List<String> newList2 = new LinkedList<>(new TreeSet<String>(res));
        System.out.println("new List2:" + newList2);

        List<String> newList3 = new LinkedList<>();
        for(String str: res){
            if(!newList3.contains(str)){
                newList3.add(str);
            }
        }
        System.out.println("new List3:" + newList3);
    }
}
