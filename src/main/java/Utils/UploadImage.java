package Utils;

import org.apache.commons.codec.binary.Base64;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.out;

public class UploadImage {

    public static void convertStringtoImage(String encodedImageStr, String fileName) {

        try {
            // Base64解码图片
            byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);

            //
            FileOutputStream imageOutFile = new FileOutputStream("C:/picture/" + fileName+".jpg");
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
