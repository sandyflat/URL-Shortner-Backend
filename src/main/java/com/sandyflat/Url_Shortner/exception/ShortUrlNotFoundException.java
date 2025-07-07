package com.sandyflat.Url_Shortner.exception;

public class ShortUrlNotFoundException extends RuntimeException{
    public ShortUrlNotFoundException(String message){
        super(message);
    }
}
