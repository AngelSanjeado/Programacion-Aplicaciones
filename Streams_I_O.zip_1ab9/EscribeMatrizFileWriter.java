
import java.io.*; 

public class EscribeMatrizFileWriter {

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
	PrintWriter out = 
	    new PrintWriter(new BufferedWriter(new FileWriter(args[0]))); 
	out.println(row); 
	out.println(col); 
	for (i = 0; i < row; i++) {
	  for (j = 0; j < col; j++) {
	    out.println(data[i][j]); 
	  }
	}
	out.close(); 
      } catch (IOException e) {}  
    }
  }
}
