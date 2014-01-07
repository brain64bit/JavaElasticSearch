/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.minified.model;

import io.searchbox.annotations.JestId;

/**
 *
 * @author agung
 */
public class Book {
    
    @JestId
    private String uuid;
    private String title;
    private String author;
    private String description;

    public Book() {
    }

    public Book(String uuid, String title, String author, String description) {
        this.uuid = uuid;
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.title + " | "+this.author; //To change body of generated methods, choose Tools | Templates.
    }
    
}
