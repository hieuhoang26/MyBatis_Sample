package vn.hhh.noti.Model;

import lombok.Getter;
import lombok.Setter;
import vn.hhh.noti.Utils.Status;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notification {
    private Long id;
    private String title;
    private String imageUrl;
    private String content;
    private Status status;
    private LocalDateTime pushedAt;
    private LocalDateTime createdAt;
}
