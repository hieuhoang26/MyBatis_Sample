package vn.hhh.noti.Dto;

import lombok.*;
import vn.hhh.noti.Utils.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private Long id;
    private String title;
    private String imageUrl;
    private String content;
    private Status status;
}
