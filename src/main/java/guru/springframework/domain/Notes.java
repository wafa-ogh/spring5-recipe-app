package guru.springframework.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {
    private String id;
    private String recipeNotes;

}
