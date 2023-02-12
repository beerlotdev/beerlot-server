package com.beerlot.domain.file;

import com.beerlot.domain.file.dto.FileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "File API", description = "The File API.")
@RequestMapping("/api/v1/files")
public interface FileApi {

    @Operation(description = "Upload file to storage")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Success."),
                    @ApiResponse(responseCode = "400", description = "No enum constant {requested value}."),
            }
    )
    @PostMapping(value = "/{fileDirectory}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<FileResponse> uploadFile (
            @Parameter(description = "file directory") @PathVariable("fileDirectory") String fileDirectory,
            @RequestPart("file") MultipartFile file
    );
}
