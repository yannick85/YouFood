package com.rman.youfood.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.rman.youfood.entity.Product;




public class YouFoodUtil {
	private static YouFoodUtil instance;
	public static YouFoodUtil getInstance(){
		if(instance == null) {
			instance = new YouFoodUtil();
		}
		return instance;
	}
	public String MD5(String input){
		byte[] uniqueKey = input.getBytes();
		byte[] hash = null;
		try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		} catch (NoSuchAlgorithmException e) {
			throw new Error("no MD5 support in this VM");
		}
		StringBuffer hashString = new StringBuffer();
		for ( int i = 0; i < hash.length; ++i ) {
			String hex = Integer.toHexString(hash[i]);
		if ( hex.length() == 1 ) {
			hashString.append('0');
			hashString.append(hex.charAt(hex.length()-1));
		} else {
			hashString.append(hex.substring(hex.length()-2));
		}
		}
		return hashString.toString(); 
	}
	public List<Product> minusLP(List<Product> lpall,List<Product> lpmin){
		Boolean bool=true;
		List<Product> lpout = new ArrayList<Product>();
		for (Product productAll : lpall) {
			for (Product productMin : lpmin) {
				if(productAll.getId() == productMin.getId()){
					bool =false;
				}
			}
			if(bool){
				lpout.add(productAll);
			}
			bool=true;
		}
		
		
		return lpout;
	}
}
