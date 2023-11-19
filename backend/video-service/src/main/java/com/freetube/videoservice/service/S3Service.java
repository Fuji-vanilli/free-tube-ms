package com.freetube.videoservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

//@Service
@RequiredArgsConstructor
public class S3Service  {
    /*
    private static final String BUCKET_NAME= "free-tube23";
   // private final AmazonS3Client awsS3Client;
    @Override
    public String uploadFile(MultipartFile file) {
        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        var key = UUID.randomUUID().toString() + filenameExtension;

        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            awsS3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException("error to send file inputStream");
        }
        awsS3Client.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);

        return awsS3Client.getResourceUrl(BUCKET_NAME, key);
    return null;

    }
    */
}
