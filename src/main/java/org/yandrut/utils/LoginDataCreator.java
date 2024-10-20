package org.yandrut.utils;

import org.yandrut.data.LoginData;

public class LoginDataCreator {

    private static final String username = DataReader.getTestData("user.username");
    private static final String email = DataReader.getTestData("user.password");

    private LoginDataCreator() {}

    public static LoginData createLoginData() {
        return new LoginData(username, email);
    }
}
