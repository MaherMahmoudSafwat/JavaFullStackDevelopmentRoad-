package com.Books.BookSystemProject.Repository;

import com.Books.BookSystemProject.Exceptions.InvalidPriceException;
import com.Books.BookSystemProject.Model.Books;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class BookRepository
{
    private static ArrayList<Books>BooksList = new ArrayList<>();
    public boolean IsBookIdAlreadyExists(int BookID)
    {
        for(Books Book:BooksList)
        {
            if(Book.getBookID()==BookID)
                return true;
        }
        return false;
    }
    public void AddBook(Books Book) throws InvalidPriceException {
        if(Book.getBookPrice() < 0)
        {
            throw new InvalidPriceException("Price shouldn't be negative it should e positive");
        }
        BooksList.add(Book);
    }
    public ArrayList<Books> GetAllBooksList()
    {
        return BooksList;
    }
    public Object GetSpecificBook(int ID)
    {
        for(Books Book:BooksList)
        {
            if(Book.getBookID()==ID)
            {
                return Book;
            }
        }
        return "This book doesn't Exists";
    }
    public Object GetSpecificBookByName(String BookName)
    {
        ArrayList<Books>GetAllBooksByName = new ArrayList<>();
        for(Books Book:BooksList)
        {
            if(Book.getBookName().contains(BookName))
            {
                GetAllBooksByName.add(Book);
            }
        }
        if(GetAllBooksByName.size()>0)
        {
            return GetAllBooksByName;
        }
        else
        {
            return "This book doesn't exists";
        }
    }
    public Object GetSpecificBookByAuthor(String BookAuthor)
    {
        ArrayList<Books>GetAllBooksByAuthor = new ArrayList<>();
        for(Books Book:BooksList)
        {
            if(Book.getBookAuthor().contains(BookAuthor))
            {
                GetAllBooksByAuthor.add(Book);
            }
        }
        if(!GetAllBooksByAuthor.isEmpty())
        {
            return GetAllBooksByAuthor;
        }
        else
        {
            return "This book doesn't exists";
        }
    }
    public Object GetSpecificBookByPrice(double BookPrice)
    {
        ArrayList<Books>GetAllBooksByPrice = new ArrayList<>();
        for(Books Book:BooksList)
        {
            if(BookPrice==Book.getBookPrice())
            {
                GetAllBooksByPrice.add(Book);
            }
        }
        if(!GetAllBooksByPrice.isEmpty())
        {
                return GetAllBooksByPrice;
        }
        else
        {
            return "This book price doesn't exists";
        }
    }
    public String UpdateBook(Books UpdateBook)
    {
        for(Books Book:BooksList)
        {
            if(Book.getBookID()==UpdateBook.getBookID())
            {
                BooksList.remove(Book);
                BooksList.add(UpdateBook);
                return "The book with the id:"+UpdateBook.getBookID()+" has been updated successfully";
            }
        }
        return "The book doesn't exists";
    }
    public String DeleteBook(Books DeleteBook)
    {
        for(Books Book:BooksList)
        {
            if(Book.getBookID()==DeleteBook.getBookID())
            {
                BooksList.remove(Book);
                return "The book with the id:"+DeleteBook.getBookID()+" has been deleted successfully";
            }
        }
        return "The book doesn't exists";
    }
}
