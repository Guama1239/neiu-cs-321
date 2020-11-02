package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Order;
import online.happyclinic.User;
import online.happyclinic.data.OrderRepository;
import online.happyclinic.data.ServiceRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @ModelAttribute
    public void addUser(Model model, @AuthenticationPrincipal User user){
        String username = user.getUsername();
        model.addAttribute("username", username);
    }

    @ModelAttribute("orders")
    public List<Order> orders(){return orderRepo.findAll();}


}
