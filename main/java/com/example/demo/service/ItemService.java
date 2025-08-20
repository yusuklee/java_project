package com.example.demo.service;

import com.example.demo.dto.AlbumForm;
import com.example.demo.dto.BookForm;
import com.example.demo.dto.ItemForm;
import com.example.demo.dto.MovieForm;
import com.example.demo.entity.Album;
import com.example.demo.entity.Book;
import com.example.demo.entity.Item;
import com.example.demo.entity.Movie;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;


    public Long registerMovie(MovieForm movieForm) {
         String name=movieForm.getName();
         int price=movieForm.getPrice()                 ;
         int stockQuantity=movieForm.getStockQuantity();
         String director=movieForm.getDirector();
         String actor=movieForm.getActor();

        Movie movie = Movie.createMovie(name,price,stockQuantity,director,actor);
        itemRepository.save(movie);

        return movie.getId();
    }

    public Long registerBook(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        itemRepository.save(book);
        return book.getId();
    }

    public Long registerAlbum(AlbumForm form) {
        Album album = new Album();
        album.setName(form.getName());
        album.setPrice(form.getPrice());
        album.setStockQuantity(form.getStockQuantity());
        album.setArtist(form.getArtist());
        album.setEtc(form.getEtc());
        itemRepository.save(album);
        return album.getId();
    }

    public List<ItemForm> findAll() {
        return itemRepository.findAll().stream().map(item -> new ItemForm(
            item.getName(),item.getPrice(),item.getStockQuantity()
        )).toList();    //아이템 엔티티들을 다 아이템폼으로 바꿔서 리스트로 넘겨주기

    }
}
