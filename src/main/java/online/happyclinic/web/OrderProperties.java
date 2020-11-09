package online.happyclinic.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "facility.orders")
@Data
public class OrderProperties {

    private int pageSize;
}
