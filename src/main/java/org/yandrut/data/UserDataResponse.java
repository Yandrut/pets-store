package org.yandrut.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class UserDataResponse {
    private Integer code;
    private String type;
    private String message;
}
