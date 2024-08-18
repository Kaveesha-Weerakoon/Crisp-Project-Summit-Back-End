package com.example.Crisp.exception.notfound;


public class NotFoundException extends RuntimeException{
    public NotFoundException(String id)
    {
      super("Could not found the Resource with id "+id);

    }
}
