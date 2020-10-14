package online.happyclinic;
// Equivalent of ingredient
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
// We are not making RequestType instances and the @Entity requires to have a protected or public constructor
// that is the reason we need a no arg constructor below
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor

public class RequestType {

    @Id
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;

    public static enum Type {
        FACILITY_TYPE, MEDICAL_SERVICES
    }
}
