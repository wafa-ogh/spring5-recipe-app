package guru.springframework;

import guru.springframework.domain.*;
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
        UnitOfMeasure tablespoons = uomRepository.findByDescription("tablespoons").orElse(null);
        UnitOfMeasure teaspoon  = uomRepository.findByDescription("teaspoon").orElse(null);
        UnitOfMeasure ounces  = uomRepository.findByDescription("ounces").orElse(null);
        UnitOfMeasure cup  = uomRepository.findByDescription("cup").orElse(null);
        UnitOfMeasure eachUom  = uomRepository.findByDescription("each").orElse(null);
        UnitOfMeasure dashUom  = uomRepository.findByDescription("dash").orElse(null);
        UnitOfMeasure pintUom  = uomRepository.findByDescription("pint").orElse(null);
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
            recipe1.setImage(Files.readAllBytes(fi.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Notes notes = new Notes();
        notes.setRecipeNotes("Shakshuka is an easy-to-make recipe where eggs simmered in tomato sauce are seasoned with cumin and smoked paprika. Top it with cilantro, feta, and olives then serve it for breakfast, lunch, or dinner.");
        recipe1.setNotes(notes);
        recipe1.getIngredients().add(new Ingredient("olive oil", new BigDecimal(2), tablespoons));
        recipe1.getIngredients().add(new Ingredient("smoked paprika", new BigDecimal(1), teaspoon));
        recipe1.getIngredients().add(new Ingredient("ground cumin", new BigDecimal(1), teaspoon));
        recipe1.getIngredients().add(new Ingredient("Maras or Aleppo pepper flakes", new BigDecimal(.25), teaspoon));
        recipe1.getIngredients().add(new Ingredient("large yellow onion, halved and thinly sliced", new BigDecimal(1), null));
        recipe1.getIngredients().add(new Ingredient("cloves garlic, thinly sliced", new BigDecimal(2), null));
        recipe1.getIngredients().add(new Ingredient("red bell pepper, seeded and thinly sliced", new BigDecimal(1), null));
        recipe1.getIngredients().add(new Ingredient("yellow bell pepper, seeded and thinly sliced", new BigDecimal(1), null));
        recipe1.getIngredients().add(new Ingredient("whole peeled tomatoes, preferably San Marzano", new BigDecimal(28), ounces));
        recipe1.getIngredients().add(new Ingredient("salt", new BigDecimal(.5), teaspoon));
        recipe1.getIngredients().add(new Ingredient("feta cheese, crumbled", new BigDecimal(4), ounces));
        recipe1.getIngredients().add(new Ingredient("pitted Kalamata or other olives in brine", new BigDecimal(.33), cup));
        recipe1.getIngredients().add(new Ingredient("large eggs", new BigDecimal(4), null));
        recipeRepository.save(recipe1);

        //get Categories
        Category americanCategory = categoryRepository.findByDescription("American").orElse(null);
        Category mexicanCategory = categoryRepository.findByDescription("Mexican").orElse(null);

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws");

        guacRecipe.setNotes(guacNotes);

        //very redundent - could add helper method, and make this simpler
        guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(".5"), tablespoons));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tablespoons));
        guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoons));
        guacRecipe.getIngredients().add(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tablespoons));
        guacRecipe.getIngredients().add(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);
        try {
            File fi = new File(getClass().getClassLoader().getResource("images/gua.webp").toURI());
            guacRecipe.setImage(Files.readAllBytes(fi.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        recipeRepository.save(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");

        tacosRecipe.setNotes(tacoNotes);

        tacosRecipe.getIngredients().add(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoons));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoon));
        tacosRecipe.getIngredients().add(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoon));
        tacosRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoon));
        tacosRecipe.getIngredients().add(new Ingredient("Salt", new BigDecimal(".5"), teaspoon));
        tacosRecipe.getIngredients().add(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoons));
        tacosRecipe.getIngredients().add(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoons));
        tacosRecipe.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tablespoons));
        tacosRecipe.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoons));
        tacosRecipe.getIngredients().add(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("packed baby arugula", new BigDecimal(3), cup));
        tacosRecipe.getIngredients().add(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacosRecipe.getIngredients().add(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.getIngredients().add(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cup));
        tacosRecipe.getIngredients().add(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);
        try {
            File fi = new File(getClass().getClassLoader().getResource("images/taco.webp").toURI());
            tacosRecipe.setImage(Files.readAllBytes(fi.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        recipeRepository.save(tacosRecipe);
    }
}
