package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document
public class Recipe {
    @Id
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private byte[] image;
    private Difficulty difficulty;
    private Notes notes;
    private Set<Ingredient> ingredients = new HashSet<>();
    @DBRef
    private Set<Category> categories = new HashSet<>();


    public Recipe addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        return this;
    }

    public Recipe removeIngredient(Ingredient ingredient){
        this.ingredients.removeIf(ingredient1 -> ingredient1.getId().equals(ingredient.getId()));
        return this;
    }
}
