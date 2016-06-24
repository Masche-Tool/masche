/**
 * 
 */
package model;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author steffen
 *
 */
public class Helper {
	public static String getRandom() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public static String xssCleaner(String dirtyString) {
		// vgl http://www.rgagnon.com/javadetails/java-0627.html
		String cleanString = dirtyString.replaceAll("(?i)<script.*?>.*?</script.*?>", "")
				.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "").replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "")
				.replaceAll("<", "").replaceAll(">", "").replaceAll("=", "");
		return cleanString;
	}
}