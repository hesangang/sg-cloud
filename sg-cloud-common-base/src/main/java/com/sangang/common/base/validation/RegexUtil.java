package com.sangang.common.base.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static boolean check(String input,String regex)
    {
        if (input==null || input.length()==0 ||regex==null || regex.length()==0) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            return matcher.find();
        }
    }

    /*public static void main(String[] args){
        System.out.println(check("17310521023", RegexCons.MOBILE));
    }*/

}
