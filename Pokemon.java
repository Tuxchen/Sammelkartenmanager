
public class Pokemon {
	
	 private String name;
	    private String set;
	    private String rarity;
	    private String price;
	    private String imageUrl;

	    // Constructor
	    public Pokemon(String name, String set, String rarity, String price, String imageUrl) {
	        this.name = name;
	        this.set = set;
	        this.rarity = rarity;
	        this.price = price;
	        this.imageUrl=imageUrl;
	    }

	    // Getter Method
	    public String getName() {
	        return name;
	    }

	    public String getSet() {
	        return set;
	    }

	    public String getRarity() {
	        return rarity;
	    }

	    public String getPrice() {
	        return price;
	    }
	    public String getImageUrl() {
	    	return imageUrl;
	    }

}
