
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false); // This makes sure the date is strictly parsed
        try {
            Date parsedDate = dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
