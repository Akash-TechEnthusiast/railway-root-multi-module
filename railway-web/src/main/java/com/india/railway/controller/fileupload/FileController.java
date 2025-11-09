package com.india.railway.controller.fileupload;

import org.apache.http.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.india.railway.model.mysql.FileUpload;
import com.india.railway.repository.mysql.FileUploadRepository;
import com.india.railway.service.mysql.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileUpload savedFile = fileService.storeFile(file);
            return ResponseEntity.ok("File uploaded with ID: " + savedFile.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        FileUpload fileEntity = fileService.getFile(id);
        if (fileEntity == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .body(fileEntity.getData());
    }
}
