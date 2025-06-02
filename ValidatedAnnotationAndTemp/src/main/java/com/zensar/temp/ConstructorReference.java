package com.zensar.temp;
interface Messageable{  
    Message getMessage(String msg);  
}  

interface MyInterface{  
    String getMessage(String msg);  
}   

class Message{  
    Message(String msg){  
        System.out.println(msg);  
    }  
    Message(){  
    }  
    
    String getData(String data) {
    	return "Hello : "+data;
    }
}  
public class ConstructorReference {  
    public static void main(String[] args) {  
        Messageable hello = Message::new;  
        hello.getMessage("Zensar"); 
        
        MyInterface b = new Message()::getData;
        System.out.println(b.getMessage("Shahabuddin"));
    }  
}