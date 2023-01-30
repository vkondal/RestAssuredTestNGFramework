package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

public class TokenManager {
    private static String access_token;
    private static String expiry_time;

    @Step
    public synchronized static String getToken() {
        try {
            if (access_token == null || Instant.now().isAfter(Instant.parse(expiry_time))) {
                System.out.println("Renewing token ... ");
                Response response = renewToken();
                //access_token= response.jsonPath().getJsonObject("access_token"); OR below same thing..
                access_token = response.path("access_token");
                //Now get the expiry time of the token ....
                int expiryDurationInSecs = response.path("expires_in"); //expiryDurationInSecs in seconds
                //Not we need to get the current time in secs
                expiry_time = String.valueOf(Instant.now().plusSeconds(expiryDurationInSecs - 200));
            } else {
                System.out.println("token is good to use ... ");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("ABORT !!! Token renew failed.. ");
        }
        return access_token;
    }


    private static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("grant_type", ConfigLoader.createInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.createInstance().getRefreshToken());
        formParams.put("client_id", ConfigLoader.createInstance().getClientID());
        formParams.put("client_secret", ConfigLoader.createInstance().getClientSecret());
        Response response = RestResource.postAccount(formParams);
        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT !!! Token renew failed.. ");
        }

        return response;
    }
}
