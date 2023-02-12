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

    public FileResponse uploadFile(FileDirectoryType fileDirectory, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null) {
            throw new IllegalArgumentException(ErrorMessage.FILE__FILENAME_NULL.getMessage());
        }
       return FileResponse.of(bucketUtils.uploadFile(fileDirectory, file));
    }
}
