package com.india.railway.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.mysql.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}
