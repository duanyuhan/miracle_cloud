package miracle.util.course;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
    public static boolean isMatch(String str, int type) {
        Pattern pattern = java.util.regex.Pattern.compile("^[a-zA-Z]+");
        Pattern pattern2 = java.util.regex.Pattern.compile("^[a-zA-Z0-9]+");
        Pattern pattern3 = java.util.regex.Pattern.compile("^[0-9]+");
        Matcher m = null;
        if (type == 1) {
            m = pattern.matcher(str);
        } else if (type == 2) {
            m = pattern2.matcher(str);
        } else if (type == 3) {
            m = pattern3.matcher(str);
        }
        return m.matches();
    }
}
