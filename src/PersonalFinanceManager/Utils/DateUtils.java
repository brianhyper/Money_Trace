package PersonalFinanceManager.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getCurrentFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        return dateFormat.format(new Date());
    }

    public static String formatDate(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
        return dateFormat.format(new Date(timestamp));
    }

    public static String formatDateWithTime(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy 'at' h:mm a");
        return dateFormat.format(new Date(timestamp));
    }

    public static long currentDatePlusDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTimeInMillis();
    }

    public static long currentDatePlusHours(int hours) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTimeInMillis();
    }

    public static long getStartOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    public static long getEndOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    public static boolean isSameDay(long timestamp1, long timestamp2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(timestamp1);
        cal2.setTimeInMillis(timestamp2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
}
