package online.happyclinic.data;

import online.happyclinic.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, String> {

}
