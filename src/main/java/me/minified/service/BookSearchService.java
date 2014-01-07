/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.minified.service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.minified.model.Book;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 *
 * @author agung
 */
public class BookSearchService {
    
    private JestClient client;

    public BookSearchService(JestClient client) {
        this.client = client;
    }
    
    public void add(Book book){
        // check index exists?
        IndicesExists indicesExists = new IndicesExists.Builder("books")
                .build();
        try {
            JestResult result = client.execute(indicesExists);
            // if index not exist, create
            if(!result.isSucceeded()){
                CreateIndex createIndex = new CreateIndex.Builder("books").build();
                client.execute(createIndex);
            }
            
            Bulk bulk = new Bulk.Builder()
                    .addAction(new Index.Builder(book)
                            .index("books").type("book").build()).build();
            result = client.execute(bulk);
            System.out.println("success = "+result.isSucceeded()+"/n"+result.getJsonString());
        } catch (Exception ex) {
            Logger.getLogger(BookSearchService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Book> searchBooks(String param){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.queryString(param));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("books")
                .addType("book")
                .build();
        try {
            JestResult result = client.execute(search);
            return result.getSourceAsObjectList(Book.class);
        } catch (Exception ex) {
            Logger.getLogger(BookSearchService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
