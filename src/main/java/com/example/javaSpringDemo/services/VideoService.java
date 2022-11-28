package com.example.javaSpringDemo.services;

import java.io.IOException;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.core.io.ClassPathResource;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.beans.factory.annotation.Value;

@Service
public class VideoService {

    @Value("${firebase.bucket-name}")
    private String userBucketPath;

    ClassLoader classLoader = VideoService.class.getClassLoader();

    private static final Logger LOGGER = LogManager.getLogger(VideoService.class);

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + getExtension(originalFileName);
    }

    public void init() {
        // initialize Firebase
        try {
            LOGGER.info("CREDSSSSSSss");
            ClassPathResource serviceAccount = new ClassPathResource("serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setStorageBucket(userBucketPath)
                    .build();
            if (FirebaseApp.getApps().size() == 0) {
                LOGGER.info(FirebaseApp.initializeApp(options).getApps());
            }
            LOGGER.info("Firebase initialized successfully!!");

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            init();
            LOGGER.info("starting to upload :>>>>>> " + file.getOriginalFilename());

            Bucket bucket = StorageClient.getInstance().bucket();

            String name = generateFileName(file.getOriginalFilename());

            bucket.create(name, file.getBytes(), file.getContentType());
            LOGGER.info("name>>>>>: " + name);
            return name;
        } catch (Exception e) {
            LOGGER.error("Error in Upload>>>>>: " + e);
            throw new IllegalStateException(e);
        }
    }
}
