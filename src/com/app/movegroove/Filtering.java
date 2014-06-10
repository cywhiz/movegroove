package com.app.movegroove;

import java.util.LinkedList;

public class Filtering {
	
	static final int buff_size=31;
	static final int filtered_buff_size=31;
	static double[] coeff1 = {
			0.002897496034410,
			-0.000567648193197,
			-0.003806516875773,
			0.006849434321440,
			-0.002371063078397,
			-0.009885625351304,
			0.017765045846347,
			-0.007058831696511,
			-0.018210616450869,
			0.032440142376186,
			-0.014214250947434,
			-0.024690264584640,
			0.045026110979048,
			-0.021244291043826,
			-0.026764466182371,
			0.047670689693782,
			-0.026764466182371,
			-0.021244291043826,
			0.045026110979048,
			-0.024690264584640,
			-0.014214250947434,
			0.032440142376186,
			-0.018210616450869,
			-0.007058831696511,
			0.017765045846347,
			-0.009885625351304,
			-0.002371063078397,
			0.006849434321440,
			-0.003806516875773,
			-0.000567648193197,
			0.002897496034410
	};
	static double[] coeff2 = {
			0.002896153145472,
			-0.001320243662263,
			0.001919466340843,
			-0.005228157104043,
			-0.003883580949238,
			-0.009828837310920,
			-0.016843602057877,
			0.000064142000811,
			-0.025837101872638,
			0.045613611701734,
			-0.014574436455205,
			0.128181097089149,
			0.016787671137371,
			0.213129124099116,
			0.044960250470563,
			0.247928886854250,
			0.044960250470563,
			0.213129124099116,
			0.016787671137371,
			0.128181097089149,
			-0.014574436455205,
			0.045613611701734,
			-0.025837101872638,
			0.000064142000811,
			-0.016843602057877,
			-0.009828837310920,
			-0.003883580949238,
			-0.005228157104043,
			0.001919466340843,
			-0.001320243662263,
			0.002896153145472
	};
	
	static private LinkedList<Double> shiftedMags = new LinkedList<Double>();
	static private LinkedList<Double> filteredWaveHigh = new LinkedList<Double>();
	static private LinkedList<Double> filteredWaveLow = new LinkedList<Double>();
	
	
	
	
	public static double[] compute(double x, double y, double z){
		
		double[] results = new double[2];
		
		double mag = Math.sqrt(x*x+y*y+z*z);
		double shiftedMag = mag - 10.89; //10.89 is a pre-computed mean.
		
		shiftedMags.addFirst(shiftedMag);
		if(shiftedMags.size()>buff_size) {
			shiftedMags.removeLast();
		}
		
		for (int i = 0; i<shiftedMags.size(); i++ ){
			results[0] += shiftedMags.get(i) * coeff1[i];
			results[1] += shiftedMags.get(i) * coeff2[i];
		}
		
		filteredWaveHigh.addFirst(results[0]);
		if(filteredWaveHigh.size()>buff_size) {
			filteredWaveHigh.removeLast();
		}
		
		filteredWaveLow.addFirst(results[1]);
		if(filteredWaveLow.size()>buff_size) {
			filteredWaveLow.removeLast();
		}
		
		results[0]=0;
		for (int i = 0; i<filteredWaveHigh.size(); i++ ){
			results[0]+=Math.abs(filteredWaveHigh.get(i));
		}
		
		results[1]=0;
		for (int i = 0; i<filteredWaveLow.size(); i++ ){
			results[1]+=Math.abs(filteredWaveLow.get(i));
		}
		
		results[0]=results[0]/filteredWaveHigh.size();
		results[1]=results[1]/filteredWaveLow.size();
		
		return results;
	}
}
