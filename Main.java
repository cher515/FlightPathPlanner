import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("do 'java Main' <data.txt> file then <req.txt> then <output.txt>");
            return;
        }

        String dataF = args[0];
        String sumPathsF = args[1];
        String outputFile = args[2];

        fPlan fPlan = new fPlan();

        try (BufferedReader readFlFiles = new BufferedReader(new FileReader(dataF));
             BufferedReader pathReader = new BufferedReader(new FileReader(sumPathsF));
             PrintWriter writer = new PrintWriter(outputFile)) {

            int numFlights = Integer.parseInt(readFlFiles.readLine());
            for (int i = 0; i < numFlights; i++) {
                String[] parts = readFlFiles.readLine().split("\\|");
                fPlan.addFlight(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
            }

            int numPath = Integer.parseInt(pathReader.readLine());
            for (int i = 0; i < numPath; i++) {
                String[] parts = pathReader.readLine().split("\\|");
                writer.println("flight " + (i + 1) + ": " + parts[0] + " to " + parts[1] + " sorted by " + (parts[2].charAt(0) == 'T' ? "Time" : "Cost"));
                fPlan.fLocate(parts[0], parts[1], parts[2].charAt(0), writer);
            }
            
            System.out.println("TESTING");
            System.out.println("IT WORKS " + outputFile);
            
        } catch (IOException e) {
            System.err.println("Error : " + e.getMessage());
        }
    }
}
