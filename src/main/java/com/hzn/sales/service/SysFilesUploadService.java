package com.hzn.sales.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SysFilesUploadService {
    public String upload(List<MultipartFile> files);
}
