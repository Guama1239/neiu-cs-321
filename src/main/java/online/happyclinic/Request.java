package online.happyclinic;
//domain class
// Equivalent to the Taco.java class
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
// Do not have to worry about the no arg constructor because
// we want to it to be a public available nor arg constructor which comes with @Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createdAt;

    @NotNull(message = "Name is required")
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = RequestType.class)
    @NotEmpty(message = "You must choose at least 1 type of facility")
    private List<RequestType> RequestsInfo;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

}
