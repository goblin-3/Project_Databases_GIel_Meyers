package be.kuleuven.dbproject;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper implements RowMapper<Game> {

    @Override
    public Game map(ResultSet rs, StatementContext ctx) throws SQLException {
        int game_id = rs.getInt("game_id");
        String gameName = rs.getString("Game_Name");
        int releaseYear = rs.getInt("Release_Year");
        String genreName = rs.getString("Genre_Name");
        String language = rs.getString("Language");
        String publisherName = rs.getString("Publisher_Name");
        String consoleName = rs.getString("Console_Name");
        boolean isDigital = rs.getBoolean("Is_Digital");
        double price = rs.getDouble("Price");

        return new Game(game_id, gameName, releaseYear, genreName, language, publisherName, consoleName, isDigital, price);
    }
}
