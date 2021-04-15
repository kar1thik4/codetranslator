package com.jkr.codetranslator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

public class FileApplicationMain {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		BufferedWriter bufferedWriter = null;

		String strContent = "public class MyNewJavaFile {        public static void main(String[] args) {              System.out.println(\"Hello World!\");        }  }";
		String fileName = "MyNewJavaFile.java";
		File myFile = new File(fileName);
		// check if file exist, otherwise create the file before writing
		if (!myFile.exists()) {
			myFile.createNewFile();
		}
		Writer writer = new FileWriter(myFile);
		bufferedWriter = new BufferedWriter(writer);
		bufferedWriter.write(strContent);
		bufferedWriter.close();
		/*
		 * System.out.println("**********");
		 * runProcess("javac -cp src MyNewJavaFile.java");
		 * System.out.println("**********"); runProcess("java -cp src MyNewJavaFile");
		 */

		try { // runProcess("pwd");
			System.out.println("**********");
			runProcess("javac -cp src MyNewJavaFile.java");
			System.out.println("**********");
			runProcess("java MyNewJavaFile");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void printLines(String cmd, InputStream ins) throws Exception {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			System.out.println(cmd + " " + line);
		}
	}

	private static void runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
		printLines(command + " stdout:", pro.getInputStream());
		printLines(command + " stderr:", pro.getErrorStream());
		pro.waitFor();
		System.out.println(command + " exitValue() " + pro.exitValue());
	}
}