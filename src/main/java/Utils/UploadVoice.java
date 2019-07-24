package Utils;

import org.apache.commons.codec.binary.Base64;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.out;

public class UploadVoice {

    public static void convertStringtoImage(String encodedImageStr, String fileName) {

        try {
            // Base64解码图片
            byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);

            //
            FileOutputStream voiceOutFile = new FileOutputStream("C:/xampp/tomcat/webapps/voice/" + fileName+".amr");
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
