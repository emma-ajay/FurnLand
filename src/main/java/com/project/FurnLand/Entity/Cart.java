package com.project.FurnLand.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;


    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    public Set<SelectedItem> selectedItems;



    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "cart")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Cart(){}
    public Cart(Long id, String status) {
        this.id = id;
        this.status =status;
    }

    public Cart(String status) {
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<SelectedItem> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(Set<SelectedItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
