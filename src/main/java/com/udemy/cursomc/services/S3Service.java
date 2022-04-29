package com.udemy.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Value("${s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public URI uploadFile(MultipartFile multipartFile) {

        try {

            InputStream inputStream = multipartFile.getInputStream();
            String fileName = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            return uploadFile(inputStream, fileName, contentType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {

        try {

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            LOG.info("Iniciando upload");
            s3Client.putObject(bucketName, fileName, inputStream, objectMetadata);
            LOG.info("Upload finalizado");
            return s3Client.getUrl(bucketName, fileName).toURI();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }

}
