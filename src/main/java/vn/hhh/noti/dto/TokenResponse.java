package vn.hhh.noti.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse implements Serializable {
    private Long id;
    private String role;
    private String accessToken;
    private String refreshToken;
    private String message;

}
