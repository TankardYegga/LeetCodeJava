package java_basic.thread;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Description: JVM
 * Creator: levin
 * Date: 10/11/2022
 * Time: 9:35 AM
 * Email: levinforward@163.com
 */
public class WebFileDownloader{

    public void download(String url, String filename){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(filename));
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
