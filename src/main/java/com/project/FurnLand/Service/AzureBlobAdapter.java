package com.project.FurnLand.Service;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.models.BlobProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class AzureBlobAdapter {
    @Autowired
    BlobClientBuilder client;

    public String upload(MultipartFile file, String prefixName){
        if(Objects.nonNull(file)&& file.getSize()>0){
            String defaultUrl = "https://furnland.blob.core.windows.net/furnland-image-upload/";
            String imagePath;
            try {
                // name file
                String fileName = prefixName+ UUID.randomUUID().toString() +file.getOriginalFilename();

                client.blobName(fileName).buildClient().upload(file.getInputStream(),file.getSize());
                imagePath = defaultUrl+fileName;
                return imagePath;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
        }

//    public byte[] getFile(String name) {
//        try {
//            File temp = new File(name);
//            BlobProperties properties = client.blobName(name).buildClient().downloadToFile(temp.getPath());
//            byte[] content = Files.readAllBytes(Paths.get(temp.getPath()));
//            temp.delete();
//            return content;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public String download(String filename){
        String defaultUrl = "https://furnland.blob.core.windows.net/furnland-image-upload/";
        String imagePath= defaultUrl+filename;
        return imagePath;
    }

    public boolean deleteFile(String name) {
        try {
            client.blobName(name).buildClient().delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    }

