package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Description: 并查集的实现，使用显示的父亲指针
 * Creator: levin
 * Date: 9/3/2022
 * Time: 2:23 PM
 * Email: levinforward@163.com
 */
public class q10_replenish3 {

    public static class UnionNode<V>{
        V value;
        UnionNode<V> parent;

        public UnionNode(V val){
            value = val;
            parent = this;
        }
    }

    public static class UnionSet<V>{

        public HashMap<V, UnionNode<V>> nodes;
        public HashMap<UnionNode<V>, Integer> sizeMap;

        public UnionSet(List<V> nodeValues){
            for(V nodeValue: nodeValues){
                UnionNode<V> node = new UnionNode<>(nodeValue);
                nodes.put(nodeValue, node);
                sizeMap.put(node, 1);
            }
        }

        public UnionNode<V> findParent(UnionNode<V> cur){
            Stack<UnionNode<V>> parentPath = new Stack<UnionNode<V>>();

            while(cur != cur.parent){
                parentPath.push(cur);
                cur = cur.parent;
            }

            while (!parentPath.isEmpty()){
                UnionNode<V> popNode = parentPath.pop();
                popNode.parent = cur;
            }

            return cur;
        }

        public boolean isSameSet(V a, V b){
            if(!nodes.containsKey(a) || !nodes.containsKey(b)){
                return false;
            }

            return findParent(nodes.get(a)) == findParent(nodes.get(b));
        }

        public void union(V a, V b){
            if(!nodes.containsKey(a) || !nodes.containsKey(b)){
                return;
            }

            UnionNode<V> aHead = findParent(nodes.get(a));
            UnionNode<V> bHead = findParent(nodes.get(b));

            if(aHead == bHead){
                return;
            }

            if(sizeMap.get(aHead) >= sizeMap.get(bHead)){
                bHead.parent = aHead;
                sizeMap.put(aHead, sizeMap.get(aHead) + sizeMap.get(bHead));
                sizeMap.remove(bHead);
            }else{
                aHead.parent = bHead;
                sizeMap.put(bHead, sizeMap.get(aHead) + sizeMap.get(bHead));
                sizeMap.remove(aHead);
            }
        }
    }
}
