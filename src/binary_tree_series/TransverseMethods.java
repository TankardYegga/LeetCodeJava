package binary_tree_series;

import leetcode.q7;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Description: JVM
 * Creator: levin
 * Date: 1/4/2023
 * Time: 4:48 PM
 * Email: levinforward@163.com
 */

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int v){
        value = v;
        left = null;
        right = null;
    }
}

public class TransverseMethods {

    List<Integer> preRes = new LinkedList<>();

    List<Integer> getPreorderTransverse(TreeNode root){
        preorderTransverse(root);
        return preRes;
    }

    void preorderTransverse(TreeNode root){
        if(root == null){
            return;
        }

        preRes.add(root.value);

        preorderTransverse(root.left);

        preorderTransverse(root.right);
    }

    List<Integer> getPreorderTraverse(TreeNode root){

        List<Integer> preRes = new LinkedList<>();

        if(root == null){
            return preRes;
        }

        preRes.add(root.value);

        preRes.addAll(getPreorderTraverse(root.left));

        preRes.addAll(getPreorderTraverse(root.right));

        return preRes;
    }

    int count(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftCount = count(root.left);
        int rightCount = count(root.right);

        System.out.print("left: " + leftCount + "; right: " + rightCount);

        return leftCount + rightCount + 1;
    }


    int maxDiameter = 0;

    int maxTreeDiameter(TreeNode root){
        maxDepthAndDiameter(root);
        return maxDiameter;
    }

    int maxDepthAndDiameter(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftMaxDepth = maxDepthAndDiameter(root.left);
        int rightMaxDepth = maxDepthAndDiameter(root.right);

        int curDiameter = leftMaxDepth + rightMaxDepth;

        maxDiameter = Math.max(curDiameter, maxDiameter);

        return 1 + Math.max(leftMaxDepth, rightMaxDepth);
    }


    // 从根节点开始层序遍历二叉树
    void levelTraverse(TreeNode root){
        if(root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()){
            int size = q.size();
            // 从左到右遍历该层的每一个根节点
            for(int i = 0; i < size; i++){
                TreeNode curNode = q.poll();
                if(curNode.left != null){
                    q.offer(curNode.left);
                }
                if(curNode.right != null){
                    q.offer(curNode.right);
                }
            }
        }
    }

    //一种利用递归写法来进行层序遍历的思路
    //整体上是一个ArrayList, 该数组列表的每个元素又都是一个List类型
    List<List<Integer>> levelList = new ArrayList<>();

    List<List<Integer>> getLevelTraverseRes(TreeNode root){
        if(root == null){
            return levelList; // 空树不存在层序遍历结果，直接返回这个长度为0的arraylist
        }

        //进行递归式的层序遍历，然后返回遍历结果
        levelTraverse(root, 0);
        return levelList;
    }

    //核心的思路是DFS：
    //每遍历到一个节点时，将该节点加入对应层的list当中，通过节点所在的深度来定位所在层；
    //因为先序遍历中是从左往右遍历的
    //所以在先序遍历的整个过程中每一层的元素虽然不是连续被访问的，
    // 但是访问的相对顺序和层序遍历是一致的
    void levelTraverse(TreeNode root, int depth){

        if(root == null){
            return;
        }

        //先遍历当前层的元素
        //在前序位置执行

        //判断是否需要创建当前层的arrayList
        //如果当前节点是该层的第一个节点，则需要创建；否则，对应的数组list必然已经创建好了
        // 如果depth从1开始计算，则该判断条件可以改写成: levelList.size() + 1 <= depth  或者 levelList.size() < depth
        if(levelList.size() <= depth){
            levelList.add(new LinkedList<>());
        }

        //将当前结点的元素值加入到对应层的列表当中
        //这里的depth = 一般题目中的树的高度 - 1, 因为这里的depth值与list中的下标一一对应
        levelList.get(depth).add(root.value);

        levelTraverse(root.left, depth + 1);
        levelTraverse(root.right, depth + 1);

    }

    //另一种更接近BFS算法含义的递归实现的层序遍历
    List<List<Integer>> levelList2 = new ArrayList<>();

    List<List<Integer>> getLevelTraverse2(TreeNode root){
        if(root == null){
            return levelList2;
        }
        LinkedList<TreeNode> curLevelNodes = new LinkedList<>();
        curLevelNodes.add(root);
        levelTraverse2(curLevelNodes);
        return levelList2;
    }

    //已知当前层的所有节点：
    //依次取出每个节点
    // 将每个节点的值依次放入一个用于存储整数值的list中，
    // 将每个节点的子孩子节点放入新的list当中，对该孩子节点组成的新list进行递归
    void levelTraverse2(LinkedList<TreeNode> curLevelNodes){
        if(curLevelNodes.isEmpty()){
            return;
        }

        LinkedList<Integer> curLevelNodeValues = new LinkedList<>();
        LinkedList<TreeNode> nextLevelNodes = new LinkedList<>();

        //遍历linkedlist中的每个节点
        for(TreeNode curNode: curLevelNodes){
            curLevelNodeValues.add(curNode.value);
            if(curNode.left != null){
                nextLevelNodes.add(curNode.left);
            }
            if(curNode.right != null){
                nextLevelNodes.add(curNode.right);
            }
        }

        //将当前层次的遍历结果加入到全局list的对应下标位置中
        //因为这里是逐层遍历到，所以无需指定下标，只需直接加入即可
        //换句话说，因为这里是先加上当前层的遍历结果，再进行下一层的递归
        // 所以整体上是从上往下或者说自顶向下的层序遍历
        levelList2.add(curLevelNodeValues);

        levelTraverse2(nextLevelNodes);

        //若在后续位置加上当前层的遍历结果，
        //则会得到自底向上的层序遍历结果
        //levelList2.add(curLevelNodeValues);
    }
}
