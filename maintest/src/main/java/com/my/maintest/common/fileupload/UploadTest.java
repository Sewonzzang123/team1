package com.my.maintest.common.fileupload;

import java.io.File;

public class UploadTest {

	public static void main(String[] args) {
		// 객체가 디렉토리를 나타냄
		File myDir = new File("C:\\Users\\DSY\\Desktop");

		System.out.println(myDir + (myDir.isDirectory() ? " is" : " is not") + " a directory.");

		
		
		
		//객체가 파일을 나타냄
		File myFile = new File(myDir, "purple.jpg");
		
		System.out.println(myFile + (myFile.exists() ? " does" : " does not") + " exist");

		System.out.println(myFile + (myFile.isFile() ? " is" : " is not") + " a file.");
		System.out.println(myFile + (myFile.isHidden() ? " is" : " is not") + " hidden");
		System.out.println("You can" + (myFile.canRead() ? " " : "not ") + "read " + myFile);
		System.out.println("You can" + (myFile.canWrite() ? " " : "not ") + "write " + myFile);
		
		
		
		return;

	}

}
