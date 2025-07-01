package com.Books.BookSystemProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Books
{
    int BookID;
    String BookName;
    String BookAuthor;
    String BookDescription;
    double BookPrice;
}
