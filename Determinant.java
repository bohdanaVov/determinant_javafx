package application;

public class Determinant {
	
	private double answer;

	public Determinant(double [][] matrix) {
		answer = berechne(matrix);
	}

	private double berechne(double[][] matrix2) {
		if(matrix2.length == 1) {
			return matrix2[0][0];
		} else if(matrix2.length == 2) {
			return matrix2[0][0] * matrix2[1][1] - matrix2[0][1] * matrix2[1][0];
		}
		double det=0;
		int factor=1;
		
		for(int i=0;i<matrix2.length;i++) {
			if (i%2!=0) {
				factor = -1;
			} else {
				factor =1;
			}
			det +=matrix2[i][0]*berechne(getMinormatrix(matrix2,i, 0))*factor;
		}
		return det;

	}

	private double[][] getMinormatrix(double[][] matrix2, int row, int column) {
		int l = matrix2.length;
		double [][] matrixMinor = new double[l-1][l-1];
		int r = 0;
        int c = 0;
		for (int i=0;i<matrixMinor.length;i++) {
			for (int j=0;j<matrixMinor.length;j++) {
	            c =j;
	            r =i;

	            
	            if (i >= row) {
	                r++; 
	            }
	            
	            if (j >= column) {
	                c++;
	            }
	            matrixMinor[i][j] = matrix2[r][c];
			}
		}
		return matrixMinor;
	}

	@Override
	public String toString() {
		return "Determinant " + answer;
	}

	public double getAnswer() {
		// TODO Auto-generated method stub
		return answer;
	}
	

	
}