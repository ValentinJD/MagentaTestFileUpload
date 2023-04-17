package com.example.vv;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

@Slf4j
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
        boolean inMemory = false;
        //        throw new RuntimeException("kkkk");
        MultipartFile multipartFile = zipFile.get(0);
        String filename = multipartFile.getOriginalFilename();
        if (inMemory) {
            //        Upload in memory
            File dest = new File("D:\\DestinationFileFolder\\" + filename);
            try {
                byte[] bytes = zipFile.get(0).getBytes();
                FileUtils.writeByteArrayToFile(dest, bytes);
                log.info("File saved use memory in {}","D:\\DestinationFileFolder\\" + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//        Streaming upload
            InputStream stream = multipartFile.getInputStream();
            OutputStream out = new FileOutputStream("D:\\DestinationFileFolder\\" + filename);
            IOUtils.copy(stream, out);
            stream.close();
            out.close();
            log.info("File saved use streaming upload in {}","D:\\DestinationFileFolder\\" + filename);
        }
    }
}
