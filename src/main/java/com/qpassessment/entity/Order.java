package com.qpassessment.entity;

import com.qpassessment.constants.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer userId;

    //json[{"item": "apple", "quantity": 2},{"item": "banana", "quantity": 1}]
    private String itemWithQuantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

    @Column(insertable = false)
    @UpdateTimestamp
    private Timestamp updatedOn;

}
