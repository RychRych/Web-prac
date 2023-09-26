package webprac.webprac.models;

import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EnableAutoConfiguration

public class Film implements GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "production_company")
    private String production_company;

    @Column(nullable = false, name = "director")
    private String director;

    @Column(nullable = false, name = "year_of_release")
    private Integer year_of_release;

    @Column(nullable = false, name = "available_cd")
    private Integer available_cd;

    @Column(nullable = false, name = "price_of_cd")
    private Double price_of_cd;

    @Column(nullable = false, name = "available_cassette")
    private Integer available_cassette;

    @Column(nullable = false, name = "price_of_cassette")
    private Double price_of_cassette;

    @Column(nullable = false, name = "total_of_cd")
    private Integer total_of_cd;

    @Column(nullable = false, name = "total_of_cassette")
    private Integer total_of_cassette;

    public Film() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film other = (Film) o;
        return Objects.equals(id, other.id)
                && Objects.equals(title, other.title)
                && Objects.equals(production_company, other.production_company)
                && Objects.equals(director, other.director)
                && Objects.equals(year_of_release, other.year_of_release)
                && Objects.equals(available_cd, other.available_cd)
                && Objects.equals(price_of_cd, other.price_of_cd)
                && Objects.equals(available_cassette, other.available_cassette)
                && Objects.equals(price_of_cassette, other.price_of_cassette)
                && Objects.equals(total_of_cd, other.total_of_cd)
                && Objects.equals(total_of_cassette, other.total_of_cassette);
    }
}
