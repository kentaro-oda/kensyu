package kensyuJuly3rd.problem3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 入力した誕生日の入力チェック(Problem2とProblem2_2の両方のクラスで使用)
 *
 * @author k_oda
 *
 */
public class DateCheck {

	/**
	 * 正規表現の設定
	 *
	 * p 正規表現を設定したフィールド(4桁の西暦、1〜12月、1〜31日)
	 */
	private static Pattern p = Pattern.compile("^\\d{4}(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])$");

	private static Pattern p2 = Pattern.compile("^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");

	/**
	 * 入力された値が実在する
	 *
	 * @param birthday メインで入力された誕生日
	 *
	 * m 引数が正規表現に沿っているかを判定
	 *
	 * dtf 実在する日付を判定するためのフィールド
	 * @return true/false メインクラスのif文のためのboolean型の値を返す
	 */
	public static boolean isBirthday(String birthday) {
		Matcher m = p.matcher(birthday);
		if(m.find()) {
			try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(birthday, dtf);
			return true;
			}catch(DateTimeParseException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isBirthday2(String birthday) {
		Matcher m = p2.matcher(birthday);
		if(m.find()) {
			try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
			LocalDate.parse(birthday, dtf);
			return true;
			}catch(DateTimeParseException e) {
				return false;
			}
		} else {
			return false;
		}
	}

}
