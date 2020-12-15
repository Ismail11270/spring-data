package pl.posl.aei.ori.spring.util;

import pl.posl.aei.ori.spring.model.Gender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseUtils {

    public static final String MIN_DATE_STRING = "1900-01-01";

    private static SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date MIN_DATE;

    static {
        try {
            MIN_DATE = defaultDateFormat.parse(MIN_DATE_STRING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public static Date parseDate(String date) {
        try {
            return defaultDateFormat.parse(date);
        } catch (ParseException e) {
            return MIN_DATE;
        }
    }

    public static Gender parseGender(String gender){
        switch(gender.toUpperCase()){
            case "MALE":
                return Gender.MALE;
            case "FEMALE":
                return Gender.FEMALE;
            default:
                return Gender.OTHER;
        }
    }
}
