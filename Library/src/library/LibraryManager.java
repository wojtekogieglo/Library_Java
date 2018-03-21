package library;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class LibraryManager {
    //wejscie do programu - pokazanie menu i wybor opcji z menu
    public static int start(){
        Scanner input = new Scanner(System.in);
        System.out.println("1. Dodaj ksiazke" + " 2. Usun ksiazke" + " 3. Szukaj ksiazki" + " 4. Edytuj ksiazke" + " 5. Wyświetl wszystkie ksiazki" + " 6. Wyjscie");
        int choice;
        choice = Integer.parseInt(input.nextLine());
        switch(choice){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 0;
            default:{
                System.out.println("Wpisz jeszcze raz");
                return start();
            }
        }  
    }
    //przejecie z metody start wartosci - jezeli start zwrocil 0 to wychodzimy z programu
    public static void menu(ArrayList<Book> books){
        int value;
        while((value = start()) != 0){
            switch (value){
                //dodajemy ksiazke do bazy a nastepnie zapisujemy baze
                case 1:{
                    System.out.println("Dodajemy nowa ksiazke do bazy:");
                    addBook(books);
                    saveBase(books);
                    break;
                }
                //Sprawdzamy czy baza jest pusta - jeżeli jest pusta nie ma czego usunąć. 
                //W przeciwnym wypadku wyświetlamy baze a następnie wybieramy ID ksiązki, którą chcemy usunąć
                case 2:{
                    if(books.isEmpty()){
                        System.out.println("Brak ksiazek w bazie");
                    }else{
                        displayBase(books);
                        System.out.print("Podaj ID ksiazki ktora chcesz usunac: ");
                        int id = addInt(new Scanner(System.in));
                        books.remove(id-1);
                        saveBase(books);
                    }
                    break;
                }
                //Sprawdzamy cza baza jest pusta, jeżeli tak to nie ma czego przeszukiwać
                //Jeżeli w bazie istnieją jakieś rekordy to pytamy według czego chcemy szukać i przechodzimy do metody searchNumber.
                case 3:{
                    if(books.isEmpty()){
                        System.out.println("Brak ksiazek w bazie");
                    }else{
                        System.out.println("Szukaj wedlug: 1. Tytulu" + " 2. Autora" + " 3. Kategorii" + " 4. Roku wydania");
                        int searchNumber = addInt(new Scanner(System.in));
                        switch(searchNumber){
                            case 1:
                                searchBooks(books,"title");
                                break;
                            case 2:
                                searchBooks(books,"author");
                                break;
                            case 3:
                                searchBooks(books,"category");
                                break;
                            case 4:
                                searchBooks(books,"publishYear");
                                break;
                        }
                    }
                    break;
                }
                //Sprawdzamy czy baza jest pusta, jeżeli tak nie ma czego edytować.
                //Jeżeli są rekordy w bazie to wyświetlamy bazę, podajemy ID książki do edycji
                //Następnie wybieramy co chcemy edytować w książce i edytujemy za pomocą settera.
                case 4:{
                    if(books.isEmpty()){
                        System.out.println("Brak ksiazek w bazie");
                    }else{
                        displayBase(books);
                        System.out.print("Podaj ID ksiazki ktora chcesz edytowac: ");
                        int id = addInt(new Scanner(System.in));
                        id--;
                        System.out.println("Edytuj: 1. Tytul" + " 2. Autora" + " 3. Kategorie" + " 4. Rok wydania" + " 5. Opis");
                        int editInt = addInt(new Scanner(System.in));
                        System.out.println("Nowy:");
                        switch (editInt){
                            case 1:
                                Book.setTitle(books.get(id), addText(new Scanner(System.in)));
                                break;
                            case 2:
                                Book.setAuthor(books.get(id), addText(new Scanner(System.in)));
                                break;
                            case 3:
                                Book.setCategory(books.get(id), addText(new Scanner(System.in)));
                                break;
                            case 4:
                                Book.setPublishYear(books.get(id), addInt(new Scanner(System.in)));
                                break;
                            case 5:
                                Book.setDescription(books.get(id), addText(new Scanner(System.in)));
                                break;
                        }
                    }
                    break;
                }
                //Jeżeli są książki w bazie to wyświetlamy wszystkie.
                case 5:{
                    if(books.isEmpty())
                        System.out.println("Brak ksiazek w bazie");
                    else{
                      displayBase(books);
                    }
                    break;
                }
            }
        }
    }
    //otwarcie bazy z pliku oraz zapisanie wszystkich ksiązek w tablicy
    public static void loadBase(ArrayList<Book> books){
        BufferedReader readBase;
        try {
            readBase = new BufferedReader(new FileReader("baza.txt"));
            int i = 0;
            while(readBase.readLine() != null){
                i++;
            }
            readBase = new BufferedReader(new FileReader("baza.txt"));
            for(int k = 0;k<i/5;k++){
                String title = readBase.readLine();
                title = title.substring(title.indexOf(" ")+1, title.length());
                String category = readBase.readLine();
                category = category.substring(category.indexOf(" ")+1, category.length());
                String author = readBase.readLine();
                author = author.substring(author.indexOf(" ")+1, author.length());
                String year = readBase.readLine();
                year = year.substring(year.indexOf(" ",4)+1, year.length());
                int yearInt = Integer.parseInt(year);
                String description = readBase.readLine();
                description = description.substring(description.indexOf(" ")+1, description.length());
                Book book = new Book(author, title, category, description, yearInt);
                books.add(book);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Nie ma pliku bazy danych");
        } catch (IOException ex) {
             
        }
    }
    //zapisanie bazy do pliku np. po aktualizacjilub  dodaniu nowej ksiązki
    public static void saveBase(ArrayList<Book> books){
        BufferedWriter saveBase; 
        try {
            saveBase = new BufferedWriter(new FileWriter("baza.txt"));
            for(Book book:books){
                saveBase.write("Tytuł: " + book.getTitle(book));
                saveBase.newLine();
                saveBase.write("Kategoria: " + book.getCategory(book));
                saveBase.newLine();
                saveBase.write("Autor: " + book.getAuthor(book));
                saveBase.newLine();
                saveBase.write("Rok wydania: " + book.getPublishYear(book));
                saveBase.newLine();
                saveBase.write("Opis: " + book.getDescription(book));
                saveBase.newLine();
            }
            saveBase.close();
        } catch (IOException ex) {
            
        }
    }
    //metoda używana do wyświetlenia całej bazy
    public static void displayBase(ArrayList<Book> books){
        for(Book book : books){
            book.display(book,books);
        }
    }
    //metoda w której dodajemy nową książke do bazy
    public static void addBook(ArrayList<Book> books){
         Scanner input = new Scanner(System.in);
         System.out.print("Podaj tytul: ");
         String title = addText(input);
         System.out.print("Podaj autora: ");
         String author = addText(input);
         System.out.print("Podaj kategorie: ");
         String category = addText(input);
         System.out.print("Podaj opis: ");
         String description = addText(input);
         System.out.print("Podaj rok wydania: ");
         int publishYear = addInt(input);
         books.add(new Book(title, author, category, description, publishYear));
     }
    
    //metoda w której szukamy ksiązki w bazie
    //na początku sprawdzamy czy użytkownik szuka według roku - jeżeli tak to używamy metody addInt
    //jeżeli użytkownik nie szuka według roku to używamy metody addInt
    public static void searchBooks(ArrayList<Book> books, String searchBy){
        System.out.println("Szukaj: ");
        String searchString = "";
        int searchInt = 0;
        
        if(searchBy.equals("publishYear")){
            searchInt = addInt(new Scanner(System.in));
        }else{
            searchString = addText(new Scanner(System.in));
        }
        //tworzymy nową tablicę w której zapiszemy ksiązki, które zostały wyszukane
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        
        //ignorujemy wielkość liter - jeżeli istnieje w bazie tytuł/autor/kategoria/rok wydania ksiązki to dodajemy je do nowej tablicy
        for(Book book : books){
            if("title".equals(searchBy)){
                if(book.getTitle(book).toLowerCase().contains(searchString.toLowerCase()) == true){
                    //dodajemy do tablicy znalezionych książek
                    foundBooks.add(book);
                }
            }else if("author".equals(searchBy)){
                if(book.getAuthor(book).toLowerCase().contains(searchString.toLowerCase()) == true){
                    //dodajemy do tablicy znalezionych książek
                    foundBooks.add(book);
                }
            }else if("category".equals(searchBy)){
                if(book.getCategory(book).toLowerCase().contains(searchString.toLowerCase()) == true){
                    //dodajemy do tablicy znalezionych książek
                    foundBooks.add(book);
                }
            }else if("publishYear".equals(searchBy)){
                if(searchInt == book.getPublishYear(book)){
                    foundBooks.add(book);
                }
            }
        }
        System.out.println("");
        System.out.println("Wynik wyszukiwania: ");
        System.out.println("");
        for(Book book : foundBooks){
            book.display(book,foundBooks);
        }
        if(foundBooks.isEmpty()){
            System.out.println("Nie ma takich ksiazek");
            System.out.println("");
        }
    }
     
    //metoda w której sprawdzamy czy to co podał użytkownik nie jest puste np. enter
    //jeżeli jest puste to wpisuje jeszcze raz
    //jeżeli nie to zwracamy to co użytkownik wpisał
    public static String addText(Scanner inputString){
        String text = inputString.nextLine();
        if(text.equalsIgnoreCase("")){
            System.out.println("Wpisz jeszcze raz");
            return addText(inputString);
        }else{
            return text;
        }
    }
    //metoda w której sprawdzamy czy to co podał użytkownik na pewno jest liczbą
    //jeżeli nie jest liczbą to wyświetlenie komunikatu oraz wpisanie jeszcze raz
    //jeżeli wszystk jest w porządku to zwracamy to co wysłał użytkownik
    public static int addInt(Scanner inputInt){
        int number;
        try{
            number = Integer.parseInt(inputInt.nextLine());
        }catch (NumberFormatException ex) {
            System.out.println("Wpisz jeszcze raz");
            return addInt(inputInt);
        }
        return number;
    }
}
