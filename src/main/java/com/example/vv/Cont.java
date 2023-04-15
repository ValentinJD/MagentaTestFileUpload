package com.example.vv;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Cont {

    @PostMapping(value = "/maxoptra/scans", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void sendScan(
            @RequestHeader Map<String, String> headers,
            @RequestPart(name = "zipFile") List<MultipartFile> zipFile,
            @RequestPart(name = "productType") String productType,
            @RequestPart(name = "appNumber", required = false) String appNumber,
            @RequestPart(name = "deliveryExternalId") String deliveryExternalId,
            @RequestPart(name = "letterBarcode") String letterBarcode,
            @RequestPart(name = "processType", required = false) Integer processType
    ) throws Exception {
        MultipartFile multipartFile = zipFile.get(0);
//        throw new RuntimeException("kkkk");
        InputStream stream = multipartFile.getInputStream();
        String filename = multipartFile.getOriginalFilename();

        OutputStream out = new FileOutputStream("D:\\opt\\wildfly-share\\destination\\" + "test-file" + filename);
        IOUtils.copy(stream, out);
        stream.close();
        out.close();

//        File source = new File(filename);
//        File dest = new File("D:\\opt\\wildfly-share\\destination\\555_60_1200809668_2023-03-23_32a226e1-933f-4d85-9346-55740c51f84f.zip");
//        try {
//            FileUtils.copyFile(source, dest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
