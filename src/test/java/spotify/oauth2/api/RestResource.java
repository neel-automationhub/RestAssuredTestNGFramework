package spotify.oauth2.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static spotify.oauth2.api.SpecBuilder.*;

public class RestResource {

    public static Response postToken(HashMap<String, String> formParams) {
        return given(getAccountRequestSpec())
                .formParams(formParams)
                .when().post("/api/token")
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }
    public static Response post(String path, String token, Object playlist) {
        return given(getRequestSpec())
                .body(playlist)
                .header("Authorization", "Bearer " + token)
                .when().post(path)
                .then().spec(getResponseSpec())
                .log().all()
                .extract()
                .response();
    }

    public static Response get(String path, String token) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .when().get(path)
                .then().spec(getResponseSpec())
                .log().all()
                .extract()
                .response();
    }

    public static Response update(String path, String token, Object playlist) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(playlist)
                .when().put(path)
                .then().spec(getResponseSpec())
                .log().all()
                .extract()
                .response();
    }

}
