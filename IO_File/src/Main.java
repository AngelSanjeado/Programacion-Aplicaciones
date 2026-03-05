import java.io.PrintWriter;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {

        String[][] dataSet = new String[1000][4];

        Random rand = new Random();

        for (int i = 0; i < dataSet.length; i++) {

            String nombre = "Nombre" + (i + 1);
            String apellido = "Apellido" + (i + 1);

            int rangoRandom = rand.nextInt(3);
            String rango = "";

            double salario = 0;

            if (rangoRandom == 0) {
                rango = "asistente";
                salario = 50000 + rand.nextDouble() * (80000 - 50000);
            }
            else if (rangoRandom == 1) {
                rango = "asociado";
                salario = 60000 + rand.nextDouble() * (110000 - 60000);
            }
            else {
                rango = "titular";
                salario = 75000 + rand.nextDouble() * (130000 - 75000);
            }

            dataSet[i][0] = nombre;
            dataSet[i][1] = apellido;
            dataSet[i][2] = rango;
            dataSet[i][3] = String.format("%.2f", salario);
        }

        PrintWriter writer = new PrintWriter("salarios.txt");

        for (String[] strings : dataSet) {

            writer.println(
                    strings[0] + " " +
                            strings[1] + " " +
                            strings[2] + " " +
                            strings[3]
            );
        }

        writer.close();
    }
}