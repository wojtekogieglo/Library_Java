package library;

import java.util.ArrayList;


public class Book{
        
        private  String author;
        private  String title;
        private  String category;
        private  String description;
        private  int publishYear;
        
        Book(String author, String title, String category, String description, int publishYear){
            this.author = author;
            this.title = title;
            this.category = category;
            this.description = description;
            this.publishYear = publishYear;
        }

        public String getAuthor(Book book) {
            return book.author;
        }

        public String getTitle(Book book) {
            return book.title;
        }

        public String getCategory(Book book) {
            return book.category;
        }

        public String getDescription(Book book) {
            return book.description;
        }

        public int getPublishYear(Book book) {
            return book.publishYear;
        }

        public static void setAuthor(Book book, String author) {
            book.author = author;
        }
       
        public static void setTitle(Book book, String title ) {
            book.title = title;
        }
        
        public static void setCategory(Book book, String category ) {
            book.category = category;
        }
       
        public static void setDescription(Book book, String description ) {
            book.description = description;
        }
       
        public static void setPublishYear(Book book, int publishYear ) {
            book.publishYear = publishYear;
        }
        
        public static void display(Book book, ArrayList<Book> books){
        System.out.println("ID: " + (books.indexOf(book)+1));
        System.out.println("Tytuł: " + book.title);
        System.out.println("Autor: " + book.author);
        System.out.println("Kategoria: " + book.category);
        System.out.println("Rok wydania " + book.publishYear);
        System.out.println("Opis: " + book.description);
        System.out.println("");
        }
    }
