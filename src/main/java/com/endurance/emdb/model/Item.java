package com.endurance.emdb.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
}
