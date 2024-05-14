package com.example.knockoffeksemanprojekt.Controller;




import com.example.knockoffeksemanprojekt.Model.MyUser;
import com.example.knockoffeksemanprojekt.Service.Usecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private Usecase usecase;

    @GetMapping("/createUpdateUser")
    public String createUserForm(Model model) {
        model.addAttribute("MyUser", new MyUser());
        return "createUpdateUser";
    }
    @PostMapping("/createUpdateUser")
    public String createUser(@ModelAttribute MyUser myUser){
        usecase.createUpdateUser(myUser);
        if (myUser.getEmail() == null)
            return "redirect:/userCreatedSucces";
        else
            return "redirect:/userpage";
    }
    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("MyUser", new MyUser());
        return "login";
    }

    @GetMapping("/userCreatedSucces")
    public String userCreatedSucces(Model model) {
        model.addAttribute("MyUser", new MyUser());
        return "userCreatedSucces";
    }

    /*@GetMapping("/indexUser")
    public String showAllUsers(Model model) {
        model.addAttribute("users", usecase.findAllUsers());
        return "indexUser";
    }*/

    /*@GetMapping("/indexUser/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        usecase.deleteUserById(userId);
        return "redirect:/indexUser";
    }*/

    /*@GetMapping("/indexUser/edit/{userId}")
    public String showEditForm(@PathVariable Long userId, Model model) {
        usecase.findUserById(userId).ifPresent(user -> model.addAttribute("user", user));
        return "editUsers";
    }*/



    /*@GetMapping("/searchtestUser")
    public String showUser(@RequestParam(required = false) Long userId, Model model, User u) {
        if (userId != null) {
            Optional<User> userOptional = usecase.findUserById(userId);
            userOptional.ifPresent(user -> model.addAttribute("user", user));
            model.addAttribute("userFound", userOptional.isPresent()); // Add attribute indicating if user was found
        }
        return "searchUser";
    }*/



    /*@PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        boolean authenticated = kattehjemmesideService.authenticateUser(user.getUsername(), user.getPassword());
        if (authenticated) {
            return "redirect:/menu"; // Omled til hovedsiden hvis login er succesfuldt
        } else {
            model.addAttribute("error", "Ugyldigt brugernavn eller password");
            return "login"; // Bliv på login siden hvis login fejler
        }
    }*/

}

