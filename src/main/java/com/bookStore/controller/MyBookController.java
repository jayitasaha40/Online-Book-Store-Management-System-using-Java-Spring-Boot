package com.bookStore.controller;

import com.bookStore.entity.MyBookList;
import com.bookStore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MyBookController {
    @Autowired
   private MyBookService myBookService;

    @GetMapping("/my_books")
    public ModelAndView getMyBooks(){
        List<MyBookList> myBookList = myBookService.getMyBookList();
        return new ModelAndView("myBooks","MyBook",myBookList);
    }


    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id){
        MyBookList myBook = myBookService.getMyBookById(id);
        myBookService.deleteMyBook(myBook);
        return "redirect:/my_books";
    }
}
