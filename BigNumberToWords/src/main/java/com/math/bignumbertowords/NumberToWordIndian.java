package com.math.bignumbertowords;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class NumberToWordIndian {
	
	private static Map<String, String> aMap = new HashMap<String, String>();

	public static String convertToNumberName(String str) {
		NumberUtil.setIndianNumberNameBase(aMap);
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
				if((numLen - 1) % 2 == 0 ) {
					 int st = 0;
			         int ed = 2;
			         int x = numLen - 3;
			         int zeroLen = numLen; 
			         while (st < x) {
			        	 String temp = getNumberNameFor2Digit(num.substring(st, ed));
			     		 if(st==0 && ed==2) {
				     		 sb.append(temp);
			     			 sb.append(aMap.get("10^"+(zeroLen-1)));
			     		 } else if(temp != null && !temp.equals("")) {
								sb.append(temp + aMap.get("10^"+(zeroLen-1)));
			     		 }
			     		 st = st + 2;
			        	 ed = ed + 2;
			        	 zeroLen = zeroLen - 2;
			         }
					sb.append(getNumberNameFor3Digit(num.substring(x, numLen)));
			         
				} else {
			         int st = 0;
			         int ed = 1;
			         int x = numLen - 3;
			         int zeroLen = numLen;
			         
			         while (st < x) {
			        	 String temp = getNumberNameFor1Digit(num.substring(st, ed));
			     		 if(st==0 && ed==1) {
			     			 sb.append(temp);
			     			 sb.append(aMap.get("10^"+zeroLen));
			     			 
			     		 } else {
				        	 String temp1 = getNumberNameFor2Digit(num.substring(st, ed));
			     		 
			     			 if(temp1 != null && !temp1.equals("")) {
			     		 
					     		sb.append(temp1);
					     		sb.append(aMap.get("10^"+zeroLen));
			     			 }
			     		 }
			     		if(st==0)	 
			     			st = st + 1;
			     		else
			     			st = st + 2;
			        	 ed = ed + 2;
			        	 zeroLen = zeroLen - 2;
			         }
					sb.append(getNumberNameFor3Digit(num.substring(x, numLen)));
				}
				break;
			} 
		}
//		System.out.println(num + "(10^" + (num.length()-1) + ")" + "\n[" + NumberUtil.getNumberInIndianFormat(num) + "]\n" + sb.toString() + "\n");
//		NumberUtil.writeToFile(file, 
//				num + "(10^" + (num.length()-1) + ")" + "\n[" + NumberUtil.getNumberInIndianFormat(num) 
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
			return aMap.get(num); // 11 to 19
			
		} else  {
			if(num.charAt(1) == '0')
				return aMap.get(num.charAt(0)+"0"); // > 20,30,40,50,60,70,80,90
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