package java_basic.webserver;

import java.util.List;
import java.util.Set;

/**
 * Description:
 * Creator: levin
 * Date: 10/19/2022
 * Time: 9:55 PM
 * Email: levinforward@163.com
 */
public class Mapping {

    private String name;
    private Set<String> urlPatterns;

    public Mapping() {

    }

    public Mapping(String name, Set<String> urlPatterns) {
        this.name = name;
        this.urlPatterns = urlPatterns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(Set<String> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }
}
