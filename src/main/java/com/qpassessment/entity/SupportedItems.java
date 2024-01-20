package com.qpassessment.entity;

import com.qpassessment.constants.ItemType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supported_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportedItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String item;

    @Enumerated(EnumType.STRING)
    private ItemType groceryType;
}
