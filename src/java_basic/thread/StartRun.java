package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/11/2022
 * Time: 1:28 PM
 * Email: levinforward@163.com
 */
public class StartRun implements Runnable{
    public String url;
    public String filename;

    public StartRun(String url, String filename){
        this.url = url;
        this.filename = filename;
    }

    @Override
    public void run() {
        WebFileDownloader wd = new WebFileDownloader();
        wd.download(url, filename);
    }

    public static void main(String[] args) {
        String url = "https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=https%3A%2F%2Fimg.alicdn.com%2Fbao%2Fuploaded%2Fi1%2F707890731%2FO1CN01vls3O51HGqIbVG0ZN_%2521%25210-item_pic.jpg&thumburl=http%3A%2F%2Ft14.baidu.com%2Fit%2Fu%3D2295625971%2C214678785%26fm%3D224%26app%3D112%26f%3DJPEG%3Fw%3D500%26h%3D500";
        String filename = "baidu2.png";
//        StartRun sr = new StartRun(url, filename);
//        Thread th = new Thread(sr);
//        th.start();
        new Thread(new StartRun(url, filename)).start();
    }
}
