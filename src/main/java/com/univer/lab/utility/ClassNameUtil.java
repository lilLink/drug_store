package com.univer.lab.utility;

public class ClassNameUtil {

    public static String getClassName(){
        try {
            throw new Exception();
        } catch (Exception e) {
            return e.getStackTrace()[0].getClassName();
        }
    }
}
