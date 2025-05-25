package vn.hhh.noti.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String upload(MultipartFile file);
    void deleteFile(String key);
}
