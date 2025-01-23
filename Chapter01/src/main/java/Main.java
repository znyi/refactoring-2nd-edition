import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Plays plays = loadPlays("plays.json");
        ArrayList<Invoice> invoices = loadInvoices("invoices.json");
        Statement statement = new Statement();
        if (invoices != null){
            for (Invoice invoice : invoices){
                String result = null;
                try {
                    result = statement.statement(invoice, plays);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println(result);
            }
        }
    }
    public static Plays loadPlays(String filename) {
        // Create a Gson instance
        Gson gson = new Gson();

        try {
            // Get the InputStream from the classpath
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filename);

            // Check if the InputStream is null (file not found)
            if (inputStream == null) {
                System.out.println("File not found in the classpath!");
                return null;
            }

            // Create an InputStreamReader to read the InputStream
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Parse the JSON into a JsonObject
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            // Prepare a Plays to store the Play objects
            Plays plays = new Plays();

            // Loop through the JSON object keys (playID)
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                // Extract the key (playID)
                String playID = entry.getKey();

                // Get the associated play details (name, type)
                JsonObject playDetails = entry.getValue().getAsJsonObject();
                String name = playDetails.get("name").getAsString();
                String type = playDetails.get("type").getAsString();

                // Create a Play object and add it to the list
                Play play = new Play(playID, name, type);
                plays.addPlay(play);
            }

            // Print the list of Play objects
            /*
            for (HashMap.Entry<String, Play> entry : plays.getPlays().entrySet()) {
                System.out.println("Play ID: " + entry.getValue().getplayID());
                System.out.println("Name: " + entry.getValue().getName());
                System.out.println("Type: " + entry.getValue().getType());
                System.out.println();
            }
            */

            // Close the reader
            reader.close();

            // Return parsed data
            return plays;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<Invoice> loadInvoices(String filename) {
        // Create a Gson instance
        Gson gson = new Gson();

        try {
            // Get the InputStream from the classpath
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filename);

            // Check if the InputStream is null (file not found)
            if (inputStream == null) {
                System.out.println("File not found in the classpath!");
                return null;
            }

            // Create an InputStreamReader to read the InputStream
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Define the type for the list of Invoice objects
            ArrayList<Invoice> invoices = gson.fromJson(reader, new TypeToken<List<Invoice>>(){}.getType());

            // Loop through the invoices and print the details
            /*
            for (Invoice invoice : invoices) {
                System.out.println("Customer: " + invoice.getCustomer());
                for (Performance performance : invoice.getPerformances()) {
                    System.out.println("  playID: " + performance.getplayID());
                    System.out.println("  Audience: " + performance.getAudience());
                }
            }
            System.out.println();
            */

            // Close the reader
            reader.close();

            // Return parsed data
            return invoices;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
