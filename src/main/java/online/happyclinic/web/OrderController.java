package online.happyclinic.web;

import lombok.extern.slf4j.Slf4j;
import online.happyclinic.Order;
import online.happyclinic.data.OrderRepository;
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
    public String requestForm() {
        return "registerform";
    }

    @ModelAttribute
    public void addAttributes(Model model) { model.addAttribute("order", new Order()); }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order")Order order, Errors errors, SessionStatus sessionStatus, Model model) {
        if (errors.hasErrors())
            return "registerform";

        orderRepo.save(order);
        log.info("Order submitted" + order);
        sessionStatus.setComplete();
        //model.addAttribute("title", "Thank you for your submission");
        return "redirect:/";  //"home"; // I am sorry, I could not find out in the docs how to send this message "redirect/:"
    }
}
