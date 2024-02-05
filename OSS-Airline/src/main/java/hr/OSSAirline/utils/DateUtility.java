package hr.OSSAirline.utils;

import java.sql.Date;
import java.time.LocalDate;

public class DateUtility {
    public static Date getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return Date.valueOf(currentDate);
    }
}
