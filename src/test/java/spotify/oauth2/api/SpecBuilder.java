package spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    static String accessToken = "BQAH__Q0ceVlDCtonSAfcNrNy0Ec-0dYEjSyzjBNl0SA4i64xbYg077sXe3MzNQgsF_nhzvqW35e73wR1YgIbgfCSIrblqRxK1oBMr2RFZNjqmztV6gXZl-aT1m3r1HnkwlB9r9N4M3REQs0lP0tGkOeDHjfdfcKgANgebnlyXsckRxXfh114qsFqg-op4WBEVySATVYniZ6_J1e4GvGk0bmJrQ7ti1I-tH_CCsRBJ34Ub93QYhWZ9AKPj8gT4O2NX-WrLUTykswnQ";

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
//                addHeader("Authorization", "Bearer "+ accessToken).
        setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }

    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://accounts.spotify.com").
                setContentType(ContentType.URLENC).
                log(LogDetail.ALL).
                build();
    }
}
