package com.my.maintest.common.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadExercise {

	
	public static void main(String[] args) {
		//파일 그리고 디렉토리 를 가지는 File 객체 생성
		String PathIndecated = "c:/users/dsy/desktop/";
		String fileIndecated = "c:/users/dsy/desktop/test.txt";
		File fileWithPath = new File(PathIndecated);
		File fileWithFile = new File(fileIndecated);
		
		//디렉토리인지 체크 		
		System.out.println("패스를 가지는 파일객체가 디렉토리를 가지는지 확인 기대값 true== " + fileWithPath.isDirectory() );
		System.out.println("파일을 가르키는 파일객체가  디렉토리를 가지는지 확인 기대값 false== " + fileWithFile.isDirectory());
		//파일인지  체크
		System.out.println("패스를 가지는 파일객체가 파일을 가지는지 확인 기대값 false== " + fileWithPath.isFile() );
		System.out.println("파일을 가르키는 파일객체가  파일을 가지는지 확인 기대값 true== " + fileWithFile.isFile());
		
		//파일 존재여부
		System.out.println("패스를 가지는 파일객체 존재 여부 확인 기대값 true == " + fileWithPath.exists());
		System.out.println("파일을 가르키는 파일객체 존재 여부 확인 기대값 true == " + fileWithFile.exists());
		
		//
		System.out.println("패스를 가지는 파일객체  절대경로 참조하는중  기대값 true == " + fileWithPath.isAbsolute());
		System.out.println("파일을 가르키는 파일객체 절대경로 참조하는중  기대값 true == " + fileWithFile.isAbsolute());
		
		//getAbsolutePath() - 현재의 File 객체가 참조하는 파일 또는 디렉토리의 절대경로를 리턴한다
		//만약 파일이 절대경로를 포함하고 있다면 getPath()출력값과 동일 
		//상대경로를 참조한다면 
		//윈도우즈 환경: 현재디렉토리가 나타내는 경로의 드라이브 이름을 사용 
		//경로에 드라이브 이름이 명시되어있지 않으면, 현재 사용자 디렉토리에 대해서 절대경로가 결정
		
		

		
		//실습 시작
		//파일과 디렉토리를 생성하고 수정하기		
		//renameTo(File path) 
		
		//원본파일 : 같은 경로에 같은 이름을 가지는 파일이 있는지 체크 
		if(fileWithFile.isFile()) {
			File newFile = fileWithFile; //newFile에 객체 카피 
			do {
				
				String name = newFile.getName();
				int period = name.indexOf(".");
				//이름을 바꾼다. 
				newFile = new File(newFile.getParent(), name.substring(0,period)+"1"+name.substring(period) );
				//newFile은 새로운 이름을 가진 객체 
				
			}while(newFile.exists());//newFile이 존재하는 동안 
			
			//원본파일객체에 새로운 이름을 가지는 newFile객체를 삽입한다. 
			fileWithFile.renameTo(newFile);
		}
		

		
		//원본을 새로운 경로에 복사
		//복사할 경로 + 파일 이름
		String filename = "c:/users/dsy/desktop/aa/test_복사본.txt";
		String savingLoc = "c:/users/dsy/desktop/aa";
		File newFileWithPath = new File(savingLoc);		
		File newFileWithFile = new File(filename);		
		
		
		if(newFileWithFile.isDirectory()){
			System.out.println("0 Yes a directory");
			
			
			
			
		}else {			
			System.out.println("1 is File ");			
			File parentDir = new File(newFileWithFile.getParent());
			if(parentDir.exists()){
				System.out.println("1 parent folder exists  ");
			}else {
				System.out.println("1 parent folder not exists  ");				
				
				//경로 디렉토리 생성
				parentDir.mkdir();	
				
				//파일 복사 
				
				//fileWithFile.
				try {
					System.out.println("원본파일 크기 :: " + fileWithFile.length());
					byte[] buffer = new byte[512];
					int readCnt = 0;
					//복사 대상 파일의 스트리 객체 생성
					//FileInputStream fis = new FileInputStream(fileWithFile);
					FileInputStream fis = new FileInputStream("c:/users/dsy/desktop/test1.txt");
					
					//복사본 파일생성 존재하면 덮어쓰기 / 아니면 새로생성 / 이어 붙이기 있음.
					FileOutputStream fos = new FileOutputStream(newFileWithFile);
					//FileOutputStream fos = new FileOutputStream("c:/users/dsy/desktop/aa/test_복사본.txt");
					
					//데이터 복사
					while((readCnt = fis.read(buffer)) != -1) {
						fos.write(buffer,0,readCnt);
						}
					fis.close();
					fos.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			
			}
			
			
		
		
		
	

		
	}
	
}
