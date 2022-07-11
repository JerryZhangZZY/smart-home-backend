package AccessSoftware;

import java.util.regex.Pattern;

public class IsNumber {
	public static Boolean checkNumber(String str) {
		boolean int_flag = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
	    boolean double_flag = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
	    if(double_flag || int_flag) { return true; }
	    else { return false; }
	}
}
