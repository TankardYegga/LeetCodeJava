package leetcode;

/**
 * Description: JVM
 * Creator: levin
 * Date: 8/15/2022
 * Time: 12:03 AM
 * Email: levinforward@163.com
 */
public class q2 {

    public static boolean isValidBrackets(String s){
        char[] ch = s.toCharArray();
        int count = 0;
        for(int i = 0; i < ch.length; i++){
            if(ch[i] == '('){
                count += 1;
            }

            if(ch[i] == ')'){
                count -= 1;
            }

            if(count == -1)
                return false;
        }

        return count == 0;
    }


    public static int complementCharNumber(String s){

        char[] ch = s.toCharArray();
        int count = 0;
        for(int i = 0; i < ch.length; i++){
            if(ch[i] == '('){
                count++;
            }else {
                count--;
            }
        }
        return Math.abs(count);
    }

    public static int complementCharsNumber2(String s){

        char[] ch = s.toCharArray();
        int count = 0;
        int need = 0;
        for(int i = 0; i < ch.length; i++){
            if(ch[i] == '('){
                count++;
            }else{
                count--;
            }

            if(count == -1){
                need += 1;
                count = 0;
            }
        }

        need += count;
        return need;
    }

    public static void main(String[] args){
//        String s = "(())(";
        String s = "((()(((";
        System.out.println(isValidBrackets(s));
        System.out.println(complementCharNumber(s));
        System.out.println(complementCharsNumber2(s));
    }
}
