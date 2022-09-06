package stringapp;

/**
 * Description: LearnJVM
 * Creator: levin
 * Date: 8/5/2022
 * Time: 6:55 PM
 * Email: levinforward@163.com
 */



public class BMTest {
    public static void main(String[] args) throws java.lang.Exception {
        KMP kmp = new KMP();
//        String a = "QWERQWR";
//        String b = "WWE QWERQW QWERQWERQWRT";

//        String a = "CABABA";
//        String b = "XXXXAABABAXXXXAABABA";

//        String a = "aaaaaab";
//        String b = "bbbbb";

//        String a = "ATCACATCATCA";
//        String b = "GATCGATCACATATCACATCATCA";

        String a = "happy life";
        String b = "We all live a Happy and happy life";

        int[] next = kmp.getNext(a.toCharArray());
        for (int i = 0; i < next.length; i++) {
            System.out.println(a.charAt(i) + "    " + next[i]);
        }
        long start1 = System.nanoTime();
        int res = kmp.indexOf(b, a);
        long end1 = System.nanoTime();
        System.out.println(res);
        System.out.println((end1 - start1) + "ns");

        BM bm = new BM();
        long start2 = System.nanoTime();
        int res2 = bm.indexOf(b,a);
        long end2 = System.nanoTime();
        System.out.println(res2);
        System.out.println((end2 - start2) + "ns");
    }
}
