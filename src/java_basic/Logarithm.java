package java_basic;

/**
 * Description: JVM
 * Creator: levin
 * Date: 8/29/2022
 * Time: 8:57 PM
 * Email: levinforward@163.com
 */
public class Logarithm {

    public static double log(double value, double base){
        return Math.log(value) / Math.log(base);
    }

    public static double log2(double value){
        return log(value, 2.0);
    }

    public static double log10(double value){
        return log(value, 10.0);
    }

    public static double logE(double value){
        return log(value, Math.E);
    }
}

