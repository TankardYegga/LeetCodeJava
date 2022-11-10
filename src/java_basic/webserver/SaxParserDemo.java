package java_basic.webserver;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/18/2022
 * Time: 8:06 PM
 * Email: levinforward@163.com
 */
public class SaxParserDemo {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        String testTrim = "\n  And  we \n are!  \n";
        System.out.println(testTrim.trim()); //trim能除去字符串前后的空格、空行，但是别认为其能去除中间的“\n”

        //1. 获取SAX的解析工厂
        SAXParserFactory spf = SAXParserFactory.newInstance();
        //2. 获取一个SAX解析器
        SAXParser parser = spf.newSAXParser();

//        //3.自定义数据解析器
//        PersonHandler handler = new PersonHandler();
//        //4. 加载document 注册处理器, 进行解析
//        parser.parse(Thread.currentThread().getContextClassLoader().
//                getResourceAsStream("java_basic/webserver/person.xml"), handler);

        PersonHandlePerfection hdlerPerf = new PersonHandlePerfection();
        parser.parse(Thread.currentThread().getContextClassLoader().
                getResourceAsStream("java_basic/webserver/person.xml"), hdlerPerf);

        List<Person> personList = hdlerPerf.getPersonList();
        for(Person p: personList){
            System.out.println(p.getName() + ":" + p.getAge());
        }
    }
}

class PersonHandler extends DefaultHandler{
    @Override
    public void startDocument() throws SAXException {
        System.out.println("--The parsing of doc start--");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        System.out.println("uri:" + uri);
//        System.out.println("localName:" + localName); //都是空值的话有什么作用呢？
//        System.out.println("attr:" + attributes);
        //qName是标签的名字 到底是把<qName>单独读取到了还是说把<qName></qName>这个整体读到了呢
        //应该只是前半部分，不然不会有endElement的方法了

        //依据输出结果，应该是先读取前半部分，再读取中间的字符序列，最后读取到后半部分
        //其进入startElement函数是依据<X>来进行判断的，进入endElement函数是依据<X/>来判断
        //也就是说前面所说的[[中间的字符序列]]中可能是包含嵌套的其他对标签的

        //难点在于两对标签之间的 空白内容 + 换行 + 一部分缩进用途的空格
        //
        System.out.println(qName + "--> start to parse ");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        System.out.println("uri2:" + uri);
//        System.out.println("localName2:" + localName);
        System.out.println(qName + "--> end to parse ");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("--The parsing of doc end--");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("ch:" + ch.toString());
        System.out.println("start:" + start + " length:" + length);
        String contents = new String(ch, start, length).trim();
        if(contents.length() != 0){
            System.out.println("内容为:" + contents);
        }else {
            System.out.println("内容为空");
        }
    }
}

//把读取的相应person标签块读取成对应的Person对象，并使用一个list来存储所有的Person对象
class PersonHandlePerfection extends DefaultHandler{

    public List<Person> personList;
    public Person curPerson;
    public String tag;

    public PersonHandlePerfection(){

    }

    @Override
    public void startDocument() throws SAXException {
        personList = new LinkedList<Person>();
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + "->开始解析");
        if(qName.equals("person")){
            curPerson = new Person();
        }
        tag = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(qName.equals("person")){
            personList.add(curPerson);
        }


        tag = null;
        System.out.println(qName + "->结束解析");

    }

    public List<Person> getPersonList() {
        return personList;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        //需要注意这里的tag必须是字符串才能调用比较方法
        //如果tag本身为null, 则自然不存在该方法
        if(tag != null){
            if(tag.equals("name")){
                curPerson.setName(content);
            }else if(tag.equals("age")){
                if(content.length() > 0){
                    curPerson.setAge(Integer.valueOf(content));
                }
            }
        }
    }
}