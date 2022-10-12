package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/11/2022
 * Time: 9:46 AM
 * Email: levinforward@163.com
 */
public class ThreadDownloader extends Thread{

    public String url;
    public String filename;

    public ThreadDownloader(String url, String filename){
        this.url = url;
        this.filename = filename;
    }

    @Override
    public void run(){
        WebFileDownloader wd = new WebFileDownloader();
        wd.download(url, filename);
    }

    public static void main(String[] args) {
        ThreadDownloader td1 = new ThreadDownloader("https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=https%3A%2F%2Fimg.alicdn.com%2Fbao%2Fuploaded%2Fi1%2F707890731%2FO1CN01vls3O51HGqIbVG0ZN_%2521%25210-item_pic.jpg&thumburl=http%3A%2F%2Ft14.baidu.com%2Fit%2Fu%3D2295625971%2C214678785%26fm%3D224%26app%3D112%26f%3DJPEG%3Fw%3D500%26h%3D500",
                "shoupoli.png");
        ThreadDownloader td2 = new ThreadDownloader("https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fimg.alicdn.com%252Fi3%252F2256772368%252FO1CN01tJ4WKw1TMaWln1INi_%2521%25212256772368.jpg%26refer%3Dhttp%253A%252F%252Fimg.alicdn.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1668045956%26t%3Df3ec643be88c0fe459b5ff5065985f47&thumburl=https%3A%2F%2Fimg2.baidu.com%2Fit%2Fu%3D3030908903%2C2458716256%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG%3Fw%3D500%26h%3D500",
                "venomous.png");
        ThreadDownloader td3 = new ThreadDownloader("https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fitem.m.jd.com%252Fproduct%252F10055124310251.html%253Fcu%253Dtrue%2526utm_source%253Dbaidu-juhe%2526utm_medium%253Dkong%2526utm_campaign%253Dt_1000151230_juhe%26refer%3Dhttp%253A%252F%252Fitem.m.jd.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1668045590%26t%3Dbe0d169f5608b2d0d408b749127483a0&thumburl=http%3A%2F%2Ft13.baidu.com%2Fit%2Fu%3D2287272536%2C1982640241%26fm%3D224%26app%3D112%26f%3DJPEG%3Fw%3D500%26h%3D500",
                "sake.png");
        td1.start();
        td2.start();
        td3.start();
    }
}
