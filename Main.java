

import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static Pokemon getPokemonData(String name) {
        try {
            // URL für die API
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + name);

            // Verbindung öffnen
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Statuscode prüfen
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Fehler: Unable to fetch data");
                return null;
            }

            // JSON-Daten lesen
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder jsonStr = new StringBuilder();
            while (scanner.hasNext()) {
                jsonStr.append(scanner.nextLine());
            }
            scanner.close();

            // JSON in JSONObject umwandeln
            JSONObject jsonObj = new JSONObject(jsonStr.toString());

            // Pokémon-Name und -Typ extrahieren
            String pokemonName = jsonObj.getString("name");
            String pokemonType = jsonObj.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name");


            // Rückgabe eines Pokémon-Objekts
            return new Pokemon(pokemonName, pokemonType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
