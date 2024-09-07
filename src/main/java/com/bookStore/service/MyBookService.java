package com.bookStore.service;

import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {

    @Autowired
    private MyBookRepository myBookRepository;

    public void saveMyBook(MyBookList book) {
        myBookRepository.save(book);
    }

    public List<MyBookList> getMyBookList() {
        return myBookRepository.findAll();
    }

    public MyBookList getMyBookById(int id) {
        return myBookRepository.findById(id).get();
    }

    public void deleteMyBook(MyBookList book) {
        myBookRepository.delete(book);
    }
}
