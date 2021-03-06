package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Order;
import online.happyclinic.User;
import online.happyclinic.data.OrderRepository;
import online.happyclinic.data.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private OrderProperties orderProperties;

    public PrimaryTableController(OrderRepository orderRepo, OrderProperties orderProperties) {
        this.orderRepo = orderRepo;
        this.orderProperties = orderProperties;
    }

    @GetMapping
    public String displayPtable() { return "primarytableview";}


    @ModelAttribute
    public void addUser(Model model, @AuthenticationPrincipal User user){
        String username = user.getUsername();
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize());
        model.addAttribute("username", username);
        List <Order> orders = orderRepo.findAllByUser(user, pageable);
        model.addAttribute("orders", orders);
        model.addAttribute("username", username);
    }



}
