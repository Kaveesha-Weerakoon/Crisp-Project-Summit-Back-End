package com.example.Crisp.exception.deleteprohibited;

public class DeleteProhibited extends RuntimeException{
    public DeleteProhibited ()
    {
        super("Deletion is Prohibited");
    }
}
