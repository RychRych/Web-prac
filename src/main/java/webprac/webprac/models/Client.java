package webprac.webprac.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Objects;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@AllArgsConstructor
@EnableAutoConfiguration
public class Client implements GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "full_name")
    private String clientName;

    @Column(nullable = false, name = "address")
    private String clientAddress;

    @Column(nullable = false, name = "phone_number")
    private String clientPhone;

    public Client() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client other = (Client) o;
        return Objects.equals(id, other.id)
                && Objects.equals(clientName, other.clientName)
                && Objects.equals(clientAddress, other.clientAddress)
                && Objects.equals(clientPhone, other.clientPhone);
    }

}
