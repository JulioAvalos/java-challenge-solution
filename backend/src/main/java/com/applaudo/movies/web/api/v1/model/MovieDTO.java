package com.applaudo.movies.web.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Movie's Information")
public class MovieDTO {

    @ApiModelProperty(notes = "Movie's title")
    private String title;

    @ApiModelProperty(notes = "movie's description")
    private String description;

    @ApiModelProperty(notes = "quantity of this movie available for sale")
    private Integer stock;

    @ApiModelProperty(notes = "movie's rental price")
    @JsonProperty(value = "rental_price")
    private BigDecimal rentalPrice;

    @ApiModelProperty(notes = "movie's sale price")
    @JsonProperty(value = "sale_price")
    private BigDecimal salePrice;

    @ApiModelProperty(notes = "image url loaded from another server")
    @JsonProperty(value = "image_url")
    private String imageUrl;

    @ApiModelProperty(notes = "url given from where is this resource located")
    @JsonProperty(value = "movie_url")
    private String movieUrl;

    @ApiModelProperty(notes = "indicates if this movie is available for rental/sale")
    private Boolean availability;

    @ApiModelProperty(notes = "popularity of this movie")
    @JsonProperty(value = "likes")
    private Integer numberOfLikes;

}
