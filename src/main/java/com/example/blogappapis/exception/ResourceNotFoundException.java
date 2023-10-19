package com.example.blogappapis.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    long value;


    public ResourceNotFoundException(String resourceName,String fieldName,long value){
        super(String.format("%s not found with %s : %s",resourceName,fieldName,value));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.value = value;
    }



}
