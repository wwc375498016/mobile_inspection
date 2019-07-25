package Utils;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.out;

/**
 * 上传签名
 */
public class UploadSignature {
    public static void convertStringtoImage(String encodedImageStr, String checkName,String signatureName,String date) {
        try {
            // Base64解码图片
            byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);
            File file = new File("C:/xampp/tomcat/webapps/signature/" + checkName+" "+date);
            if(!file.exists()){//如果文件夹不存在
                file.mkdir();//创建文件夹
            }
            //
            FileOutputStream imageOutFile = new FileOutputStream("C:/xampp/tomcat/webapps/signature/" + checkName+" "+date+"/"+signatureName+".jpg");
            imageOutFile.write(imageByteArray);

            imageOutFile.close();

            out.println("Image Successfully Stored");
        } catch (FileNotFoundException fnfe) {
            out.println("Image Path not found" + fnfe);
        } catch (IOException ioe) {
            out.println("Exception while converting the Image " + ioe);
        }
    }
}
