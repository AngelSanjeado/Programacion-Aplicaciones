
import java.io.*; 

public class CopyCharacters {
  public static void main(String[] args) throws IOException {
    FileReader inputStream = null;
    FileWriter outputStream = null;
    try {
      inputStream = new FileReader("prueba.txt");
      outputStream = new FileWriter("characteroutput.txt");
      int c;
      while ((c = inputStream.read()) != -1) {
        outputStream.write(c);
      }
    } finally {
      if (inputStream != null) {inputStream.close(); }
      if (outputStream != null) {
        outputStream.close();
      }
    }
  }
}

