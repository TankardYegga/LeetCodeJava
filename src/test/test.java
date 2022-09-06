package test;

//import java.lang.String;

import classes.ClassLoaderTest;

public class test {
    private static String getType(Object a) {
        if (a == null) {
            System.out.println("ok");
//            System.exit(0);
            return null;
        }
        return a.getClass().toString();
    }

    public static void main(String[] args) {
        String s = "飞上云霄，生活在于寻找和创造";
        System.out.println(s);
//        System.out.println("hello world");
//
//        int i = 2;
//        int j = 4;
//        int k = i + j;
//
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(sysClassLoader);

        ClassLoader extClassLoader = sysClassLoader.getParent();
        System.out.println(extClassLoader);

        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);

        ClassLoader userdefinedClassLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(ClassLoaderTest.class);
        System.out.println(userdefinedClassLoader);

        ClassLoader userdefinedClassLoaderParent = userdefinedClassLoader.getParent();
        System.out.println(userdefinedClassLoaderParent);

        try {
            // 系统自带包中的类所对应的类加载器是启动类加载器
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);
            System.out.println(getType(classLoader));
            System.out.println(Class.forName("java.lang.String"));
            System.out.println(java.lang.String.class);
            System.out.println(java.lang.String.class == Class.forName("java.lang.String"));

//            ClassLoader classLoader2 = Class.forName("String").getClassLoader();
//            System.out.println(classLoader2);
            ClassLoader classLoader3 = String.class.getClassLoader();
            System.out.println(String.class);
            System.out.println("3" + classLoader3);

            ClassLoader classLoader4 = Thread.currentThread().getContextClassLoader();
            System.out.println(classLoader4);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("ok after");
    }
}
