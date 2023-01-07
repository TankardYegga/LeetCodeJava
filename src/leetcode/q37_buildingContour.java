package leetcode;

import java.util.*;

/**
 * Description: JVM
 * Creator: levin
 * Date: 11/11/2022
 * Time: 11:17 AM
 * Email: levinforward@163.com
 */
public class q37_buildingContour {

    //内部类为何要求是静态的？
    public static class Op{
        public int x;
        public boolean isAdd;
        public int height;

        public Op(int x, boolean isAdd, int height){
            this.x = x;
            this.isAdd = isAdd;
            this.height = height;
        }
    }

    //自定义类的比较操作
    //可以说是当前类的比较类
    //是实现comparator而非comparable接口
    public static class OpComparator implements Comparator<Op>{

        @Override
        public int compare(Op o1, Op o2) {
            if(o1.x != o2.x){
                return o1.x - o2.x;
            }
            if(o1.isAdd != o2.isAdd){
                return o1.isAdd ? -1: 1;
            }
            return 0;
        }
    }

    public static List<List<Integer>> getBuildingOutline(int[][] mat){

        int N = mat.length;
        Op[] ops = new Op[N * 2];

        for(int i = 0; i < N; i++){
            ops[i * 2] = new Op(mat[i][0], true, mat[i][2]);
            ops[i * 2 + 1] = new Op(mat[i][1], false, mat[i][2]);
        }
        Arrays.sort(ops, new OpComparator());

        // treemap的底层实现是一个有序表
        TreeMap<Integer, Integer> heightTimesMap = new TreeMap<>();
        TreeMap<Integer, Integer> xToMaxHeightMap = new TreeMap<>();

        for(int i = 0; i < ops.length; i++){
            //遍历每一个三元组对象时
            // 如果是增操作
            // 则判断key是否存在
            // 如果不存在，则需要新建对应的entry；否则，只需更新对应的value就行
            // 如果是减操作
            // 则：若减完后的value变成0，则移除该entry，否则该entry对应的value自减1
            if(ops[i].isAdd){
                if(!heightTimesMap.containsKey(ops[i].height)){
                    heightTimesMap.put(ops[i].height, 1);
                }else {
                    heightTimesMap.put(ops[i].height, heightTimesMap.get(ops[i].height) + 1);
                }
            }else {
                if(heightTimesMap.get(ops[i].height) - 1 == 0){
                    heightTimesMap.remove(ops[i].height);
                }else{
                    heightTimesMap.put(ops[i].height, heightTimesMap.get(ops[i].height) - 1);
                }
            }

            // 对当前三元组的起始点的最大高度值进行更新
            // 如果 heightTimesMap 为空，则 最大高度为0
            // 如果 不为空，则 heightTimesMap 中的最大key 即为最大高度
            // 因为这里的遍历顺序是一个三元组一个三元组地进行遍历
            // 所以无法确定遍历到哪一个三元组时，该三元组起点对应的所有高度才统计结束
            // 所以每遍历一个三元组就要更新一次
            if(heightTimesMap.isEmpty()){
                xToMaxHeightMap.put(ops[i].x, 0);
            }else {
                xToMaxHeightMap.put(ops[i].x, heightTimesMap.lastKey());
            }
        }

        // 依据xToMaxHeightMap返回对应的建筑轮廓线
        List<List<Integer>> buildingOutline = new ArrayList<>();

        // 每当最大高度发生变化的时候，就得到一个轮廓线
        // 当存在纸片大楼（楼宽为1）时，最终建筑的轮廓线到底该怎么定义呢？

        // 1. 如果纸片大楼的轮廓线与其他建筑的轮廓线有所重合
        // 当纸片大楼位于最后时，无论其高度是小于还是大于其他楼层，
        // 其所在点的最大高度都是0
        // 事实上，纸片大楼存在与否，最后一个点处的最大高度都是0，因为这里所有的加和减操作都会成对抵消
        // 所以 xToMaxHeightMap 最后一个key对应的value一定为0
        // 当纸片大楼位于楼层中间时，其高度有可能超越其他楼的最大高度
        // 但是其+1 和 -1操作 都在同一个点处进行，所以必然抵消了
        // 改点的最大高度不会因为纸片大楼的存在而发生影响
        // 所以按照上述方式最后得到的轮廓线中是不考虑纸片大楼造成的单个点的
        // 2. 如果纸片大楼的轮廓线与其他建筑的轮廓线没有重合
        // 那么该纸片大楼必然单独有1个entry：（x，x，0）

        int preHeight = 0;
        int startPos = 0;

        for(Map.Entry<Integer, Integer> entry: xToMaxHeightMap.entrySet()){
            int curPos = entry.getKey();
            int curHeight = entry.getValue();
            if(curHeight != preHeight){
                if(preHeight != 0){
                    buildingOutline.add(Arrays.asList(startPos, curPos, preHeight));
                }
                startPos = curPos;
                preHeight = curHeight;
            }
        }

        return buildingOutline;

    }

    public static void main(String[] args) {

    }
}
