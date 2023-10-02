package com.pard.homework2.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pard.homework2.model.Item;
import com.pard.homework2.repository.ItemRepository;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository){ this.itemRepository = itemRepository; }

    //////////
    // Creating
    @PostMapping("/add") public String addItem(@RequestBody Item item)
    {
        itemRepository.add(item);
        return String.format("Item #%d added.", item.getId());
    }


    //////////
    // Reading
    @GetMapping("/list")
    public List<Item> getItemList(){ return itemRepository.findAll(); }

    @GetMapping("/list/{targetId}")
    public Item getItemById(@RequestParam Integer targetId){ return itemRepository.findById(targetId); }


    //////////
    // Updating
    @PatchMapping("/update/{targetId}")
    public String updateItem(@RequestParam Integer targetId, @RequestBody Item newItem)
    {
        Item targetItem = itemRepository.findById(targetId);

        if(targetItem != null)
        {
            targetItem.setName(newItem.getName());
            targetItem.setPrice(newItem.getPrice());
            targetItem.setQuantity(newItem.getQuantity());

            return String.format("Item #%d updated", targetId);
        }
        else return String.format("Failed to find target item #%d", targetId);
    }


    //////////
    // Deleting
    @DeleteMapping("/remove/{targetId}")
    public String removeItem(@RequestParam Integer targetId)
    {
        if(itemRepository.delete(targetId)) return String.format("Item #%d removed from the list.", targetId);
        else return "Failed to retrieve item.";
    }
}
