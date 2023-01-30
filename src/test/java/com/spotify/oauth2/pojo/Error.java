package com.spotify.oauth2.pojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

//@Data
@Builder
@Jacksonized
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("error")
        private InnerError error;
}
