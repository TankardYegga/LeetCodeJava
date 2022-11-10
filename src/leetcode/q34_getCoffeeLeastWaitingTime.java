package leetcode;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Description: JVM
 * Creator: levin
 * Date: 11/1/2022
 * Time: 7:30 PM
 * Email: levinforward@163.com
 */
public class q34_getCoffeeLeastWaitingTime {

    public static class CoffeeMachine{

        int timeStart;
        int workPeriod;

        public CoffeeMachine(int timeStart, int workPeriod){
            this.timeStart = timeStart;
            this.workPeriod = workPeriod;
        }
    }

    public static class CoffeeMachineComparator implements Comparator<CoffeeMachine> {

        @Override
        public int compare(CoffeeMachine o1, CoffeeMachine o2) {
            return o1.timeStart + o1.workPeriod - o2.timeStart - o2.workPeriod;
        }
    }

    //N是排队的人数
    //arr是每台咖啡机制作咖啡的时间
    public static int[] getCoffeeLeastWaitingTime(int[] arr, int N){
        int[] ans = new int[N];
        //创建一个小根堆
        PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new CoffeeMachineComparator());
        for(int i = 0; i < arr.length; i++){
            heap.add(new CoffeeMachine(0, arr[i]));
        }

        for(int i = 0; i < N; i++){
            //获取当前小根堆的对顶
            CoffeeMachine heapTop = heap.poll();
            arr[i] = heapTop.timeStart + heapTop.workPeriod; //将当前咖啡制作完成的时间给记录下来
            heapTop.timeStart = arr[i];
            heap.add(heapTop);
        }
        return ans;
    }
}
