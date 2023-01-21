package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Dish {

    @Id
    private String id;
    private String name;
    private String type;
    private String category;
    private String[] ingredients;
    private Integer maxQuantity;
    private Double price;
    private String imageUrl;
}
