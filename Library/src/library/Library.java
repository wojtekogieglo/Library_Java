package library;

import java.util.ArrayList;
import java.util.Scanner;
import static library.LibraryManager.*;


public class Library {
    public static void main(String[] args) {
        
        ArrayList<Book> books = new ArrayList<Book>();
        loadBase(books);
        menu(books);
    }   
}
    
