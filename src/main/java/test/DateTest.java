package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateTest {

	public static void main(String[] args) {
		java.util.Date date = new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		System.out.println(format.format(date));

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("JST"));
		c.clear(); // ミリ秒もクリアする必要があれば
		c.set(2016, 6 - 1, 7, 13, 40, 30);
		java.util.Date tmpTime = c.getTime();
		System.out.println(tmpTime);


		// 任意の日付文字列
		String inpDateStr = "2016/06/06 00:00:00";

		// 取り扱う日付の形にフォーマット設定
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		try {
			// Date型に変換( DateFromatクラスのperse() )
			java.util.Date dateTime = sdformat.parse(inpDateStr);
			System.out.println(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
