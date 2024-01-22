package be.kuleuven.dbproject;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.util.NoSuchElementException;

public abstract class UserDaoImpl implements UserDao{
    private  Jdbi jdbi;

    @Override
    public boolean createGame(String name, String year, String publisher, String genre ,String console, String location, boolean isChecked, float price, String language) {
        try (Handle handle = jdbi.open()) {

            int genreId = getGenreIdByName(handle, genre);
            if (genreId == -1) {
                return false;
            }


            int yearId = getYearIdByName(handle, year);
            if (yearId == -1) {
                return false;
            }


            int publisherId = getPublisherIdByName(handle, publisher);
            if (publisherId == -1) {
                return false;
            }


            int consoleId = getConsoleIdByName(handle, console);
            if (consoleId == -1) {
                return false;
            }


          int gameId =  handle.createUpdate("INSERT INTO Game (Game_Name, Releaseyear_ID, Genre_ID, Language, Publisher_ID, Console_ID, Is_Digital, Price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")
                    .bind(0, name)
                    .bind(1, yearId)
                    .bind(2, genreId)
                    .bind(3, language)
                    .bind(4, publisherId)
                    .bind(5, consoleId)
                    .bind(6, isChecked ? 1 : 0)
                    .bind(7, price)
                    .executeAndReturnGeneratedKeys("Game_ID")
                    .mapTo(Integer.class)
                    .one();
            updateLocationWithGameId(handle, location, gameId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private int getGenreIdByName(Handle handle, String genreName) {
        try {
            return handle.createQuery("SELECT Genre_ID FROM Genre WHERE Genre_Name = :genreName")
                    .bind("genreName", genreName)
                    .mapTo(Integer.class)
                    .one();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    private int getYearIdByName(Handle handle, String yearName) {
        try {
            return handle.createQuery("SELECT Releaseyear_ID FROM Releaseyear WHERE Year = :yearName")
                    .bind("yearName", yearName)
                    .mapTo(Integer.class)
                    .one();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    private int getPublisherIdByName(Handle handle, String publisherName) {
        try {
            return handle.createQuery("SELECT Publisher_ID FROM Publisher WHERE Publisher_Name = :publisherName")
                    .bind("publisherName", publisherName)
                    .mapTo(Integer.class)
                    .one();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    private int getConsoleIdByName(Handle handle, String consoleName) {
        try {
            return handle.createQuery("SELECT Console_ID FROM Console WHERE Console_Name = :consoleName")
                    .bind("consoleName", consoleName)
                    .mapTo(Integer.class)
                    .one();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }


    private void updateLocationWithGameId(Handle handle, String location, int gameId) {

        int museumId = getMuseumIdByName(handle, location);
        if (museumId != -1) {
            handle.createUpdate("UPDATE Museum SET Game_ID = :gameId WHERE Museum_ID = :museumId")
                    .bind("gameId", gameId)
                    .bind("museumId", museumId)
                    .execute();
            return;
        }


        int warehouseId = getWarehouseIdByName(handle, location);
        if (warehouseId != -1) {
            handle.createUpdate("UPDATE Warehouse SET Game_ID = :gameId WHERE Warehouse_ID = :warehouseId")
                    .bind("gameId", gameId)
                    .bind("warehouseId", warehouseId)
                    .execute();
        }
    }

    private int getMuseumIdByName(Handle handle, String museumName) {
        try {
            return handle.createQuery("SELECT Museum_ID FROM Museum WHERE Name = :museumName")
                    .bind("museumName", museumName)
                    .mapTo(Integer.class)
                    .one();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

    private int getWarehouseIdByName(Handle handle, String warehouseName) {
        try {
            return handle.createQuery("SELECT Warehouse_ID FROM Warehouse WHERE Name = :warehouseName")
                    .bind("warehouseName", warehouseName)
                    .mapTo(Integer.class)
                    .one();
        } catch (NoSuchElementException e) {
            return -1;
        }
    }

}
