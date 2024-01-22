package be.kuleuven.dbproject;

import org.jdbi.v3.core.Jdbi;

public class DatabaseManager {
    private final Jdbi jdbi;

    public DatabaseManager(String jdbcUrl){
        this.jdbi = Jdbi.create(jdbcUrl);
    }

    public Jdbi getJdbi(){
        return jdbi;
    }
}
