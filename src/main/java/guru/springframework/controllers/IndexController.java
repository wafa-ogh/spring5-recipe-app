package guru.springframework.controllers;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

    @Autowired
    private RecipeRepository recipeRepository;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        Iterable<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        Map<Long, String> recipeBase64Images = new HashMap<>();
        for(Recipe recipe: recipes){
            byte[] byteArray = new byte[recipe.getImage().length];
            int i=0;
            for(Byte b: recipe.getImage())
                byteArray[i++] = b;
            String imageBase64 = Base64.getEncoder().encodeToString(byteArray);
            recipeBase64Images.put(recipe.getId(), imageBase64);
        }
        model.addAttribute("images", recipeBase64Images);
        return "index";

    }
}
