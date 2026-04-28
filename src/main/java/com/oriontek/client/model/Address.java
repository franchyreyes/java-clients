package com.oriontek.client.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Full address description in one field
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    // Constructors
    public Address() {}

    public Address(String description, Client client) {
        this.description = description;
        this.client = client;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
