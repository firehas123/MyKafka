
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeClass {
    private String pattern = "HH:mm:ss:SSS";
    String date;
    SimpleDateFormat simpleDateFormat;
    public TimeClass(){
        simpleDateFormat = new SimpleDateFormat(pattern);
    }
    public String getTime(){
        date = simpleDateFormat.format(new Date());
        return date;
    }
}
