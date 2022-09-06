package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Description: 补充并查集的实现
 * Creator: levin
 * Date: 9/3/2022
 * Time: 9:27 AM
 * Email: levinforward@163.com
 */
public class q10_replenish2 {

    public static class Node<V>{
        V value;

        public Node(V v){
            value = v;
        }
    }

    public static class UnionSet<V>{

        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> nodesValues){
            for(V nodeValue: nodesValues){
                Node<V> node = new Node<>(nodeValue);
                nodes.put(nodeValue, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findParent(Node<V> curNode){
            Stack<Node<V>> parentsPath = new Stack<Node<V>>();
            while (curNode != parents.get(curNode)){
                parentsPath.push(curNode);
                curNode = parents.get(curNode);
            }

            while(!parentsPath.isEmpty()){
                parents.put(parentsPath.pop(), curNode);
            }
            return curNode;
        }

        public boolean isSameSet(V a, V b){
            if(!nodes.containsKey(a) || !nodes.containsKey(b)){
                return false;
            }

            Node<V> aParent = findParent(nodes.get(a));
            Node<V> bParent = findParent(nodes.get(b));

            return aParent == bParent;
        }

        public void union(V a, V b){
            if(!nodes.containsKey(a) || !nodes.containsKey(b)){
                return;
            }

            Node<V> aHead = findParent(nodes.get(a));
            Node<V> bHead = findParent(nodes.get(b));

            if(aHead == bHead){
                return;
            }

            Node<V> bigSet = sizeMap.get(aHead) >= sizeMap.get(bHead) ? aHead: bHead;
            Node<V> smallSet = bigSet == aHead ? bHead: aHead;

            parents.put(smallSet, bigSet);
            sizeMap.put(bigSet, sizeMap.get(smallSet) + sizeMap.get(bigSet));
            sizeMap.remove(smallSet);
        }
    }
}
