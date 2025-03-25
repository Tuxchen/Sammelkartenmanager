import java.util.ArrayList;

public class Favoriten {
	
	private static ArrayList<Pokemon>favoriten=new ArrayList<>();
	
	public static void addToFavorite(Pokemon pokemon) {
		favoriten.add(pokemon);
	}
	public static void getFavoriten() {
		for (Pokemon p : favoriten) {
            System.out.println(p.getName());
	}
}
}
