package com.bookStore.controller;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    MyBookService myBookService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBook(){
        List<Book> list = bookService.getAll();
        return new ModelAndView("bookList", "book", list);
    }

//    it will be also worked as PUT if we pass the id also
    @PostMapping("/save")
    public String addBook(@ModelAttribute("book") Book book){
        bookService.save(book);
        return "redirect:/available_books";
    }



    //Request not coming from HTML form so POST,GET etc not possible for <a>> HTML
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable int id){
        Book book = bookService.getBookById(id);
        MyBookList myBook = new MyBookList(book.getId(),book.getName(),book.getAuthor(),book.getPrice());
        myBookService.saveMyBook(myBook);
        return "redirect:/my_books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable int id, Model model){
       Book book = bookService.getBookById(id);
       model.addAttribute("book", book); //passing model in bookEdit
        return "bookEdit"; //bookEdit is a view
    }

    @RequestMapping("/deletebook/{id}")
    public String deleteBook(@PathVariable int id){
        bookService.deleteBookById(id);
        return "redirect:/available_books";
    }


}
