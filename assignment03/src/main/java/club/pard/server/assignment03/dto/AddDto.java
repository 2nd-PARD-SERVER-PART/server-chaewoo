package club.pard.server.assignment03.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddDto {
    private String itemName;
    private int itemPrice;
    private int itemQuantity;
}
