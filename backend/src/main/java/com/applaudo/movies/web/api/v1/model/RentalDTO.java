package com.applaudo.movies.web.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDTO {

    @JsonProperty(value = "id_movie")
    private Long idMovie;
    @JsonProperty(value = "id_user")
    private Long idUser;

}
