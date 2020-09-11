package online.happyclinic;

import lombok.Data;

@Data
public class RequestInfo {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        GENERAL_PRACTICE, DENTAL_PRACTICE, PODIATRY_PRACTICE,
        OPTICAL_CLINIC, ORTHOPEDIC_PRACTICE, MENTAL_HEALTH_PRACTICE
    }
}
