package org.ali.convert;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class XmlReader extends LoadValues {

	public static void main(String[] args) throws IOException {
		try {
			//nvd2002
			FileReader fin = new FileReader("C:\\Documents and Settings\\ali"+
			"\\My Documents\\my_folders\\my_research_new\\draft_8_28_11\\nvd2002.xml");
			/*FileReader fin = new FileReader("C:\\Documents and Settings\\ali"+
					"\\My Documents\\my_folders\\my_research_new\\draft_8_28_11\\nvd2004.xml");
					*/
			
			Scanner src = new Scanner(fin);
			String nln = "\n";
			String str;
			String linePub;
			String lineId="";
			int dateLocation =1;
			boolean setId;
			DateFormat formatter ; 
			 Date date ;
			while (src.hasNextLine()) {
				// get the new line
		
				str = src.nextLine();
				if (str.contains("<vuln:cwe id=")) {
					//System.out.println(lineId+ ","+linePub);
					
					lineId = str.replace("<vuln:cwe id=\"", "");
					lineId= lineId.replace(" ", "");
					lineId=lineId.substring(1, 7);
					lineId=lineId.replace("\"", "");
					//System.out.print(lineId);
				}
					if ( str.contains(":published-datetime")) {

						//System.out.println(str);
						linePub = str.replace("<vuln:published-datetime>", "");
						linePub=linePub.replace(" ","");
						linePub=linePub.substring(0, 10);
						if(!lineId.equals("")){
						System.out.println(lineId+","+linePub);
						
						  formatter = new SimpleDateFormat("yyyy-MM-dd");
						  date = (Date)formatter.parse(linePub);  
						
						insertData(lineId,date);
						}

					}

				}
			fin.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
}
	
}




