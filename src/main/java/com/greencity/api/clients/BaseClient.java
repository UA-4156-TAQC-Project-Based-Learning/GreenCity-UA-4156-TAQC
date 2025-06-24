package com.greencity.api.clients;

import com.greencity.api.models.user.ResponseSignIn;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class BaseClient {
    protected final String baseAPIUrl;
    protected final ContentType contentType;
    @Setter
    @Getter
    protected ResponseSignIn signIn;

    public BaseClient(String baseUrl) {
        this.baseAPIUrl = baseUrl;
        contentType = ContentType.JSON;
    }
    public BaseClient(String baseUrl, ResponseSignIn signIn) {
        this.baseAPIUrl = baseUrl;
        this.contentType = ContentType.JSON;
        this.signIn = signIn;
    }

    public BaseClient(String baseUrl, ContentType contentType) {
        this.baseAPIUrl = baseUrl;
        this.contentType = contentType;
    }

    public BaseClient(String baseUrl, String contentType) {
        this.baseAPIUrl = baseUrl;
        this.contentType = ContentType.valueOf(contentType);
    }


    protected RequestSpecification preparedRequest() {
        RequestSpecification request = RestAssured.given()
//                .log()
//                .body()
                .baseUri(baseAPIUrl)
                .contentType(contentType);
        if (signIn != null) {
            request.header("Authorization", "Bearer " + signIn.getAccessToken());
        }
        return request;
    }

    protected RequestSpecification preparedMultipartRequest() {
        RequestSpecification request = RestAssured.given()
                .baseUri(baseAPIUrl);

        if (signIn != null) {
            request.header("Authorization", "Bearer " + signIn.getAccessToken());
        }

        return request;
    }
}