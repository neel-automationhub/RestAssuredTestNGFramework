package spotify.oauth2.api.applicationApi;

import io.restassured.response.Response;
import spotify.oauth2.api.RestResource;
import spotify.oauth2.pojo.Playlist;

import static spotify.oauth2.api.TokenManager.getToken;

public class PlaylistAPI {

    //public static String accessToken = "BQChURTv4VGS4hO3kvhhDaSIt0mV95I73LvxjWcsGmSOMyIqyh9Vf5GfMV1oE9htR-10jr7puMw5OAFvpj3WGfpb3tiHokfwnJGF_5w8nPRM67UrsexVEUCm_FZ2FuEiYnpVo7NMJlwUBpo1QbLIOfXRCOXKtKpymMigYHVIIp9MQ66qbxQ1gaLDXaspCWv8W7Sep9Im5PGKzO6naZgwrqDXxP2gmcxQCK8QITPLf1RbfnlzP5WOCrilamxklkKFBdndm48QvgUk7g";
    public static Response post(Playlist playlist) {

        return RestResource.post("users/2uprmys8eup1zwgcv1wt3b1ej/playlists", getToken(), playlist);

        /*return given(getRequestSpec())
                .body(playlist)
                .when().post("users/2uprmys8eup1zwgcv1wt3b1ej/playlists")
                .then().spec(getResponseSpec())
                .extract()
                .response();*/
    }

    public static Response get(String playlistId) {

        return RestResource.get("/playlists/"+playlistId, getToken());

        /*return given(getRequestSpec())
                .pathParam("playlist_id", playlistId)
                .when().get("/playlists/{playlist_id}")
                .then().spec(getResponseSpec())
                .extract()
                .response();*/
    }

    public static Response update(String playlistId, Playlist playlist) {

        return RestResource.update("/playlists/"+ playlistId, getToken(), playlist);

        /*return given(getRequestSpec())
                .body(playlist)
                .when().put("/playlists/"+ playlistId)
                .then().spec(getResponseSpec())
                //.contentType("\"application/json\"")
                .extract()
                .response();*/
    }
}
