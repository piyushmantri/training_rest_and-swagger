package com.endurance.emdb.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "item_casts")
@Data
public class ItemCast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "cast")
    private String castName;

    @Column(name = "role")
    private String role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;
}
