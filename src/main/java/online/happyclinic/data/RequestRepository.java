package online.happyclinic.data;

import org.springframework.data.repository.CrudRepository;
import online.happyclinic.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {

}
