package ua.com.clinicaltrials.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.clinicaltrials.services.ImageService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Igor on 12-Aug-16.
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
    public @ResponseBody String imageLoad(@RequestParam(value = "image", required = false) MultipartFile image){

        String imageName = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()) + ".jpg";
        String imageUrl = "/image/get/" + imageName;
        if (!image.isEmpty()) {
            try {
                imageService.validateImage(image);
            } catch (RuntimeException re) {
//                bindingResult.reject(re.getMessage());
                return "{\"error\":\"Wrong or empty data\"}";
            }
            try {
                imageService.saveImage(imageName, image);
            } catch (IOException e) {
//                bindingResult.reject(e.getMessage());
                return "{\"error\":\"Cant save\"}";
            }
        }
        return "{\"url\":\"" + imageUrl + "\"}";
    }

    /*@ResponseBody
    @RequestMapping(value = "/get/{id}",  produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET)
    public Resource texture(@PathVariable("id") String id) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        System.out.println();
        return resourceLoader.getResource("classpath:resources/META-INF/resources/images/" + id + ".jpg");
    }*/


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] testImage3(@PathVariable("id") String id) throws IOException {

        String fileName = id + ".jpg";

        return imageService.getImageByteArray(fileName);
    }
}
