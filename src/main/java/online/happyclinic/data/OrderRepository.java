package online.happyclinic.data;

import online.happyclinic.Order;
import online.happyclinic.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByUser(User user);
    // findAllByUserOrderByPlacedAt(User user, Order order);
}
