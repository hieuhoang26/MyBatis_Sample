package vn.hhh.noti.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hhh.noti.utils.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private Long id;
    private String title;
    private MultipartFile imageUrl;
    private String content;
    private Status status;
}
