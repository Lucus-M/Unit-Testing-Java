package src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

  public static boolean zip(String input) {
    Pattern fiveDigits = Pattern.compile("^[0-9]{5}$");
    Matcher match = fiveDigits.matcher(input);
    return match.matches();
  }

  private static boolean minor(int age) {
		if(age > 17) {
			return false;
		}
		
		return true;
	}

  private static boolean email(String input) {
		if(input.contains("@") && input.contains("."))
      return true;
		
		return false;
	}

  public static boolean isLat(Double value) {
    return value >= -90 && value <= 90;
  }

  public static boolean isLng(Double value) {
    return value >= -180 && value <= 180;
  }

  public static boolean isDomain(String input){
		//not perfect, but probably 95% accurate.
		Pattern p = Pattern.compile("^[a-zA-Z0-9-]{2,253}\\.[a-zA-Z]{1}[a-zA-Z0-9]{1,}$");
		Matcher m = p.matcher(input);
		
		return m.find();
	}

  public static boolean isInDomain(String domain, String url) {
		return url.indexOf(domain) > -1 ;
	}
  
  public static boolean isUrl(String input) {
		//not perfect, but probably 95% accurate.
		Pattern p = Pattern.compile("^http(s)?:\\/\\/(.*?)\\.[a-z]{2,52}\\/.*$");
		Matcher m = p.matcher(input);
		
		return m.find();
	}
  
  /**
   * TODO: Does this look correct? Can you improve it to pass more tests?
   */
  public static char grade(Double value) {
    if(value < 60){
      return 'F';
    }else if(value < 70){
      return 'D';
    }else if(value < 80){
      return 'C';
    }else if(value < 90){
      return 'B';
    }

    return 'A';
  }

  /**
   * TODO: Does this look correct? Can you improve it to pass more tests?
   */
  public static String sanitize(String sql) {
    sql = sql.toUpperCase().replaceAll("ADMIN", "");
    sql = sql.toUpperCase().replaceAll("OR", "");
    sql = sql.toUpperCase().replaceAll("COLLATE", "");
    sql = sql.toUpperCase().replaceAll("DROP", "");
    sql = sql.toUpperCase().replaceAll("AND", "");
    sql = sql.toUpperCase().replaceAll("UNION", "");
    sql = sql.replaceAll("/*", "");
    sql = sql.replaceAll("*/", "");
    sql = sql.replaceAll("//", "");
    sql = sql.replaceAll(";", "");
    sql = sql.replaceAll("||", "");
    sql = sql.replaceAll("&&", "");
    sql = sql.replaceAll("--", "");
    sql = sql.replaceAll("#", "");
    sql = sql.replaceAll("=", "");
    sql = sql.replaceAll("!=", "");
    sql = sql.replaceAll("<>", "");

    return sql;
  }

  /**
   * TODO: Does this look correct? Can you improve it to pass more tests?
   */
  public static String stripNull(String filter) {
    filter = filter.toUpperCase().replace("NULL", "");

    return filter;
  }

  /**
   * TODO: Implement IP address validation
   * Consider white-listing, regex
   */
  public static boolean ip(String input){
    return true;
  }

  /**
   * TODO: Implement MAC address validation
   * Consider white-listing, regex
   * allow : - and whitespaces
   */
  public static boolean mac(String input){
    return true;
  }

  /**
   * TODO: Implement hash value validation
   * Consider white-listing, regex
   */
  public static boolean md5(String input){
    return true;
  }
}
