package bddTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Logger {
	  public String localtime;
	  public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	  
	  public void output(String a,int b){
		  FileOutputStream out =null;
		  Date now =new Date();
		  localtime=dateFormat.format(now);
		  String str1=localtime+"\r\n";
		  String str2=String.valueOf(b);
		  
		   try{
			   out=new FileOutputStream("C:\\IOTest\\logger.txt",true);
			   out.write("------".getBytes());
			   out.write(str1.getBytes());
			   out.write(str2.getBytes());
			   out.write("\r\n".getBytes());
			
		   }
		   catch(FileNotFoundException e){
			   e.printStackTrace();
		   }
		   catch(IOException e){
			   e.printStackTrace();
		   }  
	  } 
	  
	  public void output(String signal,String operation){
		   FileOutputStream out =null;
		   Date now = new Date(); 
		   localtime=dateFormat.format(now);
		   String str1=localtime+"\r\n";
		   String str2=operation+"\r\n";
		   
		   
		   try{
			   out=new FileOutputStream("C:\\IOTest\\path.txt",true);
			   out.write(signal.getBytes());
			   out.write("------".getBytes());
			   out.write(str1.getBytes());
			   out.write(str2.getBytes());
			   out.write("\r\n".getBytes());
			
		   }
		   catch(FileNotFoundException e){
			   e.printStackTrace();
		   }
		   catch(IOException e){
			   e.printStackTrace();
		   }
	   
	   }

	public void writeToLog(String stateChangeResult) {
		 FileOutputStream out =null;
		   Date now = new Date(); 
		   localtime=dateFormat.format(now);
		   String str1=localtime+"\r\n";
		   try{
			   out=new FileOutputStream("C:\\IOTest\\logger.txt",true);
			   out.write("---------".getBytes());
			   out.write(str1.getBytes());
			   out.write(stateChangeResult.getBytes());	
		   }
		   catch(FileNotFoundException e){
			   e.printStackTrace();
		   }
		   catch(IOException e){
			   e.printStackTrace();
		   }
	}
}
