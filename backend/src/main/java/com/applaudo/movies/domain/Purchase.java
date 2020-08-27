package com.applaudo.movies.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"movie", "user"})
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPurchase;

    @ManyToOne
    @JoinColumn(name = "id_movie", nullable = false, foreignKey = @ForeignKey(name = "fk_movie_purchase"))
    private Movie movie;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_user_purchase"))
    private User user;

    private Integer quantity;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime purchaseDate;

}
