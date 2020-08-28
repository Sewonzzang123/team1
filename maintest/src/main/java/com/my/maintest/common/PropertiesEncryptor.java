package com.my.maintest.common;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PropertiesEncryptor {

	public static void main(String[] args) {
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		// 암호키
		enc.setPassword("gkrToaWkd");

		// db암호화
//		System.out.println(enc.encrypt("oracle.jdbc.driver.OracleDriver"));
//	System.out.println(enc.encrypt("jdbc:oracle:thin:@127.0.0.1:1521:XE"));
		System.out.println(enc.encrypt("oracle.jdbc.driver.OracleDriver"));
		System.out.println(enc.encrypt("jdbc:oracle:thin:@127.0.0.1:1521:XE"));
		System.out.println(enc.encrypt("packing"));
		System.out.println(enc.encrypt("1234"));

	}

}
