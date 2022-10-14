package java_basic.thread;

import java.util.concurrent.*;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/12/2022
 * Time: 11:10 PM
 * Email: levinforward@163.com
 */
public class CallableDownloader implements Callable{
    private String fname;
    private String url;

    public CallableDownloader(String url, String fname){
        this.fname = fname;
        this.url = url;
    }

    @Override
    public Object call() throws Exception {
        WebFileDownloader wd = new WebFileDownloader();
        wd.download(url, fname);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableDownloader cd1 = new CallableDownloader("https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=https%3A%2F%2Fimg.alicdn.com%2Fbao%2Fuploaded%2Fi1%2F707890731%2FO1CN01vls3O51HGqIbVG0ZN_%2521%25210-item_pic.jpg&thumburl=http%3A%2F%2Ft14.baidu.com%2Fit%2Fu%3D2295625971%2C214678785%26fm%3D224%26app%3D112%26f%3DJPEG%3Fw%3D500%26h%3D500",
                "shoupoli.png");
        CallableDownloader cd2 = new CallableDownloader("https://image.baidu.com/search/down?tn=download&ipn=dwnl&word=download&ie=utf8&fr=result&url=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fimg.alicdn.com%252Fi3%252F2256772368%252FO1CN01tJ4WKw1TMaWln1INi_%2521%25212256772368.jpg%26refer%3Dhttp%253A%252F%252Fimg.alicdn.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Dauto%3Fsec%3D1668045956%26t%3Df3ec643be88c0fe459b5ff5065985f47&thumburl=https%3A%2F%2Fimg2.baidu.com%2Fit%2Fu%3D3030908903%2C2458716256%26fm%3D253%26fmt%3Dauto%26app%3D138%26f%3DJPEG%3Fw%3D500%26h%3D500",
                "venomous.png");

        //创建服务
        ExecutorService es = Executors.newFixedThreadPool(2);
        //提交执行
        Future<Boolean> res1 = es.submit(cd1);
        Future<Boolean> res2 = es.submit(cd2);
        //获取结果
        Boolean ans1 = res1.get();
        System.out.println("ans1:" + ans1);
        Boolean ans2 = res2.get();
        System.out.println("ans2:" + ans2);
        //关闭服务
        es.shutdownNow();
    }
}
