package com.india.railway.dto;



public class FileResponse {

    private Long id;
    private String fileName;
    private String contentType;
    private Long fileSize;

    public FileResponse() {
    }

    public FileResponse(Long id, String fileName,
                        String contentType, Long fileSize) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }

    public FileResponse(Long id, String fileName,
                        String contentType) {
        this.id = id;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
