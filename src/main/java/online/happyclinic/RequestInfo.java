package online.happyclinic;

import lombok.Data;

@Data
public class RequestInfo {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        FACILITY_TYPE, MEDICAL_SERVICES;
    }
}
