package leetcode;

import java.util.LinkedList;

/**
 * Description: JVM
 * Creator: levin
 * Date: 12/5/2022
 * Time: 11:47 AM
 * Email: levinforward@163.com
 */
public class q41_ArithematicBasics {

    public static int calculateExpression(String expression){
        char[] str = expression.toCharArray();
        int[] ans = value(str, 0);
        return ans[0];
    }

    //i是表达式开始计算的位置
    public static int[] value(char[] str, int i){

        LinkedList<String> queue = new LinkedList<String>();

        int num = 0;

        while(i < str.length && str[i] != ')'){
            if(str[i] >= '0' && str[i] <= '9'){
                num = num * 10 + str[i++] - '0';
            }else if(str[i] == '('){
                //若是左括号, 则需要递归到下一层，根据下一层的计算结果来继续进行计算
                int[] res = value(str, i+1);
                //直接返回括号内的整体计算结果和右括号在整个字符串表达式中的index
                num = res[0];
                i = res[1];
            }else {
                //如果是普通的 + - * /
                //首先需要将前面一个num合适地放入队列当中
                //若当前运算符的优先级小于队列中已有的运算符（也就是+和-）
                //那么前面运算符的结果要计算出来之后，重新放入队列中
                // 若当前运算符的优先级大于等于队列中已有的运算符，则直接放入队列中
                addQueue(num, queue);
                // 将当前的运算符加入到队列中去
                queue.addLast(String.valueOf(str[i++]));
                //num必须重新置为0, 因为运算符后面不论是（还是数字，下一轮计算数字值的时候必须num=0
                num = 0;
            }
        }
        addQueue(num, queue);
        return new int[]{getQueueCalculations(queue), i + 1};
    }

    public static void addQueue(int num, LinkedList<String> queue){
        if(!queue.isEmpty()){
            String top = queue.pollLast();
            if(top.equals("+") || top.equals("-")){
                queue.addLast(top);
            }else{
                String cur = queue.pollLast();
                int curVal = Integer.valueOf(cur);
                num = top.equals("*") ? (curVal * num): (curVal / num);
            }
        }
        queue.addLast(String.valueOf(num));
    }

    public static int getQueueCalculations(LinkedList<String> queue){
        int ans = 0;
        int num = 0;
        String lastOp = "";
        while (!queue.isEmpty()){
            String top = queue.pollFirst();
            if(top.equals("+") || top.equals("-")){
                lastOp = top;
            }else{
                num = Integer.valueOf(top);
                if(lastOp.equals("+") || lastOp.equals("")){
                    ans += num;
                }else{
                    ans -= num;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String express = "30+5*(3+7*(3-5)+4)+6";
        int res = calculateExpression(express);
        System.out.println(res);
    }
}