package com.app.movegroove;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class validate {

	public void run_validation(){
		
		String line_data;
		
		BufferedReader inputStream = null;
		BufferedWriter outputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader("datain.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		try{
			line_data=inputStream.readLine();
			String[] coords = line_data.split(",");
			
			double x = Double.parseDouble(coords[0]);
			
			double y = Double.parseDouble(coords[1]);
			
			double z = Double.parseDouble(coords[2]);		
					
			double[] result = Filtering.compute(x, y, z);
			
			outputStream = new BufferedWriter(new FileWriter("dataout.txt"));
			
			outputStream.write(result[0]+","+result[1]+"\n");
			
		} 
		
		
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
