package com.jkr.codetranslator.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkr.codetranslator.clients.JaxRsTranslatorClient;
import com.jkr.codetranslator.dto.TranslateDto;

@Service
public class TranslationServiceImpl implements TranslationService {
	
	 @Autowired
	    JaxRsTranslatorClient jaxClient;

	@Override
	public String translate(TranslateDto translate) {
		
		String out = null;
		
		BufferedWriter bufferedWriter = null;
		String fileExt = ".java";
        String fileName = translate.getTargetLanguage();
        try {
        	 
            String strContent = translate.getText();
            String importContent ="import java.lang.*;\n";
            File myFile = new File(fileName.concat(fileExt));
            // check if file exist, otherwise create the file before writing
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            Writer writer = new FileWriter(myFile);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(importContent);
            bufferedWriter.write(strContent);
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
       finally{
            try{
                if(bufferedWriter != null) bufferedWriter.close();
               out = compile(fileName,fileExt);
              
            } catch(Exception ex){
                 
            }
        }
       
        return out;    

    }
    
    private String compile(String fileName, String fileExt) {
    	  String line = null;
    	try {
    	 System.out.println("**********"+fileName.concat(fileExt));
         StringBuilder builder = new StringBuilder("javac -cp src  ");
         builder.append(fileName).append(fileExt);
         System.out.println(builder.toString());
         runProcess(builder.toString());
         System.out.println("********** java "+fileName);
       line = runProcess("java "+fileName);
       
         //out = new String(stream.readAllBytes(),StandardCharsets.UTF_8);
         
         System.out.println("inside Out "+line);
    	} catch (Exception e) {
            e.printStackTrace();
        }
    	return line;
	}

	private  String printLines(String cmd, InputStream ins) throws Exception {
		StringBuilder result = new StringBuilder();
        String line = null;
        BufferedReader in = new BufferedReader(
            new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd + " " + line);
            result.append(line);
        }
        return result.toString();
      }

      private  String runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        String print =printLines(command + " stdout:", pro.getInputStream());
        System.out.println("Output"+print);
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
       
        return print;
      }

}
