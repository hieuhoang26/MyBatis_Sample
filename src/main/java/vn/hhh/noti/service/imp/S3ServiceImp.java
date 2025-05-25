package vn.hhh.noti.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import vn.hhh.noti.service.S3Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class S3ServiceImp implements S3Service {

    private final S3Client s3Client;
    private final Region region;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    @Override
    public String upload(MultipartFile file) {
        log.info("Uploading file {} to Amazon S3", file.getOriginalFilename());

        String key = Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "_");
        ZoneId zoneId = ZoneId.of("UTC+7");
        LocalDate date = ZonedDateTime.now(zoneId).toLocalDate();

        try {
            PutObjectRequest.Builder requestBuilder = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType());

//            if (publicObject) {
//                requestBuilder.acl(ObjectCannedACL.PUBLIC_READ);
//            }

            s3Client.putObject(requestBuilder.build(), RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region.id(), key);
            return fileUrl;
        } catch (IOException e) {
            log.error("Failed to upload file: {}", e.getMessage(), e);
            throw new RuntimeException("File upload failed");
        }
    }

    @Override
    public void deleteFile(String key) {
        s3Client.deleteObject(DeleteObjectRequest.builder().bucket(bucketName).key(key).build());
    }
}
