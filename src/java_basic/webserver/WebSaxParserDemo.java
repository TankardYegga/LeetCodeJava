package java_basic.webserver;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/19/2022
 * Time: 10:04 PM
 * Email: levinforward@163.com
 */
public class WebSaxParserDemo {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        WebHandler wh = new WebHandler();
        saxParser.parse(Thread.currentThread().getContextClassLoader().
                getResourceAsStream("java_basic/webserver/web.xml"), wh);
//        List<ServerEntity> serverEntities = wh.getServerEntities();
//        List<Mapping> mappings = wh.getMappings();
//
//        for(ServerEntity se: serverEntities){
//            System.out.println(se.getName() + " 's class path is:" + se.getClassPath());
//        }
//        for(Mapping mp: mappings){
//            System.out.println(mp.getName() + " 's mapping path is:" + mp.getUrlPatterns());
//        }


        //获取数据
        WebContext webContext = new WebContext(wh.getServerEntities(), wh.getMappings());
        //假设输入了/login
        String clsPath = webContext.getCls("/login");
        System.out.println("clsPath:" + clsPath);
        Class cls = Class.forName(clsPath);
        Servlet servlet = (Servlet) cls.getConstructor().newInstance();
        servlet.service();

        WebContext webContext1 = new WebContext(wh.getServerEntities(), wh.getMappings());
        String clsPath2 = webContext1.getCls("/reg");
        System.out.println("clsPath2:" + clsPath2);
        Class cls1 = Class.forName(clsPath2);
        Servlet servlet1 = (Servlet) cls1.getConstructor().newInstance();
        servlet1.service();
    }
}

class WebHandler extends DefaultHandler{
    private List<ServerEntity> serverEntities;
    private List<Mapping> mappings;
    private ServerEntity serverEntity;
    private Mapping mapping;
    private Set<String> urlPatterns;
    private String tag;
    private boolean isEntity;

    public WebHandler(){
        serverEntities = new LinkedList<ServerEntity>();
        mappings = new LinkedList<Mapping>();
        isEntity = false;
        tag = "";
    }

    public List<ServerEntity> getServerEntities(){
        return serverEntities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("servlet")){
            serverEntity = new ServerEntity();
            isEntity = true;
        }else if(qName.equals("servlet-mapping")){
            mapping = new Mapping();
            isEntity = false;
            mapping.setUrlPatterns(new HashSet<String>());
        }
        tag = qName;
//        System.out.println("tag is:" + tag);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("servlet")){
            serverEntities.add(serverEntity);
        }else if(qName.equals("servlet-mapping")){
            mappings.add(mapping);
        }else if(qName.equals("servlet-name") || qName.equals("servlet-class") || qName.equals("url-pattern")){
            tag = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
//        System.out.println("In chars, the tag is:" + tag);
        if(tag.equals("servlet-name")){
            if(isEntity){
                serverEntity.setName(content);
            }else {
                mapping.setName(content);
            }
        }else if(tag.equals("servlet-class")){
            serverEntity.setClassPath(content);
        }else if(tag.equals("url-pattern")){
            mapping.getUrlPatterns().add(content);
        }
    }


}
