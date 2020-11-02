package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Order;
import online.happyclinic.data.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/primarytableview")

public class PrimaryTableController {
    private final OrderRepository orderRepo;

    public PrimaryTableController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public String displayPtable() { return "primarytableview";}


    @ModelAttribute("orders")
    public List<Order> orders(){return orderRepo.findAll();}


}
