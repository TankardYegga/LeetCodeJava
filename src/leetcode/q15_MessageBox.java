package leetcode;

import java.util.HashMap;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/14/2022
 * Time: 9:26 PM
 * Email: levinforward@163.com
 */
public class q15_MessageBox {

    public static class Node{
        public String info;
        public Node next;

        public Node(String str){
            info = str;
        }
    }


    //用于处理消息的接收，主要功能包括：
    // 1 前后连接序号上相连的消息
    // 2 一口气打印连续的信息
    // 3 只有当信息的链条完整时，才主动打印消息
    public static class MessageBox{
        public HashMap<Integer, Node> headMap;
        public HashMap<Integer, Node> tailMap;
        public int printHeadNum; // 打印的开始序号

        public MessageBox(){
            headMap = new HashMap<Integer, Node>();
            tailMap = new HashMap<Integer, Node>();
            printHeadNum = 1;
        }

        //接收信息
        //信息的序号范围从1到n
        public void receive(int num, String messageInfo){
            Node curInfoNode = new Node(messageInfo);
            headMap.put(num, curInfoNode);
            tailMap.put(num, curInfoNode);

            if(tailMap.containsKey(num - 1)){
                tailMap.get(num - 1).next = curInfoNode;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }

            if(headMap.containsKey(num + 1)){
                curInfoNode.next = headMap.get(num + 1);
                tailMap.remove(num);
                headMap.remove(num + 1);
            }

            if(num == printHeadNum){
                printInfoInOrder();
            }
        }

        private void printInfoInOrder(){
            Node node = headMap.get(printHeadNum);
            headMap.remove(printHeadNum);
            while (node != null){
                System.out.println(node.info);
                printHeadNum++;
                node = node.next;
            }
            tailMap.remove(printHeadNum - 1);
            System.out.println();
        }
    }

    public static void main(String[] args){
        MessageBox mb = new MessageBox();

        mb.receive(2, "Bob");
        mb.receive(4,  "Adam");
        mb.receive(7, "Google");
        mb.receive(3, "Amy");
        mb.receive(5, "Ground");

        mb.receive(1, "Justin Biber");

        mb.receive(6, "jesus");
        mb.receive(8, "Meta");
    }
}
