package com.freetube.videoservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile multipartFile);
}
