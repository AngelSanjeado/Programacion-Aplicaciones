
import java.io.*; 

public class LeeMatrizDataInp {

  static double[][] data;

  public static void main(String[] args) {
    if (args.length > 0) {
      try { 
	DataInputStream in = 
	    new DataInputStream(new FileInputStream(args[0])); 
	int row = in.readInt(); 
	System.out.println("row = " + row); 
	int col = in.readInt();
	System.out.println("col = " + col); 
	data = new double[row][col]; 
	for (int i = 0; i < row; i++) {
	  for (int j = 0; j < col; j++) {
	    data[i][j] = in.readDouble(); 
	    System.out.println("data[" + i + "][" + j + "] = " + data[i][j]);  
	  }
	}	
      } catch (IOException e) {}  
    }
  }
}
