package Utils;

import dao.CheckDAO;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static java.lang.System.out;

/**
 * 接收上传检查图片
 */
public class UploadCheckPhoto {
    public static void convertStringtoImage(String encodedImageStr, String projectName,String date, String address,String photoName) {

        try {
            //审阅完成，该检查完成，修改标志位
            CheckDAO.checkComplete(projectName,address,date);

            byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);
            File file = new File("C:/xampp/tomcat/webapps/checkphoto/" + projectName+" "+address);
            if(!file.exists()){//如果文件夹不存在
                file.mkdir();//创建文件夹
            }
            File file1 = new File("C:/xampp/tomcat/webapps/checkphoto/" + projectName+" "+address+"/"+date);
            if(!file1.exists()){//如果文件夹不存在
                file1.mkdir();//创建文件夹
            }
            //
            FileOutputStream photoOutFile = new FileOutputStream("C:/xampp/tomcat/webapps/checkphoto/" +projectName+" "+address+"/"+date+"/"+photoName+".jpg");
            photoOutFile.write(imageByteArray);

            photoOutFile.close();

            out.println("Image Successfully Stored");
        } catch (FileNotFoundException fnfe) {
            out.println("Image Path not found" + fnfe);
        } catch (IOException ioe) {
            out.println("Exception while converting the Image " + ioe);
        }
    }
}
