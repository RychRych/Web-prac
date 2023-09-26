package webprac.webprac.models;

import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import jakarta.persistence.*;
import java.util.Objects;
import java.sql.Date;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EnableAutoConfiguration

public class Transaction implements GenericModel {
    public enum CarrierType{
        CD("cd"),
        CASSETTE("cassette");
        private final String code;

        private CarrierType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "client_id")
    private Integer client_id;

    @Column(nullable = false, name = "film_id")
    private Integer film_id;

    @Column(nullable = false, name = "film_title")
    private String film_title;

    @Column(nullable = false, name = "type_of_carrier")
//    @Column(nullable = false, name = "type_of_carrier", columnDefinition = "carrier_type")
//    @Enumerated(EnumType.STRING)
//    @Convert(converter = CarrierTypeConverter.class)
    private String  type_of_carrier;

    @Column(nullable = false, name = "rent_price")
    private Double rent_price;

    @Column(nullable = false, name = "date_of_lease")
    private Date date_of_lease;

    @Column(nullable = true, name = "date_of_return")
    private Date date_of_return;

    public Transaction() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction other = (Transaction) o;
        return Objects.equals(id, other.id)
                && Objects.equals(client_id, other.client_id)
                && Objects.equals(film_id, other.film_id)
                && Objects.equals(film_title, other.film_title)
                && Objects.equals(type_of_carrier, other.type_of_carrier)
                && Objects.equals(rent_price, other.rent_price)
                && Objects.equals(date_of_lease, other.date_of_lease)
                && Objects.equals(date_of_return, other.date_of_return);
    }

}
