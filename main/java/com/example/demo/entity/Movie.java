package com.example.demo.entity;

import com.example.demo.dto.MovieForm;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("M")
@Getter @Setter
@NoArgsConstructor
public class Movie extends  Item{
    private String director;
    private String actor;

    public static Movie createMovie(String name, int price, int stockQuantity, String director, String actor){
        Movie movie = new Movie();
        movie.setName(name);
        movie.setPrice(price);
        movie.setStockQuantity(stockQuantity);
        movie.setDirector(director);
        movie.setActor(actor);

        return movie;
    }
}
