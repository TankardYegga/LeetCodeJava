package raw_datastructure;

public class Node<E>{

    E item;

    Node<E> next;

    Node<E> prev;

    public Node(Node<E> prev,  E element, Node<E> next){
        this.item = element;
        this.next = next;
        this.prev = prev;
    }

    //在链表的头节点之前插入一个新节点
    void linkFirst(Node<E> head, Node<E> lastNode,E e, int size){
        final Node<E> first = head;
        final Node<E> newNode = new Node<>(null, e, first);
        head = newNode;

        if(head == null){
            lastNode = newNode;
        }else{
            first.prev = newNode;
        }

        size++;
    }

    //在链表的尾节点之后插入一个新节点
    void linkLast(Node<E> lastNode, Node<E> head, E e, int size){
        final Node<E> lastNodeCopy = lastNode;
        final Node<E> newNode = new Node<>(lastNode, e, null);
        lastNode = newNode;

        if(lastNodeCopy == null){
            head = newNode;
        }else{
            lastNodeCopy.next = newNode;
        }

        size++;
    }

    //将指定的节点从链表中删除，也就是所谓的拆链
    E unlinkNode(Node<E> head, Node<E> lastNode, Node<E> toBeRemovedX, int size){
        E element = toBeRemovedX.item;
        Node<E> preNode = toBeRemovedX.prev;
        Node<E> nextNode = toBeRemovedX.next;

        // 当删除的节点是链表中的第一个元素节点时
        // preNode为空节点，此时 待删除节点的prev指针本来就是null值，所以无需自己再手动赋值了
        if(preNode == null){
            head = nextNode;
        }else{
            preNode.next = toBeRemovedX;
            toBeRemovedX.prev = null;
        }

        // 当删除的节点是链表中的最后一个元素节点时
        // 此时 待删除节点的next指针本来就是null值，所以也就无需自己再手动赋值了
        if(lastNode == null){
            lastNode = preNode;
        }else{
            lastNode.prev = preNode;
            toBeRemovedX.next = null;
        }

        size--;

        toBeRemovedX.item = null;
        return element;
    }

    // 从链表中删除 元素值为对象o 的值域
    // 此处代码匀速 节点的 元素值为null值
    public boolean remove(Object o, Node<E> head, Node<E> lastNode, int size){
        if(o == null){
            for(Node<E> x = head; x != null; x = x.next){
                if(x.item == null){
                    unlinkNode(head, lastNode, x, size);
                    return true;
                }
            }
        }else{
            for(Node<E> x = head; x != null; x = x.next){
                if(o.equals(x.item)){
                    unlinkNode(head, lastNode, x, size);
                    return true;
                }
            }
        }

        // 该链表中根本不存在该对象
        return false;
    }
}


