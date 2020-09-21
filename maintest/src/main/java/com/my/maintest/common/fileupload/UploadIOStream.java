package com.my.maintest.common.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadIOStream {
	
	public static void main(String[] args) {
		
		//포인트 : read()메소드
		//파일의 시작부터 차례대로 바이트 단위로 읽어들여 그값을 정수 하위 8bit에 저장해서 반환
		
		
		File file = new File("c:/users/dsy/desktop/test.txt");
		File file1 = new File("c:/users/dsy/desktop/test - 복사본.txt");
		FileInputStream fis = null; 
		FileOutputStream fos = null; 
		
		System.out.println(file.length());
		try {
			//read()메소드에 512byte 배열을 전달함으로써 파일로 부터 읽어드린 내용이 바이트배열인 buffer에 저장됨.
			byte[] buffer = new byte[512];
			int readCnt = 0;
			
			//File file을 매개변수로 
			//fis = new FileInputStream(file);
			fis = new FileInputStream("c:/users/dsy/desktop/test.txt");
			
			
			//fileoutputstream 경로에 파일이 존재하면 덮어쓰기
			//없으면 새로 생성
			fos = new FileOutputStream(file1);
			
			
			//경로 + 파일이름을 매개변수로
			FileOutputStream fos1 =  new FileOutputStream("c:/users/dsy/desktop/test - 복사본1.txt");
			//덮어쓰기 X
			//FileOutputStream fos2 =  new FileOutputStream("c:/users/dsy/desktop/test - 복사본1.txt", true);
			
			

			
			
			
			
			while((readCnt = fis.read(buffer)) != -1) {	
				
			
			fos.write(buffer,0,readCnt);		
			fos1.write(buffer, 0, readCnt);
			//fos2.write(buffer, 0, readCnt);
			}
			
			System.out.println("readCnt ::" + readCnt);
			
			System.out.println("파일카피 완료");
			fis.close();
			fos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
