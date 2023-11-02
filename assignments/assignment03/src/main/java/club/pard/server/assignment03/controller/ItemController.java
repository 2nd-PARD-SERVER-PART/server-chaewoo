package club.pard.server.assignment03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import club.pard.server.assignment03.dto.AddDto;
import club.pard.server.assignment03.dto.ResponseDto;
import club.pard.server.assignment03.entity.ItemEntity;
import club.pard.server.assignment03.service.ItemService;

@RestController
public class ItemController {
    private final ItemService itemService;

    @Autowired public ItemController(ItemService itemService){ this.itemService = itemService; }


    //////////
    // Create

    @PostMapping("/item")
    public ResponseDto<ItemEntity> add(@RequestBody AddDto dto){ return itemService.add(dto); }

    @PostMapping("/item/multiple")
    public ResponseDto<List<ItemEntity>> addMultiple(@RequestBody List<AddDto> dtos){ return itemService.addMultiple(dtos); }


    //////////
    // Read

    @GetMapping("/item/{id}")
    public ResponseDto<ItemEntity> findOne(@PathVariable int id){ return itemService.findOne(id); }

    @GetMapping("/item/all")
    public ResponseDto<List<ItemEntity>> findAll(
        @RequestParam(name = "contains", required = false) String name,
        @RequestParam(name = "priceLimit", required = false) Integer priceLimit,
        @RequestParam(name = "sortByEachPrice", defaultValue = "false") Boolean sortByEachPrice)
    {
        if(name != null && !name.isEmpty()) return itemService.findAllWithName(name);
        else if(priceLimit != null) return itemService.findAllLessThanPrice(priceLimit);
        else if(sortByEachPrice) return itemService.findAllOrderByEachPrice();
        else return itemService.findAll();
    }


    //////////
    // Update

    @PatchMapping("/item/{itemNum}")
    public ResponseDto<ItemEntity> update(@PathVariable int itemNum, @RequestBody AddDto dto){ return itemService.update(itemNum, dto); }


    //////////
    // Delete

    @DeleteMapping("/item/{itemNum}")
    public ResponseDto<?> delete(@PathVariable int itemNum){ return itemService.delete(itemNum); }
}
