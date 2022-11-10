package java_basic.webserver;

/**
 * Description:
 * Creator: levin
 * Date: 10/19/2022
 * Time: 9:51 PM
 * Email: levinforward@163.com
 */
public class ServerEntity {

    private String name;
    private String classPath;

    public ServerEntity() {
    }

    public ServerEntity(String name, String classPath) {
        this.name = name;
        this.classPath = classPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
