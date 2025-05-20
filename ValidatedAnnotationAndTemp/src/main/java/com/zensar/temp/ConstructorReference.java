package com.zensar.temp;
interface Messageable{  
    Message getMessage(String msg);  
}  
class Message{  
    Message(String msg){  
        System.out.print(msg);  
    }  
    Message(){  
    }  
}  
public class ConstructorReference {  
    public static void main(String[] args) {  
        Messageable hello = Message::new;  
        hello.getMessage("Hello");  
    }  
}