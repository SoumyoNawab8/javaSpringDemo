package com.example.javaSpringDemo.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.javaSpringDemo.services.VideoService;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/videos")
public class VideoRoutes {

    
    @Autowired
    private VideoService service;
    private static final Logger LOGGER = LogManager.getLogger(VideoRoutes.class);


@GetMapping("")
@ResponseBody
//
public Boolean getVideos(){
    try {
        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<vieo Routes hitting>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return true;
    } catch (Exception e) {
        throw e;
        // TODO: handle exception
    }
}

@PostMapping("")
@ResponseBody
public String uploadVideo(@RequestParam("file") MultipartFile multipartFile){
    try {
        LOGGER.info("^^^^^^^^ Uploading video");
        String result = service.uploadFile(multipartFile);
        LOGGER.info("result of uploadVideo");
        LOGGER.info(result);

        
        // TODO: handle file upload with firebase cloud storage
        return result;
    } catch (Exception e) {

        // TODO: handle exception
    }
    return null;
}

    
}
