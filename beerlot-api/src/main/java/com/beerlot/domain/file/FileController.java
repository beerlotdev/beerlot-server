package com.beerlot.domain.file;

import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.file.dto.FileResponse;
import com.beerlot.domain.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileController implements FileApi {

    private final FileService fileService;

    @Override
    public ResponseEntity<FileResponse> uploadFile(OAuthUserPrincipal userPrincipal,
                                                   String fileDirectory,
                                                   MultipartFile file) {
        return new ResponseEntity<>(fileService.uploadFile(FileDirectoryType.valueOf(fileDirectory.toUpperCase()), file),
                HttpStatus.OK);
    }
}
