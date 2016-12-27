package com.kolyadko_polovtseva.book_maze.row_model.impl;

import com.kolyadko_polovtseva.book_maze.entity.Book;
import com.kolyadko_polovtseva.book_maze.row_model.RowModel;

/**
 * Created by Nadzeya_Polautsava on 12/4/2016.
 */

public class BookRowModel extends RowModel {
    private Book book;

    public BookRowModel(Book book) {
        super(book.getName(), false, book.getImageUrl());
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
