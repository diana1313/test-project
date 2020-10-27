package pet.store.api.specifiactions;

import helpers.PropertiesReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class InitSpecification {
    public static RestAssuredConfig getCustomTimeOut(RestAssuredConfig restAssuredConfig) {
        return restAssuredConfig.httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", 30_000)
                .setParam("http.socket.timeout", 100_000));
    }

    public static RequestSpecification basePetSpecification = new RequestSpecBuilder()
            .setConfig(getCustomTimeOut(RestAssuredConfig.config()))
            .setContentType(ContentType.JSON)
            .setBaseUri(PropertiesReader.getPropertyValue("pet.endpoint"))
            .addHeader("Content-Type", "application/json")
            .build();
}
