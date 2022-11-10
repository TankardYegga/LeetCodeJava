package java_basic.webserver;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/25/2022
 * Time: 7:47 PM
 * Email: levinforward@163.com
 */
public class RegisterServlet implements Servlet{

    @Override
    public void service(){
        System.out.println("RegisterServlet");
    }
}
