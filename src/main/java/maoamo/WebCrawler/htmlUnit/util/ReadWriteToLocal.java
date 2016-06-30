package maoamo.WebCrawler.htmlUnit.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class ReadWriteToLocal {
	public static void main(String args[]) {
//		writeTxt("D:\\Download\\test.txt","OK");
		Set<String> set=	readTxt("D:\\Download\\test.txt");
          for(String url:set){
        	  System.out.println(url);
          }
	}

	public static void writeTxt(String pathName,String msg) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(pathName, true);
			fw.write(msg);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Set<String> readTxt(String pathName){
		Set<String> uniqueUrl = new HashSet<String>();
		try {
			FileReader fr = new FileReader(new File(pathName));
			BufferedReader br = new BufferedReader(fr);
			String s;
			while((s=br.readLine())!=null){
				uniqueUrl.add(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
          return 	uniqueUrl;
		
	}

}
