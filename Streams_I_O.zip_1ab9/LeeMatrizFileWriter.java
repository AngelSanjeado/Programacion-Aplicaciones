
import java.io.*; 

public class LeeMatrizFileWriter {

  static double[][] data;

  public static void main(String[] args) {
    if (args.length > 0) {
      try { 
	BufferedReader in = 
	    new BufferedReader(new FileReader(args[0])); 
	String line; 
	line = in.readLine();  
	int row = Integer.parseInt(line); 
	System.out.println("row = " + row); 
	line = in.readLine();  
	int col = Integer.parseInt(line); 
	System.out.println("col = " + col); 
	data = new double[row][col]; 
	for (int i = 0; i < row; i++) {
	  for (int j = 0; j < col; j++) {
	    line = in.readLine();  
	    data[i][j] = Double.valueOf(line).doubleValue(); 
	    System.out.println("data[" + i + "][" + j + "] = " + data[i][j]);  
	  }
	}	
      } catch (IOException e) {}  
    }
  }
}
