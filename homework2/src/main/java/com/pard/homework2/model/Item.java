package com.pard.homework2.model;

import lombok.Data;
// import lombok.extern.slf4j.Slf4j;

// Not calling @NoArgsConstructor this time, since field 'id' needs extra care.
@Data
// @Slf4j
public class Item {
    private static int idTotal = 1;

    private int id;
    private String name;
    private int price;
    private int quantity;

    // // Those two constructors aren't required.
    // public Item(int id, String name, int price, int quantity)
    // {
    //     this.id = Item.idTotal++;
    //     this.name = name;
    //     this.price = price;
    //     this.quantity = quantity;    

    //     log.info("All-args constructor called, details: {}", this.toString());
    // }

    // public Item(String name, int price, int quantity)
    // {
    //     this.id = Item.idTotal++;
    //     this.name = name;
    //     this.price = price;
    //     this.quantity = quantity;

    //     log.info("Constructor with id-excluded args called, details: {}", this.toString());
    // }

    // Surprisingly this has been called, even if body provided all except the id.
    // Found that @RequestBody annotation uses HttpMessageConverter to deserialize body into object, first making instance and then using setters to set fields. (Reference: https://yeonyeon.tistory.com/221)
    // Setters were made through @Data annotations.
    public Item()
    {
        this.id = Item.idTotal++;
        this.name = "";
        this.price = 0;
        this.quantity = 0;

        // log.info("Constructor with no args called, details: {}", this.toString());
    }
}
