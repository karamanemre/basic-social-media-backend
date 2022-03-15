package com.emrekaraman.springsocial.core.utilities;

public class ErrorResult extends Result{

    private ErrorResult(){
        super(false);
    }

    private ErrorResult(String message){
        super(false,message);
    }
}
