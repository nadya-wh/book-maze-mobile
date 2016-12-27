package com.kolyadko_polovtseva.book_maze.row_model.impl;

import android.icu.util.ULocale;

import com.kolyadko_polovtseva.book_maze.entity.Category;
import com.kolyadko_polovtseva.book_maze.row_model.RowModel;

/**
 * Created by Nadzeya_Polautsava on 12/2/2016.
 */

public class CategoryRowModel extends RowModel {

    private Category category;

    public CategoryRowModel(Category category) {
        super(category.getName(), false, category.getImageUrl());
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
