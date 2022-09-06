package leetcode;

import java.util.Stack;

/**
 * Description: JVM
 * Creator: levin
 * Date: 8/30/2022
 * Time: 8:26 PM
 * Email: levinforward@163.com
 */
public class q7 {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int val){
            value = val;
        }
    }

    public static int maxPathSum = Integer.MIN_VALUE;

    public static int findMaxPathSum(Node head){
        p(head, 0);
        return maxPathSum;
    }

    //先序遍历并求路径和
    public static void p(Node x, int pre){
        if(x.left == null && x.right == null){
            maxPathSum = Math.max(maxPathSum, pre + x.value);
        }

        if(x.left != null){
            p(x.left, pre + x.value);
        }

        if(x.right != null){
            p(x.right, pre + x.value);
        }
    }

    //左右分支分别求最大路径和，然后再与当前节点汇合后进行比较
    public static int findMaxPathSum2(Node head){

        if(head == null){
            return 0;
        }

        return processNoNullHead(head);
    }

    public static int processNoNullHead(Node x){
        if(x.left == null & x.right == null){
            return x.value;
        }

        int maxPathSum = Integer.MIN_VALUE;

        if(x.left != null){
            maxPathSum = Math.max(maxPathSum, findMaxPathSum2(x.left));
        }

        if(x.right != null){
            maxPathSum = Math.max(maxPathSum, findMaxPathSum2(x.right));
        }

        return maxPathSum + x.value;
    }

    public static Stack<Integer> s = new Stack<Integer>();
    public static int maxPathSumByStack = Integer.MIN_VALUE;

    public static int getStackSum(Stack<Integer> s){
        Stack<Integer> sCopy = (Stack<Integer>) s.clone();
        int sum = 0;
        while(sCopy.size() > 0){
            sum += sCopy.pop();
        }
        return sum;
    }

    public static int findMaxPathSum3(Node head){

        if(head == null){
            return 0;
        }

        findMaxPathSumByStack(head);
        return maxPathSumByStack;
    }

    public static void findMaxPathSumByStack(Node x){
        if(x.left == null && x.right == null){
            maxPathSumByStack = Math.max(maxPathSumByStack, getStackSum(s)+ x.value);
        }

        if(x.left != null){
            s.push(x.value);
            findMaxPathSumByStack(x.left);
            s.pop();
        }

        if(x.right != null){
            s.push(x.value);
            findMaxPathSumByStack(x.right);
            s.pop();
        }
    }


    public static int accumulationVariableStack = 0;
    public static int maxPathSumByAccuStack = Integer.MIN_VALUE;

    public static int findMaxPathSum4(Node head){

        if(head == null){
            return 0;
        }

        findMaxPathSumByAccumulationVariableStack(head);
        return maxPathSumByAccuStack;
    }

    public static void findMaxPathSumByAccumulationVariableStack(Node x){
        if(x.left == null && x.right == null){
            maxPathSumByAccuStack = Math.max(maxPathSumByAccuStack, accumulationVariableStack + x.value);
        }

        if(x.left != null){
            accumulationVariableStack += x.value;
            findMaxPathSumByAccumulationVariableStack(x.left);
            accumulationVariableStack -= x.value;
        }

        if(x.right != null){
            accumulationVariableStack += x.value;
            findMaxPathSumByAccumulationVariableStack(x.right);
            accumulationVariableStack -= x.value;
        }
    }


    //单纯的先序遍历
    public static void preOrder(Node x){
        if(x != null){
            System.out.println(x.value);
        }

        if(x.left != null){
            preOrder(x.left);
        }

        if(x.right != null){
            preOrder(x.right);
        }
    }

    // 单纯的中序遍历
    public static void inOrder(Node x){
        if(x != null && x.left != null){
            inOrder(x.left);
        }

        if(x != null){
            System.out.println(x.value);
        }

        if(x != null && x.right != null){
            inOrder(x.right);
        }
    }

    // 单纯的后序号遍历
    public static void postOrder(Node x){

        if(x != null && x.left != null){
            postOrder(x.left);
        }

        if(x != null && x.right != null){
            postOrder(x.right);
        }

        if(x != null){
            System.out.println(x.value);
        }
    }

    //////////////////////////////////////////////////////////////////////////
    //   路径并不一定是沿着根结点 到  叶子结点
    //   而是从任意结点  向下  到达任意结点
    //////////////////////////////////////////////////////////////////////////
    public static class Info{
        public int allTreeMaxSum;
        public int fromHeadMaxSum;

        public Info(int all, int from){
            allTreeMaxSum = all;
            fromHeadMaxSum = from;
        }
    }

    public static int findMaxDownPathNum(Node head){
        if(head == null){
            return 0;
        }
        return findMaxDownPath(head).allTreeMaxSum;
    }

    public static Info findMaxDownPath(Node x){
        if(x == null){
            return null;
        }

        Info leftInfo = findMaxDownPath(x.left);
        Info rightInfo = findMaxDownPath(x.right);

        int p1 = Integer.MIN_VALUE;
        if(leftInfo != null){
            p1 = leftInfo.allTreeMaxSum;
        }

        int p2 = Integer.MIN_VALUE;
        if(rightInfo != null){
            p2 = rightInfo.allTreeMaxSum;
        }

        int p3 = x.value;

        int p4 = Integer.MIN_VALUE;
        if(leftInfo != null){
            p4 = x.value + leftInfo.fromHeadMaxSum;
        }

        int p5 = Integer.MIN_VALUE;
        if(rightInfo != null){
            p5 = x.value + rightInfo.fromHeadMaxSum;
        }

        int allTreeMaxSum = Math.max(Math.max(Math.max(Math.max(p1, p2), p3), p4), p5);
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);

        Info f = new Info(allTreeMaxSum, fromHeadMaxSum);

        return f;
    }

    //////////////////////////////////////////////////////////////////////
    ///
    ///  开始结点固定为根结点，但是结束结点可以是任意的
    /////////////////////////////////////////////////////////////////////
    public static int findMaxSumPathFromRoot(Node head){
        if(head == null){
            return 0;
        }
        searchMaxSumPathFromRoot(head, 0);
        return maxSumPathFromRoot;
    }

    public static int maxSumPathFromRoot = Integer.MIN_VALUE;

    public static void searchMaxSumPathFromRoot(Node x, int sum){

        if(x != null){
            sum += x.value;
            maxSumPathFromRoot = Math.max(maxSumPathFromRoot, sum);
        }

        if(x.left != null){
            searchMaxSumPathFromRoot(x.left, sum);
        }

        if(x.right != null){
            searchMaxSumPathFromRoot(x.right, sum);
        }
    }

    ///////////////////////////////////////////////////////////////////
    //
    //  任意节点开始、任意节点结束、方向任意
    //////////////////////////////////////////////////////////////////
    public static class PathInfo{
        public int fromHeadMaxSum;
        public int allTreeMaxSum;

        public PathInfo(int from, int all){
            fromHeadMaxSum = from;
            allTreeMaxSum = all;
        }
    }

    public static int findMaxRandomPathSum(Node head){
        if(head == null){
            return 0;
        }
        return findMaxRandomPath(head).allTreeMaxSum;
    }

    public static PathInfo findMaxRandomPath(Node x){
        if(x == null){
            return null;
        }

        PathInfo leftPathInfo = findMaxRandomPath(x.left);
        PathInfo rightPathInfo = findMaxRandomPath(x.right);

        int p1 = Integer.MIN_VALUE;
        if(leftPathInfo != null){
            p1 = leftPathInfo.allTreeMaxSum;
        }

        int p2 = Integer.MIN_VALUE;
        if(rightPathInfo != null){
            p2 = rightPathInfo.allTreeMaxSum;
        }

        int p3 = x.value;
        int p4 = x.value;
        int p5 = x.value;
        int p6 = x.value;

        if(leftPathInfo != null){
            p6 += leftPathInfo.fromHeadMaxSum;
            p4 += leftPathInfo.fromHeadMaxSum;
        }
        if(rightPathInfo != null){
            p6 += rightPathInfo.fromHeadMaxSum;
            p5 += rightPathInfo.fromHeadMaxSum;
        }

        int allTreeMaxSum = Math.max(Math.max(Math.max(Math.max(Math.max(p1, p2), p3), p4),p5),p6);
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);

        PathInfo pi = new PathInfo(fromHeadMaxSum, allTreeMaxSum);

        return pi;
    }

    //写法稍微不一样，但是结果是一样的
    public static int findMaxRandomPathSum2(Node head){
        if(head == null){
            return 0;
        }
        return findMaxRandomPath2(head).allTreeMaxSum;
    }

    public static PathInfo findMaxRandomPath2(Node x){
        if(x == null){
            return null;
        }

        PathInfo leftPathInfo = findMaxRandomPath(x.left);
        PathInfo rightPathInfo = findMaxRandomPath(x.right);

        int p1 = Integer.MIN_VALUE;
        if(leftPathInfo != null){
            p1 = leftPathInfo.allTreeMaxSum;
        }

        int p2 = Integer.MIN_VALUE;
        if(rightPathInfo != null){
            p2 = rightPathInfo.allTreeMaxSum;
        }

        int p3 = x.value;

        int p4 = Integer.MIN_VALUE;
        if(leftPathInfo != null){
            p4 = x.value + leftPathInfo.fromHeadMaxSum;
        }

        int p5 = Integer.MIN_VALUE;
        if(rightPathInfo != null){
            p5 = x.value + rightPathInfo.fromHeadMaxSum;
        }

        int p6 = Integer.MIN_VALUE;
        if(leftPathInfo != null && rightPathInfo != null){
            p6 = x.value + leftPathInfo.fromHeadMaxSum + rightPathInfo.fromHeadMaxSum;
        }

        int allTreeMaxSum = Math.max(Math.max(Math.max(Math.max(Math.max(p1, p2), p3), p4),p5),p6);
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);

        PathInfo pi = new PathInfo(fromHeadMaxSum, allTreeMaxSum);

        return pi;
    }

    //////////////////////////////////////////////////////////////////////
    ///
    ///  开始结点任意，但是结束结点必须是叶子结点
    /////////////////////////////////////////////////////////////////////
    public static int maxPathSumToLeaf = Integer.MIN_VALUE;

    public static int findMaxPathSumToLeaf(Node head){
        if(head == null){
            return 0;
        }

        findMaxPathSumFromSpecifiedNodeToLeaf(head);
        return maxPathSumToLeaf;
    }

    public static int findMaxPathSumFromSpecifiedNodeToLeaf(Node x){
        if(x == null){
            return 0;
        }

        int leftMaxPathSumFromSpecifiedNodeToLeaf = 0;
        if(x.left != null){
            leftMaxPathSumFromSpecifiedNodeToLeaf = findMaxPathSumFromSpecifiedNodeToLeaf(x.left);
        }

        int rightMaxPathSumFromSpecifiedNodeToLeaf = 0;
        if(x.right != null){
            rightMaxPathSumFromSpecifiedNodeToLeaf = findMaxPathSumFromSpecifiedNodeToLeaf(x.right);
        }

        int curMax = Math.max(leftMaxPathSumFromSpecifiedNodeToLeaf, rightMaxPathSumFromSpecifiedNodeToLeaf) + x.value;
        maxPathSumToLeaf = Math.max(maxPathSumToLeaf, curMax);
        return curMax;
    }

    public static int maxPathSumToLeaf2 = Integer.MIN_VALUE;

    public static int findMaxPathSumToLeaf2(Node head){
        if(head == null){
            return 0;
        }

        findMaxPathSumFromSpecifiedNodeToLeaf2(head);
        return maxPathSumToLeaf2;
    }

    public static int findMaxPathSumFromSpecifiedNodeToLeaf2(Node x){
        if(x.left == null && x.right == null){
//            System.out.println("x value:" + x.value);
            maxPathSumToLeaf2 = Math.max(maxPathSumToLeaf2, x.value);
            return x.value;
        }

        int leftMaxPathSumFromSpecifiedNodeToLeaf = 0;
        if(x.left != null){
            leftMaxPathSumFromSpecifiedNodeToLeaf = findMaxPathSumFromSpecifiedNodeToLeaf2(x.left);
        }

        int rightMaxPathSumFromSpecifiedNodeToLeaf = 0;
        if(x.right != null){
            rightMaxPathSumFromSpecifiedNodeToLeaf = findMaxPathSumFromSpecifiedNodeToLeaf2(x.right);
        }

        int curMax = Math.max(leftMaxPathSumFromSpecifiedNodeToLeaf, rightMaxPathSumFromSpecifiedNodeToLeaf) + x.value;
        maxPathSumToLeaf2 = Math.max(maxPathSumToLeaf2, curMax);
        return curMax;
    }

    public static void main(String[] args){
        Node node = new Node(4);
        node.left = new Node(10);
        node.left.right = new Node(-5);
        node.right = new Node(-7);
        node.right.left = new Node(15);
        System.out.println(findMaxRandomPathSum(node));
        System.out.println(findMaxRandomPathSum2(node));
        System.out.println(findMaxSumPathFromRoot(node));
        System.out.println(findMaxDownPathNum(node));
        System.out.println(findMaxPathSum(node));
        System.out.println(findMaxPathSum2(node));
        System.out.println(findMaxPathSum3(node));
        System.out.println(findMaxPathSum4(node));

        System.out.println(findMaxPathSumToLeaf2(node));
        System.out.println(findMaxPathSumToLeaf(node));
    }
}

