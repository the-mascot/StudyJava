import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
public class TimeTest {
	
	public static void main(String[] args) {
		
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println(sdf.format(date));
		
	}
	
}
