package vn.hhh.noti.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import vn.hhh.noti.utils.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notification {
    private Long id;
    private String title;
    private String imageUrl;
    private String content;
    private Status status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pushedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
