
package com.spotify.oauth2.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value //his will do the job of @Data +  remove  identifier from all the variable
//@Data //This will do the job of @Getters @Setters + some useful methods
@Builder  // This will work with builder
@Jacksonized // This will consider the jacksonInclude annotations
//@Getter @Setter // This will remove the getters and setters
@JsonInclude(JsonInclude.Include.NON_NULL) //This will add only non_null values in the req. Null values are ignored
public class Playlist {
    @JsonProperty("collaborative")
     Boolean collaborative;
    @JsonProperty("description")
     String description;
    @JsonProperty("external_urls")
     ExternalUrls externalUrls;
    @JsonProperty("followers")
     Followers followers;
    @JsonProperty("href")
     String href;
    @JsonProperty("id")
     String id;
    @JsonProperty("images")
     List<Object> images;
    @JsonProperty("name")
     String name;
    @JsonProperty("owner")
     Owner owner;
    @JsonProperty("primary_color")
     Object primaryColor;
    @JsonProperty("public")
     Boolean _public;
    @JsonProperty("snapshot_id")
     String snapshotId;
    @JsonProperty("tracks")
     Tracks tracks;
    @JsonProperty("type")
     String type;
    @JsonProperty("uri")
     String uri;
}
