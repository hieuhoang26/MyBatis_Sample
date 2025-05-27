package vn.hhh.noti.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequest {
    @NotBlank
    String username;

    @NotBlank
    String password;
}
