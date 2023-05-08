package com.prac.almond.app.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadUtil {

    public static String uploadImage(String path, MultipartFile multipartFile) throws IOException {

        if(!multipartFile.isEmpty()){
            String uploadPath = "D:\\"+File.separator+"upload"+File.separator+"images";
            uploadPath = uploadPath +File.separator + path;
            File folder = new File(uploadPath);
            if(!folder.exists()){
                folder.mkdirs();
            }
            String originalName = multipartFile.getOriginalFilename();
            String filePath = uploadPath + File.separator + originalName;
            File file = new File(filePath);
            multipartFile.transferTo(file);

            return File.separator+"images" + File.separator + path + File.separator + originalName;
        }
        return "";
    }

}
