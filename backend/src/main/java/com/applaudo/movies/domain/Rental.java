package com.applaudo.movies.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"user", "movie"})
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRent;

    @ManyToOne
    @JoinColumn(name = "id_movie", nullable = false, foreignKey = @ForeignKey(name = "fk_movie_rent"))
    private Movie movie;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_user_rent"))
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime rentalDate;

    private BigDecimal penaltyPaid;

    private Boolean movieReturned;

    private LocalDateTime returnDate;

}
