package se.kth.pointofsale.logs;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * Created by Josef on 2019-05-15.
 */
class DateAndTimeUtility {
    static String getCurrentTime(){
        LocalDateTime currTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        return currTime.format(formatter);
    }

    static String getTimeStamp() {
        Date currTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss") ;
        return dateFormat.format(currTime);
    }
}