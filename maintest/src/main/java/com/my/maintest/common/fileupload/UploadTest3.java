package com.my.maintest.common.fileupload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class UploadTest3 {
public static void main(String[] args) {
	
	
	//guarantee File exiting
	
	String fileName = "C:\\Users\\DSY\\Desktop\\aa\\text.txt";
	File aFile = new File(fileName);
	
	//경로가 파일인지를 확인
    if (aFile.isDirectory()) {

      // 오류 메세지 후에 종료함

      // 여기서 키보드로부터 입력을 받아 다시 시도하도록 할 수 있음
      System.out.println("The path " + aFile.getPath()
                         + " does not specify a file. Program aborted.");
      System.exit(1);
    }

    // 파일이 존재하지 않을 경우
    if (!aFile.isFile()) {
      // 부모 디렉토리를 확인
      aFile = aFile.getAbsoluteFile();
      File parentDir = new File(aFile.getParent());
      
      System.out.println(aFile.getParent());
      
      if (!parentDir.exists()) {   // ... and create it if necessary
        parentDir.mkdirs();
      }
    }


		
		//스트림에 대한 참조를 저장할 장소(파일을 생성하고 새로 생성된 파일을 참조할 변수 설정)ㄴ
		FileOutputStream outputFile = null;
		
		//데이터를 추가하는데 사용될 스트림 생성
		try {
			outputFile = new FileOutputStream(aFile, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.exit(0);
				
		
	}
	
	
	
}

