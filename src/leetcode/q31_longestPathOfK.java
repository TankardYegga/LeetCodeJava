package leetcode;

//import java_basic.webserver.Mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/20/2022
 * Time: 11:27 AM
 * Email: levinforward@163.com
 */
public class q31_longestPathOfK {

    static class Node{
        int value;
        Node left;
        Node right;

//        public Node(){
//            value = 0;
//            left = null;
//            right = null;
//        }

        public Node(int v){
            value = v;
        }
    }

    public int getLongestPathOfK(Node head, int K){
        return process(head, K);
    }

    //这个代码写的不对
    //[1]问题主要在于包含当前结点的分支结果的计算
    // 因为此处你的写法不能够保证：
    // process(node.left, K - node.value) 就是从左子树的根结点也就是左孩子出发的最长路径
    // 所以要怎么进行改写呢？
    //[2]这里的递归终止条件是叶子结点还是空结点呢？
    // 应该都行，如果是空结点，则递归层次数会多1而已;
    public int process(Node node, int K){
        if(node.right == null && node.left == null){
            return node.value == K ? 1: 0;
        }

        int nodeNums1 = Integer.MIN_VALUE;
        if(node.left != null){
            nodeNums1 = process(node.left, K);
        }

        int nodeNum2 = Integer.MIN_VALUE;
        if(node.left != null){
            nodeNum2 = process(node.left, K - node.value) + 1;
        }

        int nodeNum3 = Integer.MIN_VALUE;
        if(node.right != null){
            nodeNum3 = process(node.right, K);
        }

        int nodeNum4 = Integer.MIN_VALUE;
        if(node.right != null){
            nodeNum4 = process(node.right, K - node.value) + 1;
        }

        return Math.max(Math.max(nodeNums1, nodeNum2), Math.max(nodeNum3, nodeNum4));
    }


    public static int ans = 0; // 用于存储最终结果，即最多结点数

    public static int getLongestPathWithKSum(Node node, int K){

        Map<Integer, Integer> sumMap = new HashMap<>(); //key存放路径的前缀和，value存放到达此路径的最短level
        sumMap.put(0, -1);
        process2(node, 0, 0, K, sumMap);

        return ans;
    }

    public static void process2(Node node, int level, int preSum, int K, Map<Integer, Integer> map){
        if(node != null){
            int allSum = preSum + node.value;
            if(map.containsKey(allSum - K)){
                ans = Math.max(ans, level - map.get(allSum - K));
            }
            if(!map.containsKey(allSum)){
                map.put(allSum, level);
            }
            process2(node.left, level + 1, allSum, K, map);
            process2(node.right, level + 1, allSum, K, map);
            if(map.containsKey(allSum)){
                map.remove(allSum);
            }
        }
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.left = new Node(1);
        head.right = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(2);
        head.left.right.right = new Node(1);
        int ans = getLongestPathWithKSum(head, 6);
        System.out.println("ans:" + ans);
    }
}
