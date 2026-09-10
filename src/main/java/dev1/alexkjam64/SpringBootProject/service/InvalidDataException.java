package dev1.alexkjam64.SpringBootProject.service;

public class InvalidDataException extends Exception{
    public InvalidDataException(String message){
        super(message);
    }
}
