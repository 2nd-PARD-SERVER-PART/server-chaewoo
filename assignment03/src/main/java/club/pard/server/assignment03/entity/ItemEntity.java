package club.pard.server.assignment03.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import club.pard.server.assignment03.dto.AddDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int itemId;
    @Column(columnDefinition = "TEXT", nullable = false, unique = true) private String itemName;
    @Column @Max(value = 49999, message = "Item price cannot exceed 50k") private int itemPrice;
    @Column private int itemQuantity;
    @CreationTimestamp private Timestamp itemTime;

    public ItemEntity(AddDto dto){ this.itemName = dto.getItemName(); this.itemPrice = dto.getItemPrice(); this.itemQuantity = dto.getItemQuantity(); }
}
