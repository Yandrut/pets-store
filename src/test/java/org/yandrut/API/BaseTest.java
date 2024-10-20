package org.yandrut.API;

import org.testng.annotations.BeforeMethod;
import org.yandrut.data.Specifications;
import org.yandrut.utils.DataReader;

public class BaseTest {
    private static final String BASE_URL = DataReader.getTestData("endpoints.base_url");

    @BeforeMethod
    public void buildAndSubmitSpecifications() {
        Specifications.buildSpecifications(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(200));
    }
}
