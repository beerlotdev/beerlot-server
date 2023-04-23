package com.beerlot.domain.file.service;

import com.beerlot.domain.file.FileDirectoryType;
import com.beerlot.domain.file.dto.FileResponse;
import com.beerlot.domain.file.util.BucketUtils;
import com.beerlot.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Transactional
public class FileService {

    private final BucketUtils bucketUtils;

    public FileResponse uploadFiles(FileDirectoryType fileDirectory, MultipartFile[] files) {
        FileResponse fileResponse = new FileResponse();

        for (MultipartFile file : files) {
            fileResponse.addUrl(uploadSingleFile(fileDirectory, file));
        }
        return fileResponse;
    }

    private String uploadSingleFile(FileDirectoryType fileDirectory, MultipartFile file){
        if (file.getOriginalFilename() == null) {
            throw new IllegalArgumentException(ErrorMessage.FILE__FILENAME_NULL.getMessage());
        }
        return bucketUtils.uploadFile(fileDirectory, file);
    }
}