package com.india.railway.service.mysql;

import org.apache.http.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.india.railway.model.mysql.FileUpload;
import com.india.railway.repository.mysql.FileUploadRepository;

@Service
public class FileService {

    @Autowired
    FileUploadRepository fileRepository;

    public FileUpload storeFile(MultipartFile file) throws Exception {
        FileUpload fileEntity = new FileUpload();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setType(file.getContentType());
        fileEntity.setData(file.getBytes());
        return fileRepository.save(fileEntity);
    }

    public FileUpload getFile(Long id) {
        return fileRepository.findById(id).orElse(null);
    }
}
