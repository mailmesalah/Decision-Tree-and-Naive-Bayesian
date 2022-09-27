

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NaiveBayesian {
	// Training Data set
	ArrayList<double[]> class1Training = new ArrayList<double[]>();
	ArrayList<double[]> class2Training = new ArrayList<double[]>();
	ArrayList<double[]> class3Training = new ArrayList<double[]>();

	// Prior Probabilities
	double class1PriorP;
	double class2PriorP;
	double class3PriorP;

	// Evidence Probabilities
	double fEvidenceP[] = new double[13];

	// Likelihood Probabilities
	double class1LikeliP[] = new double[13];
	double class2LikeliP[] = new double[13];
	double class3LikeliP[] = new double[13];

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
					class1Training.add(row);

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
					class2Training.add(row);

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
					class3Training.add(row);
				}
			}
			in.close();
		} catch (IOException e) {
			System.out.println("File Read Error");
		}

	}

	public void setPriorProbabilities() {
		// Finding total quantity attribute values of each class
		double class1Q = 0;
		for (double d[] : class1Training) {
			class1Q = class1Q + d[0] + d[1] + d[2] + d[3] + d[4] + d[5] + d[6]
					+ d[7] + d[8] + d[9] + d[10] + d[11] + d[12];
		}

		double class2Q = 0;
		for (double d[] : class2Training) {
			class2Q = class2Q + d[0] + d[1] + d[2] + d[3] + d[4] + d[5] + d[6]
					+ d[7] + d[8] + d[9] + d[10] + d[11] + d[12];
		}

		double class3Q = 0;
		for (double d[] : class3Training) {
			class3Q = class3Q + d[0] + d[1] + d[2] + d[3] + d[4] + d[5] + d[6]
					+ d[7] + d[8] + d[9] + d[10] + d[11] + d[12];
		}

		double totalQ = class1Q + class2Q + class3Q;
		class1PriorP = class1Q / totalQ;
		class2PriorP = class2Q / totalQ;
		class3PriorP = class3Q / totalQ;

	}

	public void setEvidenceProbabilities() {
		double fieldQ[] = new double[13];
		double totalQ = 0;
		for (double d[] : class1Training) {
			for (int i = 0; i < d.length; i++) {
				fieldQ[i] = fieldQ[i] + d[i];
				totalQ = totalQ + d[i];
			}
		}
		for (double d[] : class2Training) {
			for (int i = 0; i < d.length; i++) {
				fieldQ[i] = fieldQ[i] + d[i];
				totalQ = totalQ + d[i];
			}
		}
		for (double d[] : class3Training) {
			for (int i = 0; i < d.length; i++) {
				fieldQ[i] = fieldQ[i] + d[i];
				totalQ = totalQ + d[i];
			}
		}

		// Finding Evidence Probabilities
		for (int i = 0; i < fieldQ.length; i++) {
			fEvidenceP[i] = fieldQ[i] / totalQ;
		}

	}

	public void setLikelihoodProbabilitlies() {
		// Finding total quantity attribute values of each class
		double class1Q = 0;
		for (double d[] : class1Training) {
			class1Q = class1Q + d[0] + d[1] + d[2] + d[3] + d[4] + d[5] + d[6]
					+ d[7] + d[8] + d[9] + d[10] + d[11] + d[12];
		}

		double class2Q = 0;
		for (double d[] : class2Training) {
			class2Q = class2Q + d[0] + d[1] + d[2] + d[3] + d[4] + d[5] + d[6]
					+ d[7] + d[8] + d[9] + d[10] + d[11] + d[12];
		}

		double class3Q = 0;
		for (double d[] : class3Training) {
			class3Q = class3Q + d[0] + d[1] + d[2] + d[3] + d[4] + d[5] + d[6]
					+ d[7] + d[8] + d[9] + d[10] + d[11] + d[12];
		}

		double class1FieldQ[] = new double[13];
		double class2FieldQ[] = new double[13];
		double class3FieldQ[] = new double[13];
		for (double d[] : class1Training) {
			for (int i = 0; i < d.length; i++) {
				class1FieldQ[i] = class1FieldQ[i] + d[i];
			}
		}
		for (double d[] : class2Training) {
			for (int i = 0; i < d.length; i++) {
				class2FieldQ[i] = class2FieldQ[i] + d[i];
			}
		}
		for (double d[] : class3Training) {
			for (int i = 0; i < d.length; i++) {
				class3FieldQ[i] = class3FieldQ[i] + d[i];
			}
		}

		// Finding Likelihood of each class and its attributes(fields)
		for (int i = 0; i < class1LikeliP.length; i++) {
			class1LikeliP[i] = class1FieldQ[i] / class1Q;
		}

		for (int i = 0; i < class2LikeliP.length; i++) {
			class2LikeliP[i] = class2FieldQ[i] / class2Q;
		}

		for (int i = 0; i < class3LikeliP.length; i++) {
			class3LikeliP[i] = class3FieldQ[i] / class3Q;
		}
	}

	public double getErrorRate() {
		double errorCount = 0;
		double totalCount = 0;
		for (double d[] : dataEvaluation) {
			double class1Rate = (d[1] * class1LikeliP[0])
					* (d[2] * class1LikeliP[1]) * (d[3] * class1LikeliP[2])
					* (d[4] * class1LikeliP[3]) * (d[5] * class1LikeliP[4])
					* (d[6] * class1LikeliP[5]) * (d[7] * class1LikeliP[6])
					* (d[8] * class1LikeliP[7]) * (d[9] * class1LikeliP[8])
					* (d[10] * class1LikeliP[9]) * (d[11] * class1LikeliP[10])
					* (d[12] * class1LikeliP[11]) * (d[13] * class1LikeliP[12]);
			double class2Rate = (d[1] * class2LikeliP[0])
					* (d[2] * class2LikeliP[1]) * (d[3] * class2LikeliP[2])
					* (d[4] * class2LikeliP[3]) * (d[5] * class2LikeliP[4])
					* (d[6] * class2LikeliP[5]) * (d[7] * class2LikeliP[6])
					* (d[8] * class2LikeliP[7]) * (d[9] * class2LikeliP[8])
					* (d[10] * class2LikeliP[9]) * (d[11] * class2LikeliP[10])
					* (d[12] * class2LikeliP[11]) * (d[13] * class2LikeliP[12]);
			double class3Rate = (d[1] * class3LikeliP[0])
					* (d[2] * class3LikeliP[1]) * (d[3] * class3LikeliP[2])
					* (d[4] * class3LikeliP[3]) * (d[5] * class3LikeliP[4])
					* (d[6] * class3LikeliP[5]) * (d[7] * class3LikeliP[6])
					* (d[8] * class3LikeliP[7]) * (d[9] * class3LikeliP[8])
					* (d[10] * class3LikeliP[9]) * (d[11] * class3LikeliP[10])
					* (d[12] * class3LikeliP[11]) * (d[13] * class3LikeliP[12]);
			int classR = getClass(class1Rate, class2Rate, class3Rate);
			int EClass = (int) d[0];

			if (classR == 1) {
				if (EClass != 1) {
					errorCount++;
				}
			} else if (classR == 2) {
				if (EClass == 3) {
					errorCount++;
				}
			} else {
				if (EClass == 3) {
					errorCount++;
				}
			}
			totalCount++;
		}
		return errorCount/totalCount;
	}

	private int getClass(double class1, double class2, double class3) {
		if (class1 > class2) {
			if (class1 > class3) {
				return 1;
			} else {
				return 3;
			}
		} else if (class2 > class3) {
			return 2;
		} else {
			return 3;
		}
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

	public static void main(String arg[]) {
		if (arg.length >= 2) {
			NaiveBayesian nb = new NaiveBayesian();
			nb.loadTrainingDataset(arg[0]);
			nb.setPriorProbabilities();
			nb.setEvidenceProbabilities();
			nb.setLikelihoodProbabilitlies();
			nb.loadEvaluationDataset(arg[1]);
			System.out.println("Error Rate is = "+ nb.getErrorRate());
		}

	}
}
