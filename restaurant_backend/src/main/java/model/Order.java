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
    private Dish dish;

    private Integer quantity;

    private LocalDate orderedAt;

    public Order(User author, Dish dish, Integer quantity, LocalDate orderedAt) {
        this.author = author;
        this.dish = dish;
        this.quantity = quantity;
        this.orderedAt = orderedAt;
    }
}
