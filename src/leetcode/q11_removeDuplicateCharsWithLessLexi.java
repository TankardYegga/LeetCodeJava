package leetcode;

import java.util.Locale;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/9/2022
 * Time: 2:38 PM
 * Email: levinforward@163.com
 */
public class q11_removeDuplicateCharsWithLessLexi {

    public static String remove(String str) {
        if (str == null || str.length() < 2) {
            return "";
        }

        // 首先统计每个字符的词频
        // 找到字符串中词频最快降低为0的那个字符的末尾位置
        // 将前面这部分子串中字典序最高的字符A选择出来
        // 将选中字符后面的子串中的A全部删除，再递归进行删除操作

        int[] charMap = new int[256];
        for (int i = 0; i < str.length(); i++) {
            System.out.println("char at " + i + ":" +  (str.charAt(i)) + " / " + (int) (str.charAt(i)));
            charMap[str.charAt(i)]++;
        }

        int maxASICIndex = 0;
        int i;
        for (i = 0; i < str.length(); i++) {
            if (--charMap[str.charAt(i)] == 0) {
                break;
            }
            maxASICIndex = str.charAt(maxASICIndex) > str.charAt(i) ? i : maxASICIndex;
        }

        return String.valueOf(
                str.charAt(maxASICIndex)) +
                remove(
                        str.
                                substring(maxASICIndex + 1).
                                replace(String.valueOf(str.charAt(maxASICIndex)), "")
                );
    }

    public static void main(String[] args){
        String str = "daccbdbaccdbbaz";
        String removedStr = remove(str);
        System.out.println(removedStr);
    }
}
