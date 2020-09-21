package com.my.maintest.common.fileupload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class UploadTest4 {
public static void main(String[] args) {
	
	
	String filePath = "C:\\Users\\DSY\\Desktop\\test.txt";
	File aFile = new File(filePath);
	FileOutputStream outputFile = null; //스트림에 대한 참조를 저장할 장소
	if(aFile.isFile()) {
		File newFile = aFile; //원래 파일 가지고 시작함.
		//유일한 이름이 될때까지 파일이름에 "_old"를 덧붙임
		do {
			
			String name = newFile.getName(); //파일의 이름을 읽어옴
			int period  = name.indexOf("."); //확장자 구분자를 찾음
			newFile = new File(newFile.getParent(), name.substring(0,period)+"_old"+name.substring(period));
			
		}while(newFile.exists()); //newFile이 존재하는 동안  (이름이 바꾸기 )
		aFile.renameTo(newFile);		
	}
	
	
	//새로운 파일생성
	try {
		outputFile = new FileOutputStream(aFile);
		System.out.println("text.txt output Stream created ");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
System.exit(0);
	
	
	
	
}

}

