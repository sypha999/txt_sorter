package com.subsidian.controller;

import com.subsidian.services.serviceImpl.AppServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/v1/")
public class AppController {

    final AppServiceImpl appService;

    public AppController(AppServiceImpl appService) {
        this.appService = appService;
    }

    @GetMapping(value = "/sort",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody Resource sort_file(@RequestBody MultipartFile file) throws IOException {
        File my_file = new File("my_file");
        try(OutputStream outputStream = new FileOutputStream(my_file)){
            outputStream.write(file.getBytes());
        }
        File f = appService.sortFile(my_file);
        InputStream inputStream = new FileInputStream(f);
        return new ByteArrayResource(IOUtils.toByteArray(inputStream));
    }
}
