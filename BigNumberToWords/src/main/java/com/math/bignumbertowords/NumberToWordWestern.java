package com.math.bignumbertowords;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

// http://www.thealmightyguru.com/Pointless/BigNumbers.html

public class NumberToWordWestern {
	
	private static Map<String, String> aMap = new HashMap<String, String>();
	
	public static String convertToNumberName(String str) {
		NumberUtil.setWesternNumberNameBase(aMap);
		String num = String.valueOf(new BigInteger(str));
		StringBuffer sb = new StringBuffer();
		
		try {
			NumberUtil.isValidNumber(num);
		} catch (InvalidNumber e) {
			e.printStackTrace();
		}
		
		int numLen = num.length();
		for (int i = 1; i <= numLen; i++) {
			if(num.length() == 1) {
				sb.append(getNumberNameFor1Digit(num));
				break;
			} else if(num.length() == 2) {
				sb.append(getNumberNameFor2Digit(num));
				break;
			} else if(num.length() == 3) {
				sb.append(getNumberNameFor1Digit(num.substring(0,1))); // One Hundrad
				sb.append(aMap.get("10^2"));
				sb.append(getNumberNameFor2Digit(num.substring(1, numLen))); // Ten Hundrad
				break;
			} else {
			    String strTemp = NumberFormat.getNumberInstance(Locale.US).format(new BigInteger(num));
			    int count = strTemp.substring(0, strTemp.indexOf(",")).length();
				if(count == 1) { // 1,......
					int zeroLen = numLen;
					int st = 0;
					int ed = 1;
					int x = numLen - 2;
					while(st < x) {
			     		 if(st == 0 && ed == 1) {
			     			sb.append(getNumberNameFor1Digit(num.substring(st,ed))); 
							sb.append(aMap.get("10^" + zeroLen));
			     		 } else  {
			     			 String temp = getNumberNameFor3Digit(num.substring(st, ed));
			     			if(temp != null && !temp.equals("")) { 
			     				sb.append(temp);
			     				if(aMap.containsKey("10^"+(zeroLen)))
			     					sb.append(aMap.get("10^"+(zeroLen)));
			     			}
			     		 }
						if(st == 0)
							st = st + 1;
						else 
							st = st + 3;
						ed = ed + 3;
						zeroLen = zeroLen - 3;
					}
				} else if(count == 2) {  // 10,.....
					int zeroLen = numLen - 1;
					int st = 0;
					int ed = 2;
					int x = numLen - 2;
					while(st < x) {
			     		 if(st == 0 && ed == 2) {
			     			sb.append(getNumberNameFor2Digit(num.substring(st,ed))); 
							sb.append(aMap.get("10^" + zeroLen));
			     		 } else  {
			     			 String temp = getNumberNameFor3Digit(num.substring(st, ed));
			     			if(temp != null && !temp.equals("")) { 
			     				sb.append(temp);
			     				if(aMap.containsKey("10^"+(zeroLen)))
			     					sb.append(aMap.get("10^"+(zeroLen)));
			     			}
			     		 }
						if(st == 0)
							st = st + 2;
						else 
							st = st + 3;
						ed = ed + 3;
						zeroLen = zeroLen - 3;
					}
				} else if(count == 3 ) { // 100,.......
					int zeroLen = numLen - 2;
					int st = 0;
					int ed = 3;
					int x = numLen - 2;
					while(st < x) {
			     		 if(st == 0 && ed == 3) { 			     				
			     			String temp = getNumberNameFor3Digit(num.substring(st,ed));
			     			if(temp != null && !temp.equals("")) { 
			     				sb.append(getNumberNameFor3Digit(num.substring(st,ed)));
			     				sb.append(aMap.get("10^" + zeroLen));
			     			}
			     		 } else  {
			     			String temp = getNumberNameFor3Digit(num.substring(st, ed));
			     			if(temp != null && !temp.equals("")) { 
			     				sb.append(temp);
			     				if(aMap.containsKey("10^"+(zeroLen))) {
			     					//System.out.println("zeroLen: " + zeroLen);
			     					sb.append(aMap.get("10^"+(zeroLen)));
			     				}
			     			}
			     		 }
						if(st == 0)
							st = st + 3;
						else 
							st = st + 3;
						ed = ed + 3;
						zeroLen = zeroLen - 3;
					}
				}
				break;
			} 
		}
		//System.out.println(num + "(10^" + (num.length()-1) + ")" + "\n[" + NumberUtil.getNumberInWesternFormat(num) + "]\n" + sb.toString() + "\n");
//		NumberUtil.writeToFile(file, 
//				num + "(10^" + (num.length()-1) + ")" + "\n[" + NumberUtil.getNumberInWesternFormat(num) 
//				+ "]\n" + sb.toString() + "\n");
		return sb.toString().trim();
	}
	
	private static String getNumberNameFor3Digit(String num) {
		num = String.valueOf(Integer.valueOf(num));
		
		if(Integer.valueOf(num) <=0)
			return "";
		
		else if(Integer.valueOf(num) <=99)
			return getNumberNameFor2Digit(num);
		
		else if(Integer.valueOf(num) <=999)
			return getNumberNameFor1Digit(num.charAt(0)+"") + 
					aMap.get("10^2") +
					getNumberNameFor2Digit(num.substring(1, num.length()));
		
		return "";
	}

	private static String getNumberNameFor2Digit(String num) {
		num = String.valueOf(Integer.valueOf(num));
		
		if(Integer.valueOf(num) <=0)
			return "";
		else if(Integer.valueOf(num) <= 9) 
			return getNumberNameFor1Digit(num);
			
		if(Integer.valueOf(num) >= 11 && Integer.valueOf(num) <= 19) {
			return aMap.get(num);
			
		} else  {
			if(num.charAt(1) == '0')
				return aMap.get(num.charAt(0)+"0");
			else
				return aMap.get(num.charAt(0)+"0") 
						+ aMap.get(num.charAt(1)+""); // > 21
		}
	}	
	
	private static String getNumberNameFor1Digit(String num) {
		if(Integer.valueOf(num) >= 1 && Integer.valueOf(num) <= 9) 
			return aMap.get(num); // 1 to 9
		else
			return "";
	}
}