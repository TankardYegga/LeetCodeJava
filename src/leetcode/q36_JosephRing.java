package leetcode;

/**
 * Description:
 * Creator: levin
 * Date: 11/8/2022
 * Time: 11:00 PM
 * Email: levinforward@163.com
 */
public class q36_JosephRing {

    //最后一个活下来的人在新的编号序列中必定是1
    //通过 新编号 =》旧编号 的函数
    //我们可以使用迭代或者递归来求解出活下来的人在最先开始的编号序列中的位置

    //函数需要传递两个参数：当前环中的人数sizeOfCircle、杀人的卡点数killPoint
    public static int getLiveNo(int sizeOfCircle, int killPoint){
        //递归终点
        if(sizeOfCircle == 1){
            return 1;
        }
        return (getLiveNo(sizeOfCircle - 1, killPoint) + killPoint - 1) % sizeOfCircle + 1;
    }

    //问题如果拓展到每次杀人的卡点数都不同，则只需要改变对应公式里的m的取值就行
    public static int getLiveNoWithDynamicKillPoints(int sizeOfCircle, int curRound, int[] killPoints){
        //递归终点
        if(sizeOfCircle == 1){
            return sizeOfCircle;
        }

        return (getLiveNoWithDynamicKillPoints(sizeOfCircle - 1, curRound + 1, killPoints)
                + killPoints[curRound] - 1) % sizeOfCircle + 1;
    }

    public static class Node{
        int value;
        Node next;

        public Node(int value){
            this.value = value;
        }
    }

    //构建一个循环的链表
    public static Node createCircularList(int[] valuesList){
        if(valuesList == null){
            return null;
        }

        Node head = new Node(valuesList[0]);
        Node preNode = head;
        Node curNode = null;

        for(int i = 1; i < valuesList.length; i++){
            curNode = new Node(valuesList[i]);
            if(i == valuesList.length - 1){
                curNode.next = head;
            }else {
                curNode.next = null;
            }
            preNode.next = curNode;
            preNode = curNode;
        }

        return head;
    }

    //直接按照游戏规则来计算得到最终活下来的那个人，在这里也就是返回对应的链表结点
    public static Node josephusKill1(Node head, int killPoint){
        //当循环单链表为空链表，则返回null
        //当单链表只有1个元素，则返回唯一的结点
        //当killPoint <= 0时，则相当于是不计数，也就是不杀人
        //当killPoint = 1时，相当于是每次都杀掉第一个人，那么最后活下来的必定是循环链表中的最后一个结点
        if(head == null || head.next == head || killPoint < 1){
            return head;
        }

        //从head开始计数，使用变量count来表示累加和
        //当count == killPoint，将对应的链表结点从循环链表中删除，同时count = 0

        //删除一个结点：将前一个结点的next指向当前结点的下一个结点，所以必须保存当前结点、当前结点的前一个结点
        Node preNode = null;
        Node curNode = head;
        int count = 1;

        //迭代的终止条件是循环单链表中只有1个结点
        while (curNode.next != curNode){
//            System.out.println("1 cur value:" + curNode.value);
            if(count != 0){
                preNode = curNode;
                curNode = curNode.next;
//                System.out.println("2 pre value:" + preNode.value);
//                System.out.println("2 cur value:" + curNode.value);
            }

            count++;
            if(count == killPoint){
                preNode.next = curNode.next;
                curNode.next = null; //这段代码会导致最初的head结点的next指针为null，所以必须要更新循环链表的head指针
                curNode = preNode.next;
                count = 0;
//                System.out.println("3 pre value:" + preNode.value);
//                System.out.println("3 cur value:" + curNode.value);
            }
        }

        head = curNode; // 此处对head指针的更新是没有用的，因为head相当于是原本circleHead引用值的一个拷贝，这里只对这份拷贝变量进行了修改

        return curNode;
    }

    ///直接按照游戏规则来计算得到最终活下来的那个人, 但是代码实现与上面有所不同
    //本质上的思想是一样的：记录前面一个结点和当前一个结点
    //不同的是：
    // 上面的代码并没有计算出head结点的前面一个结点，
    // 因为其从count = 1开始计数，也就是第二个结点开始进行循环，
    // 第二个结点的前驱结点必然是第一个结点
    // 从而无需考虑第一个重新开始计数的结点的前驱结点是什么，
    //  而这里从count=0也就是从第一个计数结点开始进行循环或者说迭代过程
    public static Node josephusKill1Adapted(Node head, int killPoint){
        if(head == null || head.next == head || killPoint < 1){
            return head;
        }

        Node last = head;
        while (last.next != head){
            last = last.next;
        }

        int count = 0;
        //head始终是last的下一个结点
        //每次删除一个人时都是删除对应的head
        //last是为了辅助这个删除过程的
        //当head == last时，说明循环链表只剩下最后一个元素
        while (head != last){
            if(++count == killPoint){
                last.next = head.next;
                count = 0;
            }else {
                last = last.next;
            }
            head = last.next;
        }

        return head;
    }

    //按照优化后的算法来返回存活下来的链表结点
    public static Node josephusKill2(Node head, int killPoint){
        if(head == null || head.next == head || killPoint < 1){
            return head;
        }

        //首先获取循环链表的大小
        //方法1
        int size = 0;
        Node curNode = head;
        while (curNode.next != head){
            size++;
            curNode = curNode.next;
        }
        size++;

        //方法2
        int size2 = 1;
        Node cur = head.next;
        while (cur != head){
            size2++;
            cur = cur.next;
        }

        //依据链表大小和killPoint计算活下来的结点的编号（从1开始编号）
        int liveNo = getLiveNo(size, killPoint);

        //将这个编号对应的结点给定位出来，并将整个循环链表改造成 只由该结点 组成的循环链表
        while ( (--liveNo) != 0 ){
            head = head.next;
        }
        head.next = head;

        return head;
    }

    public static void printCircularList(Node head){
        if(head == null){
            return;
        }

        System.out.println("Circular List: " + head.value + " ");

        Node curNode = head.next;
        //因为是循环链表，所以打印时只用打印一圈就可以，而不用循环打印
        while (curNode != head){
            System.out.println(curNode.value + " ");
            curNode = curNode.next;
        }
    }

    public static void main(String[] args) {
        int[] valuesList = {12, 15, 10, 3, 5, 7, 8};
        Node circleHead = createCircularList(valuesList);
        printCircularList(circleHead);

//        Node liveNode1 = josephusKill1(circleHead, 3);
//        System.out.println("live Node:" + liveNode1.value);
        // 因为这个过程中liveNode的获取是通过preNode和curNode两个指针实现的，而与head没有直接关系
        // head在整个过程中是没有发生任何变化、也没有被使用的
        // 所有head 和 liveNode 必然不一定相等，除非liveNode对应的结点就是最开始的head结点
//        System.out.println(liveNode1 == circleHead);


//        //在对链表进行循环杀人之后，一定要记得此时的链表的head要更新成最后那个活下来的结点
//        circleHead = liveNode1;
//        printCircularList(circleHead);


        Node liveNode2 = josephusKill1Adapted(circleHead, 3);
        System.out.println("live Node 2:" + liveNode2.value);

        //liveNode获取的过程与circleHead无关，所以两者不一定相等
        System.out.println(circleHead == liveNode2);
        circleHead = liveNode2;
        printCircularList(circleHead);

//        Node liveNode3 = josephusKill2(circleHead, 3);
//        System.out.println("live Node 3:" + liveNode3.value);
        //这里面是直接通过序号来定位到对应的结点，同样与初始的head值没有关系，所以必然不等
//        System.out.println(circleHead == liveNode3);
//        circleHead = liveNode3;
//        printCircularList(circleHead);

    }
}
