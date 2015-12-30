package xyz.iseeyou.sayhi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	private static final Pattern EMAIL_ADDRESS
    = Pattern.compile(
			"[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
					"\\@" +
					"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
					"(" +
					"\\." +
					"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
					")+"
	);

	public static boolean isValidEmail(String email) {
	    return EMAIL_ADDRESS.matcher(email).matches();
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		String regExp = "^[1][0-9]{10}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phoneNumber);
		return m.find();
	}
	
	public static boolean isValidUsername(String str){
		if(str == null || str == "")
			return false;
		
		str = str.trim();
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(!isChineseChar(c) && !isDigit(c) && !isEnglishChar(c))
				return false;
		}

		return true;
	}
	
	public static boolean isChineseChar(char c) {
		return String.valueOf(c).matches("[\u4e00-\u9fa5]");
	}

	public static boolean isDigit(char c) {
		return String.valueOf(c).matches("[0-9]");
	}

	public static boolean isEnglishChar(char c) {
		return String.valueOf(c).matches("[a-zA-Z]");
	}
}
