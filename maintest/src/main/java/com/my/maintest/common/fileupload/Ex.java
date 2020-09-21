package com.my.maintest.common.fileupload;

import java.io.File;

public class Ex {

	
	public static void main(String[] args) {
		File f = null;
		File f1 = null;
		String v;
		boolean bool = false;
		
		
		
		//create new 
		f= new File("c:/users/dsy/desktop/bb/texx.txt");
		
		 //returns abstract parent pathname 
		f1 = f.getParentFile();
		System.out.println("getParentFile " + f1);
		
		// absolute path from abstract pathanme
		v = f1.getAbsolutePath();		
		System.out.println("getAbsolutePath " + v);
		
		bool = f.exists();		
		System.out.println("bool " + bool);
		
		bool = f1.exists();		
		System.out.println("bool2 " + bool);
		
		f1.mkdir();

		
	}
	
	
	
}
