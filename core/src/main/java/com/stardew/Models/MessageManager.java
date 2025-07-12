package com.stardew.Models;

public class MessageManager {
    private static StringBuilder output= new StringBuilder();
    public static void getMessage(Result result){
        output.append("[success: " + result.success() + ", message: "+ result.message() + "]");
        System.out.println(result.message());
    }
}
