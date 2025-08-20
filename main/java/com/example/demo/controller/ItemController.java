package com.example.demo.controller;

import com.example.demo.dto.AlbumForm;
import com.example.demo.dto.BookForm;
import com.example.demo.dto.ItemForm;
import com.example.demo.dto.MovieForm;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;


    @PostMapping("/상품등록_영화")
    public ResponseEntity<?> registerMovie(@RequestBody MovieForm movieForm){
        Long id = itemService.registerMovie(movieForm);
        return ResponseEntity.ok(id);
    }
    @PostMapping("/상품등록_앨범")
    public ResponseEntity<?> registerMovie(@RequestBody AlbumForm albumForm){
        Long id = itemService.registerAlbum(albumForm);
        return ResponseEntity.ok(id);
    }
    @PostMapping("/상품등록_책")
    public ResponseEntity<?> registerMovie(@RequestBody BookForm bookForm){
        Long id = itemService.registerBook(bookForm);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/상품목록")
    public List<?> showItems(){
        List<ItemForm> itemForms = itemService.findAll();
        return itemForms;
    }

}
