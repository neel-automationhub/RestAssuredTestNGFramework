package spotify.oauth2.tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import spotify.oauth2.api.applicationApi.PlaylistAPI;
import spotify.oauth2.pojo.Playlist;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static spotify.oauth2.api.SpecBuilder.getResponseSpec;

public class PlayListTest {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String accessToken = "BQByAkJ5KEA3F3LrIN7RRy_588eawBwkqbMaG0vJ5aUkn4bF_Lm206lcAdXRtXuF1gXNn9wNYYVTC6x-v0sTOY2CDyltMPD2sNjhwW0ZNp9zioc9n_5CIRyVqyfMcenb8odzNqzwqWtRWhsrFSCUSfHxpiAaLhMl8fUAG2oRY9ZGQ-kTsW9xZkOxCV5q74YGDOO5QtiWQ_OasQSUHSTYFZbXlwDpIpxVzhL8aAUWqIKSRDfTpo0WmZpC25XKQVZ67HlRxai_yyJPLQ";

    // Moved in another class for reuse purpose...
   /* @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder spec = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer "+ accessToken)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL);

        requestSpecification = spec.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                *//*.expectContentType(ContentType.JSON)*//*
                .log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();
    }*/

    @Test(enabled = false)
    public void createPlayList() {
        String payload = "{\n" +
                "    \"name\": \"New Playlist\",\n" +
                "    \"description\": \"New Playlist Description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(getRequestSpec())
                .body(payload)
                .when().post("users/2uprmys8eup1zwgcv1wt3b1ej/playlists")
                .then().spec(getResponseSpec())
                .assertThat().statusCode(201)
                .body("name", equalTo("New Playlist"),
                        "description", equalTo("New Playlist Description"),
                        "public", equalTo(false));
    }

    @Test(enabled = false)
    public void getPlayList() {
        given(getRequestSpec())
                .pathParam("playlist_id", "2VqVk9cGJaV9gau4zYzw2p")
                .when().get("/playlists/{playlist_id}")
                .then().spec(getResponseSpec())
                .assertThat().statusCode(200);
    }

    @Test(enabled = false)
    public void updatePlayList() {
        String payload = "{\n" +
                "    \"name\": \"Updated Playlist\",\n" +
                "    \"description\": \"Updated Playlist Description\",\n" +
                "    \"public\": false\n" +
                "}";
        given(getRequestSpec())
                .body(payload)
                .when().put("/playlists/2VqVk9cGJaV9gau4zYzw2p")
                .then().spec(getResponseSpec())
                .assertThat().statusCode(200);
    }

    @Test(enabled = false)
    public void validateNegativeTest() {
        String payload = "{\n" +
                "    \"name\": \"\",\n" +
                "    \"description\": \"Updated Playlist Description\",\n" +
                "    \"public\": false\n" +
                "}";

        given(getRequestSpec())
                .body(payload)
                .when().post("users/2uprmys8eup1zwgcv1wt3b1ej/playlists")
                .then().spec(getResponseSpec())
                .assertThat().statusCode(400)
                .body("error.status", equalTo(400),
                        "error.message", equalTo("Missing required field: name"));

    }

// --------------- Api Automation Via POJO Class ----------------
    @Test
    public void validateCreatePlaylistViaPojo() {
        Playlist playlist = new Playlist();
        playlist.setName("New Playlist");
        playlist.setDescription("New Playlist Description");
        playlist.set_public(false);

        Response response = PlaylistAPI.post(playlist);
        assertThat(response.statusCode(), equalTo(201));

        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(playlist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(playlist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(playlist.get_public()));
    }

    @Test
    public void validateGetPlaylistViaPojo() {
        Playlist playlist = new Playlist();
        playlist.setName("Updated Playlist");
        playlist.setDescription("Updated Playlist Description");
        playlist.set_public(false);

        Response response = PlaylistAPI.get("756TXamycM5NjLG8QBK6lL");
        assertThat(response.statusCode(), equalTo(200));

        Playlist responsePlaylist = response.as(Playlist.class);

        assertThat(responsePlaylist.getName(), equalTo(playlist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(playlist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(playlist.get_public()));
    }

    @Test
    public void validateUpdatePlaylistViaPojo() {
        Playlist playlist = new Playlist();
        playlist.setName("Updated Playlist");
        playlist.setDescription("Updated Playlist Description");
        playlist.set_public(false);

        Response response = PlaylistAPI.update("756TXamycM5NjLG8QBK6lL", playlist);
        assertThat(response.statusCode(), equalTo(200));

    }

}
