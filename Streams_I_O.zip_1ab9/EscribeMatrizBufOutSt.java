
import java.io.*; 

public class EscribeMatrizBufOutSt {

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
	DataOutputStream out = 
	    new DataOutputStream(new BufferedOutputStream(new FileOutputStream(args[0]))); 
	out.writeInt(row); 
	out.writeInt(col); 
	for (i = 0; i < row; i++) {
	  for (j = 0; j < col; j++) {
	    out.writeDouble(data[i][j]); 
	  }
	}
	out.close(); 
      } catch (IOException e) {}  
    }
  }
}
