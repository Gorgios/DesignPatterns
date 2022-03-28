package pl.jreclaw.onlinelibrary;

import pl.jreclaw.onlinelibrary.adapter.LibraryAdapter;
import pl.jreclaw.onlinelibrary.adapter.LibraryAdapterImpl;
import pl.jreclaw.onlinelibrary.factory.BookFactory;
import pl.jreclaw.onlinelibrary.factory.PoemFactory;
import pl.jreclaw.onlinelibrary.model.Author;
import pl.jreclaw.onlinelibrary.model.Client;
import pl.jreclaw.onlinelibrary.model.Library;
import pl.jreclaw.onlinelibrary.model.publication.Publication;

public class Main {
    public static void main(String[] args) {
        LibraryAdapter libraryAdapter = new LibraryAdapterImpl();
        Client client = new Client("Siemasz");
        Client client2 = new Client("Siemasz2");
        Author author = new Author("Bartosz", "Bartolinski");
        Author author2 = new Author("Zygmunt", "Hajek");
        Publication p1 = BookFactory.getInstance().getPublication(author, "Siema", "Content", "Wojenne");
        Publication p2 =BookFactory.getInstance().getPublication(author, "Władek", "Wladek byl w lesie ziomus", "Wojenne");
        Publication p3 =BookFactory.getInstance().getPublication(author, "Władek2", "Wladek byl w lesie znowu", "Fabularne");
        Publication p4 =PoemFactory.getInstance().getPublication(author, "Władek3", "Wladek wojna sie skonczyla", "Science fiction");

        System.out.println("Przed obserwowaniem -> lista do przeczytania");
        System.out.println(client.getPublicationsToRead());
        System.out.println("Po obserwowaniu i dodaniu -> lista do przeczytania");
        client.followAuthor(author2);
        Publication p5 = PoemFactory.getInstance().getPublication(author2, "Niezreczny",
                "Bardzo Długi to poem\nniełatwy do czytania\n nie chcesz shit mnie widzieć babe\n koncze wiec", "Nic");
        System.out.println(client.getPublicationsToRead());
        System.out.println(libraryAdapter.showDetailedPublications());
        String result = libraryAdapter.reportPublication(p1) ? "Usunieta" : "Nieusunieta";
        System.out.printf("Reportuje publikacje %s, wynik: %s%n", p1, result);
        System.out.println(libraryAdapter.showAllPoems());
        result = libraryAdapter.reportPublication(p5) ? "Usunieta" : "Nieusunieta";
        System.out.printf("Reportuje publikacje %s, wynik: %s%n", p5, result);
        System.out.println(libraryAdapter.showAllPoems());

    }
}
