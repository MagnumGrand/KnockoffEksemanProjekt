package com.example.knockoffeksemanprojekt.Controller;




import com.example.knockoffeksemanprojekt.Service.Usecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {
    @Autowired
    private Usecase usecase;

    @GetMapping("/userpage")
    public String showAllRecipes(Model model) {
        model.addAttribute("users", usecase.findAllRecipes());
        return "seeRecipes";
    }

}
