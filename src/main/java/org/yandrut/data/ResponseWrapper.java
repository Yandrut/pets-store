package org.yandrut.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class ResponseWrapper {
  private Integer code;
  private String type;
  private String message;
}
