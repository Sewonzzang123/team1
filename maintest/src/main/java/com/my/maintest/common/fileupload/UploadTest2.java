package com.my.maintest.common.fileupload;

import java.io.File;
import java.util.Date;

public class UploadTest2 {

	public static void main(String[] args) {
		// 객체가 디렉토리를 나타냄
		File myDir = new File("C:\\Users\\DSY\\Desktop");

		System.out.println(myDir.getAbsolutePath() + (myDir.isDirectory() ? " is " : " is not ") + "a directory");
		System.out.println("The parent of " + myDir.getName() + " is " + myDir.getParent());
		
		
		//디렉토리 내의 파일 리스트 호출
		File[] contents = myDir.listFiles();
		
		//파일 리스트를 출력
		if(contents !=null) {
			
			System.out.println("\nThe" + contents.length + "items in the directory" + myDir.getName()
			+"are");
			
			
			for(int i = 0 ; i < contents.length ; i ++) {
				System.out.println(contents[i] + "is a " + (contents[i].isDirectory()?"directory":"file")
						+ new Date(contents[i].lastModified()));
			}
			}else{
				System.out.println(myDir.getName() + " is not a directory");

			}
			System.exit(0);
		
		
		return;

	}

}
