package online.happyclinic.data;

import online.happyclinic.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, String> {
//    List<Service> deleteById(String s);
}
