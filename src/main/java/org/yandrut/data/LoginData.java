package org.yandrut.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginData {
    private final String username;
    private final String password;
}