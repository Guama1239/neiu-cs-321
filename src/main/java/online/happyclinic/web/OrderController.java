package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Order;
import online.happyclinic.User;
import online.happyclinic.data.OrderRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo)
    {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String requestForm(Model model, @AuthenticationPrincipal User user) {
        addUserInfoToModel(model, user);
        return "registerform";
    }

    private void addUserInfoToModel(Model model, User user) {
        model.addAttribute("fullName", user.getFullName());
        model.addAttribute("street", user.getStreet());
        model.addAttribute("city", user.getCity());
        model.addAttribute("state", user.getState());
        model.addAttribute("zip", user.getZip());
    }

    @ModelAttribute
    public void addAttributes(Model model) { model.addAttribute("order", new Order()); }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order")Order order, Errors errors, SessionStatus sessionStatus, Model model,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors())
            return "registerform";

        order.setUser(user);
        orderRepo.save(order);
        log.info("Order submitted" + order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
