package com.scaler.productservice.demo;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<Book> books;

    public Author() {}

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
}
