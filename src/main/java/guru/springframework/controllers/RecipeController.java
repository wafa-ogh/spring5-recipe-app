package guru.springframework.controllers;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Created by jt on 6/19/17.
 */
@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeRepository.findById(Long.valueOf(id)).get());
        return "recipe/show";
    }

    @RequestMapping("/recipe/image/{id}")
    public void showRecipeImage(@PathVariable String id,
                               HttpServletResponse response) throws IOException {
        response.setContentType("image/webp");

        Optional<Recipe> recipe = recipeRepository.findById(Long.valueOf(id));
        if(recipe.isPresent()){
            InputStream is = new ByteArrayInputStream(recipe.get().getImage());
            IOUtils.copy(is, response.getOutputStream());
        }else {
            response.sendError(404);
        }
    }
}
