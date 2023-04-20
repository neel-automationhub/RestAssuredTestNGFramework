package spotify.oauth2.api;

import io.restassured.response.Response;
import spotify.oauth2.utils.ConfigLoader;

import java.time.Instant;
import java.util.HashMap;

import static spotify.oauth2.api.RestResource.postToken;

public class TokenManager {

    private static String accessToken;
    private static Instant expiryTime;

    public static String getToken() {
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)) {
                System.out.println("Renewing token...");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use...");
            }
        } catch (Exception e) {
            throw new RuntimeException("ABORT!!! Failed to get token");
        }
        return accessToken;
    }

    public static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());

        Response response = postToken(formParams);

        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT!!! Renew Token Failed.!");
        }

        return response;
    }
}
