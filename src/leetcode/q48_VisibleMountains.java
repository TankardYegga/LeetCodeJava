package leetcode;

import java.util.HashSet;
import java.util.Stack;

public class q48_VisibleMountains {

    public static class Record{

        int value;
        int times;

        public Record(int value){
            this.value = value;
            this.times = 1;
        }
    }

    public static int getVisiblePairsNumByStack(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }

        int n = arr.length;

        Stack<Record> mountainHeightsMonotonousStack = new Stack<>();

        // 找出数组中最大值所对应的下标
        int maxIndex = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > arr[maxIndex]){
                maxIndex = i;
            }
        }

        mountainHeightsMonotonousStack.add(new Record(arr[maxIndex]));

        int res = 0;

        // 遍历一遍数组元素，将元素入栈
        // 以下的代码优化之处：使用while而不是for；开始的两个分支可以直接去掉，因为第三个分支下中当while语句不执行就是这两个分支了
        for(int i = nextIndex(maxIndex, n); i != maxIndex; i = nextIndex(i, n)){
            int top = mountainHeightsMonotonousStack.peek().value;

            // 若当前元素与栈顶相等，则栈顶对应的次数加1
            if(arr[i] == top){
                mountainHeightsMonotonousStack.peek().times++;
            }else if(arr[i] < top){
                // 若当前元素比栈顶小，则直接将当前元素入栈
                mountainHeightsMonotonousStack.add(new Record(arr[i]));
            }else{
                // 若当前元素比栈顶大，则需要不断弹出栈顶元素并进行可见山峰对的清算，直到栈顶元素不比当前元素小
                while (top < arr[i]){
                    int k = mountainHeightsMonotonousStack.pop().times;
                    res += getInternalSum(k) + 2 * k;
                    top = mountainHeightsMonotonousStack.peek().value;
                }

                if(top == arr[i]){
                    mountainHeightsMonotonousStack.peek().times++;
                }else if(top > arr[i]){
                    mountainHeightsMonotonousStack.add(new Record(arr[i]));
                }else {

                }
            }
        }

        // 对栈中的元素进行清算
        // 当栈中剩余元素超过2个，也就是说栈顶元素下面至少有两个元素
        while (mountainHeightsMonotonousStack.size() > 2){
            int k = mountainHeightsMonotonousStack.pop().times;
            res += getInternalSum(k) + 2 * k;
        }

        // 当栈中剩余元素只有2个
        int k = mountainHeightsMonotonousStack.pop().times;
        res += getInternalSum(k) +
                mountainHeightsMonotonousStack.peek().times > 1 ?
                2 * k : k;

        // 当栈中剩余元素只有1个
        res += getInternalSum(mountainHeightsMonotonousStack.pop().times);

        return res;
    }

    public static int getInternalSum(int k){
        return k == 1 ? 0 : k * (k - 1) / 2;
    }

    public static int nextIndex(int idx, int len){
        return idx < len - 1 ? idx + 1: 0;
    }

    public static int lastIndex(int idx, int len){
        return idx > 0 ? idx - 1: len - 1;
    }

    public static int getNextRoundIndex(int idx, int len, String direction){
        if("next".equals(direction)){
            return (idx + 1 + len) % len;
        }else if("last".equals(direction)){
            return (idx - 1 + len) % len;
        }else {
            return -1;
        }
    }

    // 普通的暴力解题思路：for test, 时间复杂度是O(N^3)
    // 输入是一个int型的数组
    // 输出是可见山峰对的对数
    // 思路：对每个山峰，判断除了自身以外的所有山峰是否可以与当前山峰形成可见对
    // 可见对也要求：（小，大）来避免重复判断和寻找

    //需要注意的是：
    // Q1: 当数组中含有重复元素时，会出现什么问题？要怎么处理呢？
    // 我觉得这与判断一组山峰对（A，B）时的处理方法有关：
    // 如果A > B， 则直接排除；
    // 如果A < B，在两个方向上判断是否存在比A大的数，如果任意一个方向上不存在，则是可见对，否则不是可见对；
    // 如果A == B, 与A < B的判断方法一样，但是此时必须把（A, B）已经计算过一次的事实存储起来，
    // 不然下一次会遇到（B, A）且会重复计数，
    // 而A < B 时的（B,A）不满足先小后大的条件，所以直接排除了，所以不需要存储（A,B）这个可见对的信息
    
    // Q2: 如何存储（A, B) (A=B)这种可见对的信息？
    // A2-I：可以用集合直接存储A这个元素，那么当遇到(B, A) (B = A) 是先判断是否是可见对，还是先判断其是否出现在集合中呢？
    // 如果先判断是否在集合中，如果是，则直接跳过；如果不是，则还需判断，判断后是的话需要加进集合中
    // 如果先判断是否是可见对的话，如果是，需要再判断是否出现在集合中，不在集合则加入；如果不是，直接跳过
    // 两种判断顺序最多都是需要判断一次是否在集合内和一次是否是可见对，
    // 但是最少情况下前者只用判断一次是否在集合内，后者只用判断一次是否是可见对
    // 判断可见对 明显比 判断集合 更耗时
    // 所以从平均性能来说，应该先判断：是否出现在集合里面
    // A2-II: 对于可见对（A, B）（A=B）， 存储A的下标i和B的下标j组成的：i + 某个符号 + j （i < j）
    // 那么对于任意一个待判断的山峰对(X, Y), 判断：min_index(X,Y) + 某个符合 + max_index(X,Y) 是否已经存在
    public static int rightWay(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }

        int res = 0;
        HashSet<String> elemEqualPairs = new HashSet<>();
        boolean isVisible = false;

        for(int i = 0; i < arr.length; i++){

            res += getVisiblePairsNumFromIndex(arr, i, elemEqualPairs);
        }

        return res;
    }

    public static int getVisiblePairsNumFromIndex(int[] arr, int index, HashSet<String> elemEqualPairs){
        int res = 0;

        for(int j = 0; j < arr.length; j++){

            if(j != index){

                if(arr[index] == arr[j]){

                    String keyStr = Math.min(index, j) + "_" + Math.max(index, j);

                    if(elemEqualPairs.add(keyStr) && isVisiblePairs(index, j, arr)){
                        res++;
                    }

                }else if(isVisiblePairs(index, j, arr)){
                   res++;
                }else {
                }
            }
        }

        return res;
    }


    public static boolean isVisiblePairs(int lowValIndex, int highValIndex, int[] arr){
        int n = arr.length;

        if(arr[lowValIndex] > arr[highValIndex]){
            return false;
        }

        // lowValIndex通过next方向走到highValIndex, 沿途不能出现比arr[lowValIndex]大的数
        int nextI = nextIndex(lowValIndex, n);
        boolean walkNext = true;
        while (nextI != highValIndex){
            if(arr[nextI] > arr[lowValIndex]){
                walkNext = false; //next方向失败
                break;
            }
            nextI = nextIndex(nextI, n);
        }

        // lowValIndex通过last方向走到highValIndex, 沿途不能出现比arr[lowValIndex]大的数
        int lastI = lastIndex(lowValIndex, n);
        boolean walkLast = true;
        while (lastI != highValIndex){
            if(arr[lastI] > arr[lowValIndex]){
                walkLast = false; //last方向失败
                break;
            }
            lastI = lastIndex(lastI, n);
        }

        return walkNext || walkLast;
    }


    //给定数组的最大长度、数组中元素的最大值来生成一个随机数组
    public static int[] generateRandomArray(int lenMax, int elemMax){
        int[] arr = new int[(int)(Math.random() * (lenMax + 1))];

        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * (elemMax + 1));
        }

        return arr;
    }

    //打印数组元素
    public static void printArr(int[] arr){
        if(arr == null || arr.length < 1){
            return;
        }

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i] + " ");
        }

        System.out.println();
    }


    public static void main(String[] args) {
        int testTimes = 10;
        int lenMax = 10;
        int elemMax = 100;

        for(int i = 0; i < testTimes; i++){
            int[] arr = generateRandomArray(lenMax, elemMax);

            int ans2 = rightWay(arr);

            int ans1 = getVisiblePairsNumByStack(arr);

            System.out.println("ans1:" + ans1 +  " ans2:" + ans2);

            if(ans1 != ans2){
                System.out.println("ans1:" + ans1 +  " ans2:" + ans2);
                break;
            }
        }
    }
}
