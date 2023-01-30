package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.*;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class PlayListApi {
    @Step
    public static Response post(Playlist requestPlayList) {
        return RestResource.post(USERS + DataLoader.createInstance().getUserID() + PLAYLISTS, getToken(), requestPlayList);
    }

    public static Response post(Playlist requestPlayList, String token) {
        return RestResource.post(USERS + DataLoader.createInstance().getUserID() + PLAYLISTS, token, requestPlayList);
    }

    public static Response get(String PlayListID) {
        return RestResource.get(PLAYLISTS + PlayListID, getToken());
    }

    public static Response update(Playlist requestPlayList, String PlayListID) {
        return RestResource.update(PLAYLISTS + PlayListID, getToken(), requestPlayList);
    }

}
