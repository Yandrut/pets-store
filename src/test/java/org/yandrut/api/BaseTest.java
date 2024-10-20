package org.yandrut.api;

import org.junit.jupiter.api.BeforeEach;
import org.yandrut.data.Specifications;
import org.yandrut.utils.DataReader;

public class BaseTest {
    private static final String BASE_URL = DataReader.getTestData("endpoints.base_url");

    @BeforeEach
    public void buildAndSubmitSpecifications() {
        Specifications.buildSpecifications(Specifications.requestSpec(BASE_URL),
                Specifications.responseSpec(200));
    }
}