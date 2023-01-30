package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.api.Route.*;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static com.spotify.oauth2.utils.FakerUtils.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@Epic("Spotify oAuth2.0")
@Feature("Playlist api")
public class PlayListApiTests extends BaseTest{

    @Step
    public Playlist playlistBuilder(String name, String desc, boolean _public) {
        /*This is Lombok without builder pattern.*/
        /*Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setDescription(desc);
        playlist.set_public(_public);
        return playlist; */

        /*This is Lombok with builder pattern.*/
        return Playlist.builder()
                .name(name)
                .description(desc)
                ._public(_public).build();
    }

    @Story("To test for release 20.1.3")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @TmsLink("test-1")
    @Description("This is allure description: create playlist")
    @Test(description = "Create playlist")
    public void test_01_createPlaylist() {
        Playlist requestPlayList = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlayListApi.post(requestPlayList);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        //assertThat(response.statusCode(), equalTo(StatusCode.CODE_201));
        assersionPlaylistEqual(requestPlayList, response.as(Playlist.class));
    }

    @Description("This is allure description: get playlist")
    @Test(description = "get the created playlist")
    public void test_02_getPlaylist() {
        Playlist requestPlayList = playlistBuilder("Updated Playlist", "Updated playlist description", false);
        Response response = PlayListApi.get("18BphFccxzPMIY6TdgU4Xa");
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assersionPlaylistEqual(requestPlayList, response.as(Playlist.class));
    }

    @Story("To test for release 20.1.3")
    @Description("This is allure description: update playlist")
    @Test(description = "update the playlist")
    public void test_03_updatePlaylist() {
        Playlist requestPlayList = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlayListApi.update(requestPlayList, DataLoader.createInstance().getPlayListID());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    //-ve scenarios
    @Description("This is allure description: Create palylist with empty name")
    @Test(description = "Create palylist with empty name")
    public void test_04_createPlaylistWithEmptyName() {
        Playlist requestPlayList = playlistBuilder("", "New playlist description", false);
        Response response = PlayListApi.post(requestPlayList);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Description("This is allure description: create playlist with invalid token")
    @Test(description = "create playlist with invalid token")
    public void test_05_createPlaylistWithInvalidToken() {
        Playlist requestPlayList = playlistBuilder("New Playlist", "New playlist description", false);
        Response response = PlayListApi.post(requestPlayList, "12345");

        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(response.as(Error.class), StatusCode.CODE_401);

    }


    @Step
    public void assersionPlaylistEqual(Playlist requestPlayList, Playlist responsePlaylist) {
        assertThat(requestPlayList.getName(), equalTo(responsePlaylist.getName()));
        assertThat(requestPlayList.getDescription(), equalTo(responsePlaylist.getDescription()));
        assertThat(requestPlayList.get_public(), equalTo(responsePlaylist.get_public()));
    }

    public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
        assertThat(actualStatusCode, equalTo(statusCode.code));
    }

    public void assertError(Error responseErr, StatusCode statusCode) {
        assertThat(responseErr.getError().getStatus(), equalTo(statusCode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statusCode.msg));
    }

}
