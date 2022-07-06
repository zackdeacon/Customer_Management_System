package Utility;

import javafx.fxml.Initializable;

import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class timezones implements Initializable {

    static ZoneId UTC = ZoneId.of("UTC");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

//    public static LocalDateTime localToUTC(LocalDate date, LocalTime time) {
//        LocalTime currTime = time;
//        LocalDate currDate = date;
//        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
//        ZonedDateTime local = ZonedDateTime.of(currDate, currTime, localZoneID);
//        ZonedDateTime utcToLocal = local.withZoneSameInstant(UTC);
//        LocalTime newTime = utcToLocal.toLocalTime();
//        LocalDate newDate = utcToLocal.toLocalDate();
//        return LocalDateTime.of(newDate, newTime);
//    }

//    public static LocalDateTime utcToLocal(LocalDate date, LocalTime time) {
//        LocalTime utcTime = time;
//        LocalDate utcDate = date;
//        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
//        ZonedDateTime local = ZonedDateTime.of(utcDate, utcTime, UTC);
//        ZonedDateTime localToUTC = local.withZoneSameInstant(localZoneID);
//        LocalTime newTime = localToUTC.toLocalTime();
//        LocalDate newDate = localToUTC.toLocalDate();
//        return LocalDateTime.of(newDate, newTime);
//    }

    public static LocalDateTime localToEST(LocalDate date, LocalTime time) {
        ZonedDateTime local = ZonedDateTime.of(date, time, ZoneId.systemDefault());
        ZonedDateTime localToEST = local.withZoneSameInstant(ZoneId.of("America/New_York"));
        return localToEST.toLocalDateTime();
    }

    public static LocalDateTime estToLocal(LocalDate date, LocalTime time) {
        ZonedDateTime estToLocal = ZonedDateTime.of(date, time, ZoneId.systemDefault());
        ZonedDateTime local = estToLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
        return local.toLocalDateTime();
    }
}
