package org.yandrut.data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeleteUserResponse {
     private Integer code;
     private String type;
     private String message;
}
