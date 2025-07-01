package com.Books.BookSystemProject.Controller;

import com.Books.BookSystemProject.Exceptions.InvalidPriceException;
import com.Books.BookSystemProject.Model.Books;
import com.Books.BookSystemProject.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class ControllerBook
{
    @Autowired
    BookService Book;
    @GetMapping
    public String ShowMenu()
    {
        return "Welcome to the book store menu project\n"
                +"Please choose one of the following\n"
                +"1-AddBook\n"
                +"2-UpdateBook\n"
                +"3-DeleteBook\n"
                +"4-FindBook\n"
                +"5-ShowAllBooks\n";
    }
    @GetMapping("/Books")
    public ArrayList<Books> GetUserChoice()
    {
        return Book.GetBooks();
    }
    @GetMapping(path = "/Books/Id/{ID}")
    public Object GetSpecificBook(@PathVariable int ID)
    {
        return Book.GetSpecificBook(ID);
    }
    @PostMapping(path = "/Books")
    public String AddNewBook(@RequestBody Books AddBook)
    {
        try {
            if(Book.AddNewBook(AddBook))
            {
                return "Book has been Added Successfully";
            }
            else
            {
                return "Book Id is already Exists";
            }
        } catch (InvalidPriceException e) {
            return e.getMessage();
        }
    }
    @GetMapping(path = "/Books/Name/{BookName}")
    public Object SearchByName(@PathVariable String BookName)
    {
        return Book.GetSpecificBookByName(BookName);
    }
    @GetMapping(path = "/Books/Author/{BookAuthor}")
    public Object SearchByAuthor(@PathVariable String BookAuthor)
    {
        return Book.GetSpecificBookByAuthor(BookAuthor);
    }
    @GetMapping(path = "/Books/Price/{BookPrice}")
    public Object SearchByPrice(@PathVariable double BookPrice)
    {
        return Book.GetSpecificBookByPrice(BookPrice);
    }
    @PutMapping(path = "/Books")
    public String UpdateBook(@RequestBody Books UpdateBook)
    {
        try {
            return Book.UpdateBook(UpdateBook);
        } catch (InvalidPriceException e) {
            return e.getMessage();
        }
    }
    @DeleteMapping(path = "/Books")
    public String DeleteBook(@RequestBody Books DeleteBook)
    {
        return Book.DeleteBook(DeleteBook);
    }
}
