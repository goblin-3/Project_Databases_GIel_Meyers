package be.kuleuven.dbproject;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;


public interface UserDao {
    @SqlQuery("SELECT * FROM Customer WHERE Username = :username AND Password = :password")
    @RegisterRowMapper(UserMapper.class)
    User getUserByCredentials(@Bind("username") String username, @Bind("password") String password);

    @SqlUpdate("INSERT INTO Customer (Username, Password, Email, Last_Name, First_Name, Is_Employee, Is_Admin) " +
            "VALUES (:username, :password, :email, :lastName, :firstName, :isEmployee, :isAdmin)")
    void registerUser(@BindBean User user);

    @SqlQuery("SELECT g.Game_ID, g.Game_Name, r.Year AS Release_Year, gn.Genre_Name, g.Language, p.Publisher_Name, c.Console_Name, g.Is_Digital, g.Price " +
            "FROM Game g " +
            "JOIN Releaseyear r ON g.Releaseyear_ID = r.Releaseyear_ID " +
            "JOIN Genre gn ON g.Genre_ID = gn.Genre_ID " +
            "JOIN Publisher p ON g.Publisher_ID = p.Publisher_ID " +
            "JOIN Console c ON g.Console_ID = c.Console_ID")
    @RegisterRowMapper(GameMapper.class)
    List<Game> getAllGames();



    @SqlUpdate("DELETE FROM Game WHERE Game_ID = :gameId")
    void deleteGameById(@Bind("gameId") int gameId);




    @SqlQuery("SELECT Year FROM Releaseyear")
    List<String> getAllReleaseYears();

    @SqlQuery("SELECT Publisher_Name FROM Publisher")
    List<String> getAllPublishers();

    @SqlQuery("SELECT Genre_Name FROM Genre")
    List<String> getAllGenres();

    @SqlQuery("SELECT Console_Name FROM Console")
    List<String> getAllConsoles();
    @SqlQuery("SELECT DISTINCT Name FROM Museum")
    List<String> getAllMuseumNames();

    @SqlQuery("SELECT DISTINCT Name FROM Warehouse")
    List<String> getAllWarehouseNames();

    boolean createGame(String name, String year, String publisher, String genre ,String console, String location, boolean isChecked, float price, String language);
}
