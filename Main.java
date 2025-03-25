import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static Pokemon getCardData(String name) {
        try {
            // URL für die API
            URL url = new URL("https://api.pokemontcg.io/v2/cards?q=name:" + name);

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

            // Wir gehen davon aus, dass der Name immer existiert, wenn das Pokémon gefunden wird
            String pokemonName = jsonObj.getJSONArray("data")
                                        .getJSONObject(0)
                                        .getString("name");

            // Set, Seltenheit und Preis extrahieren
            String pokemonSet = jsonObj.getJSONArray("data")
                                       .getJSONObject(0)
                                       .getJSONObject("set")
                                       .getString("name");

            String rarity = jsonObj.getJSONArray("data")
                                   .getJSONObject(0)
                                   .getString("rarity");

            // Preis (in USD) extrahieren (Preis ist nicht immer vorhanden, deshalb prüfen)
            String price = "Nicht verfügbar"; // Standardwert, falls Preis nicht vorhanden
            try {
                price = jsonObj.getJSONArray("data")
                               .getJSONObject(0)
                               .getJSONArray("card_prices")
                               .getJSONObject(0)
                               .getString("usd");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Bild-URL extrahieren
            String imageUrl = jsonObj.getJSONArray("data")
                                     .getJSONObject(0)
                                     .getJSONObject("images")
                                     .getString("small");

            // Rückgabe eines Pokémon-Objekts
            return new Pokemon(pokemonName, pokemonSet, rarity, price, imageUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}