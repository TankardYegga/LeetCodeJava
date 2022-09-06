package leetcode;

import java.util.ArrayList;

/**
 * Description: 在编写q7的过程中思考出的一些其他问题
 * Creator: levin
 * Date: 9/1/2022
 * Time: 9:45 AM
 * Email: levinforward@163.com
 */
public class q7_extension {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int val){
            value = val;
        }
    }

    ///////////////////////////////////////////////////////////////
    //
    //  获得某个结点的父亲结点信息
    ////////////////////////////////////////////////////////////////
    public static Node findParent(Node head, Node searchedNode){
        if(head == null){
            return null;
        }

        return labelParent(head, null, searchedNode);
    }

    public static Node labelParent(Node x, Node p, Node searchedNode){
        if(x == searchedNode){
            return p;
        }

        Node leftSearchedResult = null;
        if(x.left != null){
            leftSearchedResult = labelParent(x.left, x, searchedNode);
        }
        if(leftSearchedResult != null){
            return leftSearchedResult;
        }

        Node rightSearchedResult = null;
        if(x.right != null){
            rightSearchedResult = labelParent(x.right, x, searchedNode);
        }
        if(rightSearchedResult != null){
            return rightSearchedResult;
        }

        return null;
    }

    ///////////////////////////////////////////////////////////////
    //
    //  获得某个结点是其父亲结点的左孩子还是右孩子
    //  如果是左孩子，则返回'left', 如果是有孩子，则返回‘right’, 如果没有父亲结点，则返回‘no’
    ////////////////////////////////////////////////////////////////
    public static String determineChildDirection(Node head, Node searchNode){
        if(head == null){
            return new String("no");
        }

        return labelChildDirection(head, "no", searchNode);
    }

    public static String labelChildDirection(Node x, String direction, Node searchNode){
        if(x == searchNode){
            return direction;
        }

        String leftDirectionStr = "no";
        if(x.left != null){
            leftDirectionStr = labelChildDirection(x.left, "left", searchNode);
        }
        if(!leftDirectionStr.equals("no")){
            return leftDirectionStr;
        }

        String rightDirectionStr = "no";
        if(x.right != null){
            rightDirectionStr = labelChildDirection(x.right, "right", searchNode);
        }
        if(!rightDirectionStr.equals("no")){
            return rightDirectionStr;
        }

        return "no";
    }


    ///////////////////////////////////////////////////////////////
    //
    //  获得某个结点的所有祖先结点信息
    ////////////////////////////////////////////////////////////////
    public static ArrayList<Node> findAllAncestors(Node head, Node searchedNode){
        if(head == null){
            return null;
        }

        ArrayList<Node> pL = new ArrayList<Node>();
        return labelAllAncestors(head, pL, searchedNode);
    }

    public static ArrayList<Node> labelAllAncestors(Node x, ArrayList<Node> parentList, Node searchedNode){
        System.out.println("x :" + x.value);
        if(x == searchedNode){
            parentList.add(x);
            // 必须进行真拷贝
            // 不然接受返回值的变量和函数参数parentList指向的就是同一个list了
            ArrayList<Node> parentListTrueCopy = (ArrayList<Node>) parentList.clone();
            return parentListTrueCopy;
        }

        ArrayList<Node> leftNodeParentList = new ArrayList<Node>();
        if(x.left != null){
            parentList.add(x);
            leftNodeParentList = labelAllAncestors(x.left, parentList, searchedNode);
            parentList.remove(x);
        }
        if(leftNodeParentList.size() != 0){
            return leftNodeParentList;
        }

        ArrayList<Node> rightNodeParentList = new ArrayList<Node>();
        if(x.right != null){
            parentList.add(x);
            rightNodeParentList = labelAllAncestors(x.right, parentList, searchedNode);
            parentList.remove(x);
        }
        if(rightNodeParentList.size() != 0){
            return rightNodeParentList;
        }

        return null;
    }

    public static void main(String[] args){
        Node head = new Node(5);
        head.left = new Node(-3);
        head.left.left = new Node(20);
        head.left.right = new Node(18);
        head.left.left.right = new Node(35);
        head.right = new Node(60);

        Node searchedNode = head.left.left.right;
        System.out.println(findParent(head, searchedNode).value);
        ArrayList<Node> l = findAllAncestors(head, searchedNode);
        for(int i = 0; i < l.size(); i++){
            System.out.print(l.get(i).value + ",");
        }
        System.out.println();

        System.out.println(determineChildDirection(head, searchedNode));
    }
}
