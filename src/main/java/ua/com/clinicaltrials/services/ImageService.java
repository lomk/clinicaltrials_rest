package ua.com.clinicaltrials.services;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

/**
 * Created by Igor on 11-Aug-16.
 */

@Service
public class ImageService {

    @Autowired
    ServletContext servletContext;

    public void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new RuntimeException("Only JPG images are accepted");
        }
    }

    public void saveImage(String filename, MultipartFile image)
            throws RuntimeException, IOException {
        try {
//            File file = new File("src/main/resources/META-INF/resources/images/" + filename);
//            File file = new File("D:\\IMAGES\\" + filename);
            File file = new File("/opt/" + filename);
            System.out.println(file.getName());
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            //FileUtils.readFileToByteArray(file);

        } catch (IOException e) {
            throw e;
        }
    }

    public byte[] getImageByteArray(String fileName){
//        File file = new File("D:\\IMAGES\\" + fileName);
        File file = new File("/opt/" + fileName);

        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
