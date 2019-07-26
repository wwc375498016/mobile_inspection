package Utils;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.out;

public class UploadVoice {

    public static void convertStringtoImage(String encodedImageStr, String projectName,String date,String time,String address) {

        try {
            // Base64解码图片
            byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);
            File file = new File("C:/xampp/tomcat/webapps/voice/" + projectName+" "+address);
            if(!file.exists()){//如果文件夹不存在
                file.mkdir();//创建文件夹
            }
            File file1 = new File("C:/xampp/tomcat/webapps/voice/" + projectName+" "+address+"/"+date);
            if(!file1.exists()){//如果文件夹不存在
                file1.mkdir();//创建文件夹
            }
            //
            FileOutputStream voiceOutFile = new FileOutputStream("C:/xampp/tomcat/webapps/voice/" +projectName+" "+address+"/"+date+"/"+time+".amr");
            voiceOutFile.write(imageByteArray);

            voiceOutFile.close();

            out.println("voice Successfully Stored");
        } catch (FileNotFoundException fnfe) {
            out.println("voice Path not found" + fnfe);
        } catch (IOException ioe) {
            out.println("Exception while converting the voice " + ioe);
        }
    }
}
