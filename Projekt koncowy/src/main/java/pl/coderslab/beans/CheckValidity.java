package pl.coderslab.beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValidity {
    public static boolean isNameValid(String name){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{8,20}$");
        Matcher matcher = pattern.matcher(name);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isPasswordValid(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$");
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }else{
            return false;
        }
    }
}
