
import java.io.*; 

public class LeeMatrizDataObj {

  static double[][] data;

  public static void main(String[] args) {
    if (args.length > 0) {
      try { 
	ObjectInputStream in = 
	    new ObjectInputStream(new FileInputStream(args[0])); 
	data = (double[][]) in.readObject(); 
	int row = data.length; 
	int col = data[0].length; 
	for (int i = 0; i < row; i++) {
	  for (int j = 0; j < col; j++) {
	    System.out.println("data[" + i + "][" + j + "] = " + data[i][j]);  
	  }
	}	
      } catch (Exception e) {} 
    }
  }
}
