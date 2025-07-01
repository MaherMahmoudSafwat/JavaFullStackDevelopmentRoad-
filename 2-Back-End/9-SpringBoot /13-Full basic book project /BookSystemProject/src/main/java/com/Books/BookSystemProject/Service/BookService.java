package com.Books.BookSystemProject.Service;

import com.Books.BookSystemProject.Exceptions.InvalidPriceException;
import com.Books.BookSystemProject.Model.Books;
import com.Books.BookSystemProject.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;

@Service
public class BookService
{
    @Autowired
    BookRepository BookRepo;

    public ArrayList<Books> GetBooks()
    {
        return BookRepo.GetAllBooksList();
    }
    public boolean AddNewBook(Books AddBook) throws InvalidPriceException {
        if(BookRepo.IsBookIdAlreadyExists(AddBook.getBookID()))
        {
            return false;
        }
        BookRepo.AddBook(AddBook);
        return true;
    }
    public Object GetSpecificBook(int ID)
    {
        return BookRepo.GetSpecificBook(ID);
    }
    public Object GetSpecificBookByName(String Name)
    {
        return BookRepo.GetSpecificBookByName(Name);
    }
    public Object GetSpecificBookByAuthor(String Author)
    {
        return BookRepo.GetSpecificBookByAuthor(Author);
    }
    public Object GetSpecificBookByPrice(double Price)
    {
        return BookRepo.GetSpecificBookByPrice(Price);
    }
    public String UpdateBook(Books Book) throws InvalidPriceException {
        if(Book.getBookPrice() < 0)
        {
            throw new InvalidPriceException("The price should be positive");
        }
        return BookRepo.UpdateBook(Book);
    }
    public String DeleteBook(Books Book)
    {
        return BookRepo.DeleteBook(Book);
    }
}
