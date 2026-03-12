package com.udemy.spring_boot.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class UploadFileResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;
    private String FileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponseDTO() {
    }

    public UploadFileResponseDTO(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        FileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return FileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        FileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UploadFileResponseDTO that = (UploadFileResponseDTO) o;
        return getSize() == that.getSize() && Objects.equals(getFileName(), that.getFileName()) && Objects.equals(getFileDownloadUri(), that.getFileDownloadUri()) && Objects.equals(getFileType(), that.getFileType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getFileDownloadUri(), getFileType(), getSize());
    }
}
