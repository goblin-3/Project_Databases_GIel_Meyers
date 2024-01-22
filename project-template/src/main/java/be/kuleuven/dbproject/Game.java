package be.kuleuven.dbproject;
public class Game {
    private int game_id;
    private String gameName;
    private int releaseYear;
    private String genreName;
    private String language;
    private String publisherName;
    private String consoleName;
    private boolean isDigital;
    private double price;


    public Game(int game_id, String gameName, int releaseYear, String genreName, String language, String publisherName, String consoleName, boolean isDigital, double price) {
        this.game_id = game_id;
        this.gameName = gameName;
        this.releaseYear = releaseYear;
        this.genreName = genreName;
        this.language = language;
        this.publisherName = publisherName;
        this.consoleName = consoleName;
        this.isDigital = isDigital;
        this.price = price;
    }


    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void setConsoleName(String consoleName) {
        this.consoleName = consoleName;
    }

    public boolean getIsDigital() {
        return isDigital;
    }

    public void setIsDigital(boolean isDigital) {
        this.isDigital = isDigital;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

