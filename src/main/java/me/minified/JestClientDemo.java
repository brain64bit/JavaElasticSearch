package me.minified;

import com.github.javafaker.Faker;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import java.util.List;
import java.util.UUID;
import me.minified.model.Book;
import me.minified.service.BookSearchService;

public class JestClientDemo {

    public static void main(String... args) {
        ClientConfig clientConfig = new ClientConfig.
                Builder("http://localhost:9200").
                multiThreaded(true).build();
        
        JestClientFactory factory = new JestClientFactory();
        factory.setClientConfig(clientConfig);
        JestClient client = factory.getObject();
        
        Faker faker = new Faker();
        BookSearchService searchService = new BookSearchService(client);
//        for (int i = 0; i < 10; i++) {
//            Book b  = new Book();
//            b.setUuid(UUID.randomUUID().toString());
//            b.setTitle(faker.sentence(3));
//            b.setAuthor(faker.name());
//            b.setDescription(faker.paragraph(2));
//            searchService.add(b);
//            System.out.println("add book = "+b.toString());
//        }
        
        List<Book> books = searchService.searchBooks("muller");
        for (Book book : books) {
            System.out.println("found : "+book.toString());
        }
    }
}
