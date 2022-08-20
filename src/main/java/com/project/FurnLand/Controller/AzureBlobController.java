package com.project.FurnLand.Controller;

import com.project.FurnLand.DTO.Response.ApiResponse;
import com.project.FurnLand.Exceptions.BadRequestException;
import com.project.FurnLand.Service.AzureBlobAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class AzureBlobController {
    @Autowired
    AzureBlobAdapter azureAdapter;








//    @GetMapping(path = "/download")
//    public ResponseEntity<ByteArrayResource> uploadFile(@RequestParam(value = "file") String file) throws IOException {
//        byte[] data = azureAdapter.getFile(file);
//        ByteArrayResource resource = new ByteArrayResource(data);
//
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + file + "\"")
//                .body(resource);
//
//    }

    @GetMapping(path = "/download")
    public ResponseEntity<?> downloadFile(@RequestParam(name = "fileName") String fileName){
        String filePath = azureAdapter.download(fileName);
        return  ResponseEntity.ok(filePath);
    }


}
