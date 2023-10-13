package club.pard.server.assignment03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import club.pard.server.assignment03.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer>{
    // Required internally
    boolean existsByItemName(String itemName);

    // Additional queries
    List<ItemEntity> findByItemPriceLessThanEqualOrderByItemPrice(int price); // Find items with price less than or equal to {price}, sorting by item price
    List<ItemEntity> findByItemNameContainingOrderByItemTimeDesc(String targetItemName); // Find items containing certain name, sorting descendingly by creation tiemstamp
    @Query(value = "SELECT * FROM item_entity ORDER BY (item_price / item_quantity)", nativeQuery = true)
    List<ItemEntity> findAllOrderByPricePerEach(); // Find all items, sorting by price per each item(price / quantity)
}
