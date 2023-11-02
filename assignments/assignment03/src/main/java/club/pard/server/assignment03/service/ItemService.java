package club.pard.server.assignment03.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import club.pard.server.assignment03.dto.AddDto;
import club.pard.server.assignment03.dto.ResponseDto;
import club.pard.server.assignment03.entity.ItemEntity;
import club.pard.server.assignment03.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired public ItemService(ItemRepository itemRepository){ this.itemRepository = itemRepository; }


    //////////
    // Create

    public ResponseDto<ItemEntity> add(AddDto dto)
    {
        try
        {
            ItemEntity item = new ItemEntity(dto);
            String itemName = dto.getItemName();
            int itemPrice = dto.getItemPrice();
            // int itemQuantity = dto.getItemQuantity(); // Not used since this one is not being comapred to anything.

            if(itemPrice >= 50000) return ResponseDto.setFailure("Failed to add item: price exceeding 50k.");
            else if(itemName == null || itemName.isEmpty()) return ResponseDto.setFailure("Failed to add item: invalid item name.");
            else if(itemRepository.existsByItemName(itemName)) return ResponseDto.setFailure("Failed to add item: already existent.");
            else
            {
                itemRepository.save(item);
                return ResponseDto.setSuccess("Successfully added item", item);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("Internal Database Error");
        }
    }

    public ResponseDto<List<ItemEntity>> addMultiple(List<AddDto> dtos)
    {
        List<ItemEntity> addedItems = new ArrayList<>();
        
        for(AddDto dto: dtos)
        {
            try
            {
                ItemEntity item = new ItemEntity(dto);
                String itemName = dto.getItemName();
                int itemPrice = dto.getItemPrice();
                // int itemQuantity = dto.getItemQuantity(); // Not used since this one is not being comapred to anything.

                if(itemPrice >= 50000)
                    log.error("Failed to add item {}: price exceeding 50k.", itemName);
                else if(itemName == null || itemName.isEmpty())
                    log.error("Failed to add item: invalid item name.");
                else if(itemRepository.existsByItemName(itemName))
                    log.error("Failed to add item {}: already existent", itemName);
                else
                {
                    itemRepository.save(item);
                    addedItems.add(item);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        if(addedItems.size() != 0) return ResponseDto.setSuccess(String.format("Successfully added %d item(s)", addedItems.size()), addedItems);
        else return ResponseDto.setFailure("Failed to add items.");
    }


    //////////
    // Read

    public ResponseDto<List<ItemEntity>> findAll()
    {
        try
        {
            List<ItemEntity> items = itemRepository.findAll();
            return ResponseDto.setSuccess("Successfully retrieved list of items", items);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("Internal Database Error");
        }
    }

    public ResponseDto<List<ItemEntity>> findAllOrderByEachPrice()
    {
        try
        {
            List<ItemEntity> items = itemRepository.findAllOrderByPricePerEach();
            return ResponseDto.setSuccess("Successfully retrieved list of items", items);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("Internal Database Error");
        }
    }

    public ResponseDto<ItemEntity> findOne(Integer itemNum)
    {
        try
        {
            ItemEntity item = itemRepository.findById(itemNum).get();
            return ResponseDto.setSuccess("Successfully found target item", item);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("Internal Database Error");
        }
    }

    public ResponseDto<List<ItemEntity>> findAllWithName(String targetName)
    {
        try
        {
            List<ItemEntity> items = itemRepository.findByItemNameContainingOrderByItemTimeDesc(targetName);
            return ResponseDto.setSuccess("Successfully retrieved list of items", items);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("Internal Database Error");
        }
    }

    public ResponseDto<List<ItemEntity>> findAllLessThanPrice(int price)
    {
        try
        {
            List<ItemEntity> items = itemRepository.findByItemPriceLessThanEqualOrderByItemPrice(price);
            return ResponseDto.setSuccess("Successfully retrieved list of items", items);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("Internal Database Error");
        }
    }


    //////////
    // Update

    @Transactional public ResponseDto<ItemEntity> update(Integer itemNum, AddDto dto)
    {
        try
        {
            ItemEntity item = itemRepository.findById(itemNum).get();
            if(dto.getItemName() == null || dto.getItemName().isEmpty())
                return ResponseDto.setFailure("Update failed: invalid item name");
            else if(!dto.getItemName().equals(item.getItemName()) && itemRepository.existsByItemName(dto.getItemName()))
                return ResponseDto.setFailure("Update failed: already existent item name");
            else if(dto.getItemPrice() >= 50000)
                return ResponseDto.setFailure("Update failed: invalid item price(must not exceed 50k)");
            else
            {
                item.setItemName(dto.getItemName());
                item.setItemPrice(dto.getItemPrice());
                item.setItemQuantity(dto.getItemQuantity());

                return ResponseDto.setSuccess("Sucessfully updated item", item);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("Internal Database Error");
        }
    }


    //////////
    // Delete

    public ResponseDto<?> delete(Integer itemNum)
    {
        try
        {
            if(itemRepository.existsById(itemNum))
            {
                itemRepository.deleteById(itemNum);
                return ResponseDto.setSuccess("Successfully deleted item", null);
            }            
            else return ResponseDto.setFailure("Non-existent id");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("Internal Database Error");
        }
    }    
}
