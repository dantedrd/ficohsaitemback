package com.ficohsa.item.infrastructure.adapter.h2.models;

import com.ficohsa.item.domain.models.Item;
import com.ficohsa.item.domain.utils.UtilFuntion;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ITEM")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


    public Item toDomain(){
        return Item
                .builder()
                .id(this.id)
                .name(this.name)
                .createdAt(UtilFuntion.transformDateToLocalDateTime(this.createdAt))
                .updatedAt(UtilFuntion.transformDateToLocalDateTime(this.updatedAt))
                .build();
    }

    public static ItemEntity fromDomain(Item item){
        return ItemEntity
                .builder()
                .id(item.getId())
                .name(item.getName())
                .build();
    }
}
