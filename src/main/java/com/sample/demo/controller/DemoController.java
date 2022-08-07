package com.sample.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.demo.model.entity.Book;
import com.sample.demo.service.DataService;


@Controller
public class DemoController {
	
	@Autowired
	private DataService dataService;
	
	@GetMapping("/books")
	public String getBooks(Model model) {
		Iterable<Book> bookList = dataService.selectBooks();
        model.addAttribute("bookList", bookList);
        Book book = new Book();
        model.addAttribute("bookObject", book);
        return "books";
	}
	
	@PostMapping("/book")
    public String createBook(@ModelAttribute Book book, Model model) {
		dataService.insertBook(book);
        Iterable<Book> bookList = dataService.selectBooks();
        Book emptyBook = new Book();
        model.addAttribute("bookList", bookList);
        model.addAttribute("bookObject", emptyBook);
        return "redirect:/books";
    }
	
	@ResponseBody
	@PutMapping("/book/{id}")
    public String updateBook(@PathVariable Integer id, @RequestBody Book book) {
		dataService.updateBookById(id, book);
        return "OK";
    }
	
	@ResponseBody
	@DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable Integer id) {
		dataService.deleteBookById(id);        
        return "OK";
    }

}
