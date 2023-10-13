# PARD Server part, Assignment #3
Assignment after Seminar #3, results:

`ItemEntity` settings:
```java
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int itemId;
    @Column(columnDefinition = "TEXT", nullable = false, unique = true) private String itemName;
    @Column @Max(value = 49999, message = "Item price cannot exceed 50k") private int itemPrice;
    @Column private int itemQuantity;
    @CreationTimestamp private Timestamp itemTime;
```

`ItemRepository` custom JPAs:
```java
    List<ItemEntity> findByItemPriceLessThanEqualOrderByItemPrice(int price); // Find items with price less than or equal to {price}, sorting by item price
    List<ItemEntity> findByItemNameContainingOrderByItemTimeDesc(String targetItemName); // Find items containing certain name, sorting descendingly by creation tiemstamp
    @Query(value = "SELECT * FROM item_entity ORDER BY (item_price / item_quantity)", nativeQuery = true)
    List<ItemEntity> findAllOrderByPricePerEach(); // Find all items, sorting by price per each item price / quantity)
```

Expand to see the screenshots, or visit `./screenshots` to see them.

## Create
<details>
    <summary>Adding One Item:</summary>
    <img src="./screenshots/2023-10-13 23 12 13.png">
</details>

<details>
    <summary>Adding Multiple Items:</summary>
    <img src="./screenshots/2023-10-13 23 12 23.png">
</details>

---

## Read
<details>
    <summary>Retrieving One Item with ID #3:</summary>
    <img src="./screenshots/2023-10-13 23 12 30.png">
</details>

<details>
    <summary>Retrieving All Items:</summary>
    <img src="./screenshots/2023-10-13 23 12 43.png">
</details>

<details>
    <summary>Retrieving Items with Price Limit 35000:</summary>
    <img src="./screenshots/2023-10-13 23 12 52.png">
</details>

<details>
    <summary>Retrieving Items containing name "HEAT-FS":</summary>
    <img src="./screenshots/2023-10-13 23 13 01.png">
</details>

<details>
    <summary>Retrieving All Items Sorted by Price per Each:</summary>
    <img src="./screenshots/2023-10-13 23 13 07.png">
</details>

---


## Update
<details>
    <summary>Updating Item with ID #3:</summary>
    <img src="./screenshots/2023-10-13 23 13 20.png">
</details>

---

## Delete
<details>
    <summary>Deleting Item with ID #5:</summary>
    <img src="./screenshots/2023-10-13 23 13 30.png">
</details>