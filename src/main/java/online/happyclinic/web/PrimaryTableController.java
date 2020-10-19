package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Order;
import online.happyclinic.data.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
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
    public String displayPtable(Model model) {
        model.addAttribute("title", "Database Order Table Info");
        return "primarytableview";}


    @ModelAttribute("orders")
    public List<Order> orders(){return orderRepo.findAll();}


}