package com.timba.weatherapi.exception;

public class ThreeFavoriteCityOnlyException extends RuntimeException{
    public ThreeFavoriteCityOnlyException () {
        super("You can only add 3 favorite city");
    }
    public ThreeFavoriteCityOnlyException (String message) {
        super(message);
    }
}
