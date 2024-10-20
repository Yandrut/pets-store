package org.yandrut.utils;

import org.yandrut.data.UserData;

public class UserDataCreator {

    private static final String id = DataReader.getTestData("user.id");
    private static final String username = DataReader.getTestData("user.username");
    private static final String firstName = DataReader.getTestData("user.firstName");
    private static final String lastName = DataReader.getTestData("user.lastName");
    private static final String email = DataReader.getTestData("user.email");
    private static final String password = DataReader.getTestData("user.password");
    private static final String phone = DataReader.getTestData("user.phone");
    private static final String userStatus = DataReader.getTestData("user.userStatus");

    private UserDataCreator(){}

    public static UserData createUser() {
        return new UserData(
                Integer.parseInt(id),
                username,
                firstName,
                lastName,
                email,
                password,
                phone,
                Integer.parseInt(userStatus)
        );
    }
}