package com.pard.homework2.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pard.homework2.model.Item;

@Repository
public class ItemRepository {
    private static final Map<Integer, Item> items = new HashMap<>();

    public Item add(Item item)
    {
        items.put(item.getId(), item);
        return item;
    }

    public Item findById(Integer itemId){ return items.get(itemId); }
    public List<Item> findAll(){ return new ArrayList<>(items.values()); }

    public boolean update(Integer id, Item newItem)
    {
        Item target = this.findById(id);

        if(target != null)
        {
            target.setId(newItem.getId());
            target.setName(newItem.getName());
            target.setPrice(newItem.getPrice());
            target.setQuantity(newItem.getQuantity());

            return true;
        }
        else return false;
    }

    public boolean delete(Integer id){ return (items.remove(id) != null); }
}
