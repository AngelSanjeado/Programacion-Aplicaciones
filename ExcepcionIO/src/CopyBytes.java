import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException{
        FileInputStream in = null;
        FileOutputStream out = null;

        try{
            in = new FileInputStream("prueba.txt");
            out = new FileOutputStream("byteprueba3.txt");

            int c;

            while((c = in.read()) != -1){
                out.write(c);
            }

        } catch (IOException e) {
            System.out.println("No fue posible instanciar el objeto");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            if (in != null){
                in.close();
            }

            if (out != null){
                out.close();
            }
        }
    }
}
