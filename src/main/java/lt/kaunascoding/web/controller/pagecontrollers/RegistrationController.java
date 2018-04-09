package lt.kaunascoding.web.controller.pagecontrollers;

import lt.kaunascoding.web.controller.userControl.UserController;
import lt.kaunascoding.web.model.mysql.classes.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Time;
import java.sql.Timestamp;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String greeting(Model model) {
        model.addAttribute("registrationForm", new User());
        model.addAttribute("error", "");
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String greeting(@ModelAttribute("loginForm") User vartotojas,
                           Model model) {
        UserController control = new UserController();
        String returnPage;
        boolean isError = false;
        String error = "";
        if (vartotojas != null && control.isEmailGood(vartotojas.getEmail())) {
            if (vartotojas.getPassword().length() > 6) {
                if (control.Register(vartotojas)) {
                    isError = false;
                } else {
                    isError = true;
                    error = "Toks slapyvardis jau egzistuoja, rinkitės kitą: ";
                }
            } else {
                isError = true;
                error = "Slaptažodis negali būti sudarytas iš mažiau nei 6 simbolių";
            }

        } else {
            isError = true;
            error = "Patikrinkite ar teisingai įvedėte duomenis";
        }
        if (isError) {
            returnPage = "registration";
            model.addAttribute("registrationForm", new User());
            model.addAttribute("error", error);
        } else {
            returnPage = "index";
        }
        return returnPage;
    }
}
