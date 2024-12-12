package com.biOneMany.entity;

import lombok.Data;

@Data
public class ResponseStructure <T>{

    public int status;
    public String message;
    public T data;


}
