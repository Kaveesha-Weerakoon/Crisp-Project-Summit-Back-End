package com.example.Crisp.exception.savefailed;


public class SavedFailed extends RuntimeException{
    public SavedFailed()
    {
        super("Saving Failed Please Check the fields");
    }
}
