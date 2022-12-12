import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ImgDownloader {

    public static void download(String sourceURL, String destFilename) throws Exception {

        URL url = new URL(sourceURL);
        URLConnection con = url.openConnection();
        con.setConnectTimeout(3 * 1000);

        InputStream is = con.getInputStream();
        byte[] bs = new byte[1024];
        int len;

        File file = new File(destFilename);
        OutputStream os = new FileOutputStream(file);
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }

        os.close();
        is.close();
    }

    public static void main(String[] args) throws Exception {
        int page;
        Scanner sc = new Scanner(System.in);
        String sourceURL = "https://api.ghser.com/random/pc.php";
        while(true){
            System.out.printf("请输入下载数量(0<a<=5):");
            page = sc.nextInt();
            if(page > 5 || page <= 0){
                System.out.println("请按要求输入!");
                continue;
            }else if(page > 0 && page <= 5){
                for(int i = 1;i < page+1;i++){
                    String destFilename = i+".jpg";
                    download(sourceURL, destFilename);
                    System.out.printf("第%d个图片下载成功!\n",i);
                    Thread.sleep(1 * 1000);
                }
            }
            break;
        }
    }
}
