package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order {

    @Id
    private String id;

    @DBRef
    private User author;

    @DBRef
    private Dish[] dishes;

    private LocalDate orderedAt;

    public Order(User author, Dish[] dishes, LocalDate orderedAt) {
        this.author = author;
        this.dishes = dishes;
        this.orderedAt = orderedAt;
    }
}
