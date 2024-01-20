package com.qpassessment.entity;

import com.qpassessment.constants.ItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "grocery_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String itemKey;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ItemType groceryType;

    private Double price;

    private Long currentStock;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(insertable = false)
    @UpdateTimestamp
    private Timestamp updatedOn;

}
