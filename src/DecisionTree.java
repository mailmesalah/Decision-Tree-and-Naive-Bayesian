

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DecisionTree {
	// Raw Training Data set
	ArrayList<double[]> class1RawTraining = new ArrayList<double[]>();
	ArrayList<double[]> class2RawTraining = new ArrayList<double[]>();
	ArrayList<double[]> class3RawTraining = new ArrayList<double[]>();
	
	
	// Processed Training Data set
	ArrayList<int[]> trainingDataset = new ArrayList<int[]>();	
	
	//Fields for discretize Traning Dataset
	double[] maxValues = new double[13];
	double[] minValues = new double[13];
	int numberOfValues = 0;
	
	// Evaluation data set
	ArrayList<double[]> dataEvaluation = new ArrayList<double[]>();

	public void loadTrainingDataset(String arg) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(arg));
			String str;
			double row[] = new double[13];
			while ((str = in.readLine()) != null) {
				String[] ar = str.split(",");
				if (ar[0].trim().equals("1")) {
					row[0] = Double.parseDouble(ar[1]);
					row[1] = Double.parseDouble(ar[2]);
					row[2] = Double.parseDouble(ar[3]);
					row[3] = Double.parseDouble(ar[4]);
					row[4] = Double.parseDouble(ar[5]);
					row[5] = Double.parseDouble(ar[6]);
					row[6] = Double.parseDouble(ar[7]);
					row[7] = Double.parseDouble(ar[8]);
					row[8] = Double.parseDouble(ar[9]);
					row[9] = Double.parseDouble(ar[10]);
					row[10] = Double.parseDouble(ar[11]);
					row[11] = Double.parseDouble(ar[12]);
					row[12] = Double.parseDouble(ar[13]);
					class1RawTraining.add(row);

				} else if (ar[0].trim().equals("2")) {
					row[0] = Double.parseDouble(ar[1]);
					row[1] = Double.parseDouble(ar[2]);
					row[2] = Double.parseDouble(ar[3]);
					row[3] = Double.parseDouble(ar[4]);
					row[4] = Double.parseDouble(ar[5]);
					row[5] = Double.parseDouble(ar[6]);
					row[6] = Double.parseDouble(ar[7]);
					row[7] = Double.parseDouble(ar[8]);
					row[8] = Double.parseDouble(ar[9]);
					row[9] = Double.parseDouble(ar[10]);
					row[10] = Double.parseDouble(ar[11]);
					row[11] = Double.parseDouble(ar[12]);
					row[12] = Double.parseDouble(ar[13]);
					class2RawTraining.add(row);

				} else if (ar[0].trim().equals("3")) {
					row[0] = Double.parseDouble(ar[1]);
					row[1] = Double.parseDouble(ar[2]);
					row[2] = Double.parseDouble(ar[3]);
					row[3] = Double.parseDouble(ar[4]);
					row[4] = Double.parseDouble(ar[5]);
					row[5] = Double.parseDouble(ar[6]);
					row[6] = Double.parseDouble(ar[7]);
					row[7] = Double.parseDouble(ar[8]);
					row[8] = Double.parseDouble(ar[9]);
					row[9] = Double.parseDouble(ar[10]);
					row[10] = Double.parseDouble(ar[11]);
					row[11] = Double.parseDouble(ar[12]);
					row[12] = Double.parseDouble(ar[13]);
					class3RawTraining.add(row);
				}
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}

	}
	
	public void discretizeTrainingDataset(){
		for (double[] ds : class1RawTraining) {
			for(int i=0;i<13;i++){
				if(ds[i]>maxValues[i]){
					maxValues[i]=ds[i];
				}
				else if(ds[i]<minValues[i]){
					minValues[i]=ds[i];
				}
			}			
		}
		
		for (double[] ds : class2RawTraining) {
			for(int i=0;i<13;i++){
				if(ds[i]>maxValues[i]){
					maxValues[i]=ds[i];
				}
				else if(ds[i]<minValues[i]){
					minValues[i]=ds[i];
				}
			}
		}

		for (double[] ds : class3RawTraining) {
			for(int i=0;i<13;i++){
				if(ds[i]>maxValues[i]){
					maxValues[i]=ds[i];
				}
				else if(ds[i]<minValues[i]){
					minValues[i]=ds[i];
				}
			}
		}
		
		numberOfValues = class1RawTraining.size()+class2RawTraining.size()+class3RawTraining.size();
		
		//Continuous to Discrete Values
		for (double[] ds : class1RawTraining) {
			int row[] = new int[14];
			row[0]=1;
			for(int i=0;i<13;i++){
				row[i+1]=getDiscreteValue(i, ds[i]) ;
			}		
			trainingDataset.add(row);
		}
		
		for (double[] ds : class2RawTraining) {
			int row[] = new int[14];
			row[0]=2;
			for(int i=0;i<13;i++){
				row[i+1]=getDiscreteValue(i, ds[i]) ;
			}		
			trainingDataset.add(row);
		}

		for (double[] ds : class3RawTraining) {
			int row[] = new int[14];
			row[0]=3;
			for(int i=0;i<13;i++){
				row[i+1]=getDiscreteValue(i, ds[i]) ;				
			}		
			trainingDataset.add(row);
		}
		
	}
	
	private int getDiscreteValue(int index,double val){
		double discreteValue;
		
		discreteValue = (val - minValues[index]) / (maxValues[index] - minValues[index]);
		discreteValue *= numberOfValues;
				
		if (discreteValue > maxValues[index]) {			
			return (numberOfValues - 1);
		} else if (discreteValue < minValues[index]) {
			return 0;
		}

		return (int) discreteValue;		
	}
	
	public void saveTrainingDataset(String arg){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(arg));
			String str;
			for (int[] ds : trainingDataset) {
				str=ds[0]+","+ds[1]+","+ds[2]+","+ds[3]+","+ds[4]+","+ds[5]+","+ds[6]+","+ds[7]+","+ds[8]+","+ds[9]+","+ds[10]+","+ds[11]+","+ds[12]+","+ds[13];
				out.write(str);	
				out.newLine();
			}									
			
			out.close();
		} catch (IOException e) {
			System.out.println("File Write Error:"+e.getMessage());
		}

	}
	
	public void loadTree(){
		
	}
	
	public void loadEvaluationDataset(String arg) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(arg));
			String str;
			double row[] = new double[14];
			while ((str = in.readLine()) != null) {
				String[] ar = str.split(",");

				row[0] = Double.parseDouble(ar[1]);
				row[1] = Double.parseDouble(ar[2]);
				row[2] = Double.parseDouble(ar[3]);
				row[3] = Double.parseDouble(ar[4]);
				row[4] = Double.parseDouble(ar[5]);
				row[5] = Double.parseDouble(ar[6]);
				row[6] = Double.parseDouble(ar[7]);
				row[7] = Double.parseDouble(ar[8]);
				row[8] = Double.parseDouble(ar[9]);
				row[9] = Double.parseDouble(ar[10]);
				row[10] = Double.parseDouble(ar[11]);
				row[11] = Double.parseDouble(ar[12]);
				row[12] = Double.parseDouble(ar[13]);
				row[13] = Integer.parseInt(ar[0]);
				dataEvaluation.add(row);
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}

	}
	
	public void discretizeEvaluationDataset(){
		
	}
	
	public void classifyNEvaluate(){
		
	}
	
	public double getErrorRate(){
		return 0;
	}

	public static void main(String arg[]) {
		if (arg.length >= 3) {
			if(arg[0].trim().equals("-d")){
				DecisionTree dt = new DecisionTree();
				dt.loadTrainingDataset(arg[1]);
				dt.discretizeTrainingDataset();
				dt.saveTrainingDataset(arg[2]);
			}		
		}

	}
}
