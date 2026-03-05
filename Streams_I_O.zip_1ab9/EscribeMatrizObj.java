
import java.io.*; 

public class EscribeMatrizObj {

  static double[][] data = {
    { Math.exp(2.0), Math.exp(3.0), Math.exp(4.0) }, 
    { Math.exp(-2.0), Math.exp(-3.0), Math.exp(-4.0) }, 
  };
  
  public static void main(String[] args) {
    int row = data.length; 
    int col = data[0].length; 
    int i, j; 
    for (i = 0; i < row; i++) {
      for (j = 0; j < col; j++) {
	System.out.println("data[" + i + "][" + j + "] = " + data[i][j]);  
      }
    }
    
    if (args.length > 0) {
      try { 
	ObjectOutputStream out = 
	    new ObjectOutputStream(new FileOutputStream(args[0])); 
	out.writeObject(data); 
	out.close(); 
      } catch (IOException e) {}  
    }
  }
}
