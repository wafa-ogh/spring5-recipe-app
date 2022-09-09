package guru.springframework;

import guru.springframework.model.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;

@Component
public class DataInitilizr implements ApplicationRunner {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UnitOfMeasureRepository uomRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UnitOfMeasure tablespoons = uomRepository.findByDescription("tablespoons");
        UnitOfMeasure teaspoon  = uomRepository.findByDescription("teaspoon");
        UnitOfMeasure ounces  = uomRepository.findByDescription("ounces");
        UnitOfMeasure cup  = uomRepository.findByDescription("cup");
        Category breakfast = new Category();
        breakfast.setDescription("Breakfast");
        breakfast = categoryRepository.save(breakfast);
        Category dinner = new Category();
        dinner.setDescription("Dinner");
        dinner = categoryRepository.save(dinner);
        Recipe recipe1 = new Recipe();
        recipe1.getCategories().add(breakfast);
        recipe1.getCategories().add(dinner);
        recipe1.setCookTime(30*60);
        recipe1.setPrepTime(20*60);
        recipe1.setDescription("Shakshuka with Feta, Olives, and Peppers");
        recipe1.setServings(4);
        recipe1.setUrl("https://www.simplyrecipes.com/shakshuka-with-feta-olives-and-peppers-5114919");
        recipe1.setDirections("1. Cook the onion and peppers\n2. Crush the tomatoes and simmer them with the peppers\n3. Cook the eggsCook the eggs\n4. Garnish with cilantro leaves and a sprinkling of red pepper flakes");
        recipe1.setDifficulty(Difficulty.EASY);
        try {
            File fi = new File(getClass().getClassLoader().getResource("images/recipe.webp").toURI());
            byte[] fileContent = Files.readAllBytes(fi.toPath());
            Byte[] byteObjects = new Byte[fileContent.length];
            int i=0;
            for(byte b: fileContent)
                byteObjects[i++] = b;
            recipe1.setImage(byteObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Notes notes = new Notes();
        notes.setRecipeNotes("Shakshuka is an easy-to-make recipe where eggs simmered in tomato sauce are seasoned with cumin and smoked paprika. Top it with cilantro, feta, and olives then serve it for breakfast, lunch, or dinner.");
        recipe1.setNotes(notes);
        notes.setRecipe(recipe1);
        recipe1.getIngredients().add(new Ingredient("olive oil", new BigDecimal(2), tablespoons, recipe1));
        recipe1.getIngredients().add(new Ingredient("smoked paprika", new BigDecimal(1), teaspoon, recipe1));
        recipe1.getIngredients().add(new Ingredient("ground cumin", new BigDecimal(1), teaspoon, recipe1));
        recipe1.getIngredients().add(new Ingredient("Maras or Aleppo pepper flakes", new BigDecimal(.25), teaspoon, recipe1));
        recipe1.getIngredients().add(new Ingredient("large yellow onion, halved and thinly sliced", new BigDecimal(1), null, recipe1));
        recipe1.getIngredients().add(new Ingredient("cloves garlic, thinly sliced", new BigDecimal(2), null, recipe1));
        recipe1.getIngredients().add(new Ingredient("red bell pepper, seeded and thinly sliced", new BigDecimal(1), null, recipe1));
        recipe1.getIngredients().add(new Ingredient("yellow bell pepper, seeded and thinly sliced", new BigDecimal(1), null, recipe1));
        recipe1.getIngredients().add(new Ingredient("whole peeled tomatoes, preferably San Marzano", new BigDecimal(28), ounces, recipe1));
        recipe1.getIngredients().add(new Ingredient("salt", new BigDecimal(.5), teaspoon, recipe1));
        recipe1.getIngredients().add(new Ingredient("feta cheese, crumbled", new BigDecimal(4), ounces, recipe1));
        recipe1.getIngredients().add(new Ingredient("pitted Kalamata or other olives in brine", new BigDecimal(.33), cup, recipe1));
        recipe1.getIngredients().add(new Ingredient("large eggs", new BigDecimal(4), null, recipe1));
        recipeRepository.save(recipe1);
    }
}
