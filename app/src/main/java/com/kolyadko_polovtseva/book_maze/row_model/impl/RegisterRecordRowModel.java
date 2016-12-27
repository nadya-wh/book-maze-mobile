package com.kolyadko_polovtseva.book_maze.row_model.impl;

import com.kolyadko_polovtseva.book_maze.entity.RegisterRecord;
import com.kolyadko_polovtseva.book_maze.row_model.RowModel;

/**
 * Created by Nadzeya_Polautsava on 12/10/2016.
 */

public class RegisterRecordRowModel extends RowModel {

    private RegisterRecord registerRecord;

    public RegisterRecordRowModel(RegisterRecord registerRecord) {
        super(registerRecord.getLibraryBook().getBook().getName(),
                false,
                registerRecord.getLibraryBook().getBook().getImageUrl());
        this.registerRecord = registerRecord;
    }

    public RegisterRecord getRegisterRecord() {
        return registerRecord;
    }

}
