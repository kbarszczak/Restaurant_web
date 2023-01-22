package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Review {

    @Id
    private String id;

    @DBRef
    private User author;

    @DBRef
    private Dish dish;

    private Integer mark;
    private String description;

    public Review(User author, Dish dish, Integer mark, String description) {
        this.author = author;
        this.dish = dish;
        this.mark = mark;
        this.description = description;
    }
}
