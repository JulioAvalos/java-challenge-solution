package com.applaudo.movies.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "likes")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;

    private String title;
    @Lob
    private String description;
    @Lob
    private String imageUrl;
    private Integer stock;
    private BigDecimal rentalPrice;
    private BigDecimal salePrice;
    private Boolean availability;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_likes",
            joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "idMovie"),
            inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "idUser"))
    private Set<User> likes;
    
}
