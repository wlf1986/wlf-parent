package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月25日 19:16
 */
public class _20080809_jin {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String jiu = "2008-08-09";
        Date date = format.parse(jiu);
        Date now = new Date();
        Long n = now.getTime()-date.getTime();
        Long day = n/1000/60/60/24;
        System.out.println(day);
    }
}
