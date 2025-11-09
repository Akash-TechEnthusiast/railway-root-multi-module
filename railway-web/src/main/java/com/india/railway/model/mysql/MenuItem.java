package com.india.railway.model.mysql;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // getter and setter and tostring
@AllArgsConstructor
@NoArgsConstructor
// @EntityListeners(CustomIdGenerationListener.class)
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Name of the menu item
    private String url; // URL or route associated with the menu item

    @OneToMany(mappedBy = "menuitem", cascade = CascadeType.ALL)
    private List<MenuItem> children;

    // Many employees report to one manager
    @ManyToOne
    @JoinColumn(name = "parentmenuitem_id")
    private MenuItem menuitem;

}
