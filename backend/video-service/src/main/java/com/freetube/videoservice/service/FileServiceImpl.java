package com.freetube.videoservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    @Value("${video.storage.local.directory}")
    private String localDirectory;
    @Override
    public String uploadFile(MultipartFile file) {
        String originalFileName= file.getOriginalFilename();
        Path pathFile= Paths.get(localDirectory, originalFileName);
        var fileNameExtension= StringUtils.getFilenameExtension(originalFileName);

        var key= localDirectory+ "/"+originalFileName;

        try {
            file.transferTo(pathFile.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return key;
    }
}
