package be.kuleuven.dbproject.controller;

import be.kuleuven.dbproject.Game;
import be.kuleuven.dbproject.ProjectMain;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BeheerScherm3Controller {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClose;
    @FXML
    private TableView tblConfigs;
    @FXML
    private Button ADDUsr;
    @FXML
    private Button refresh;

    private final ProjectMainController projectMainController;



    public BeheerScherm3Controller(ProjectMainController projectMainController){
        this.projectMainController = projectMainController;
    }

    public void initialize() {
        initTable();
        btnAdd.setOnAction(e -> addNewRow());

        btnDelete.setOnAction(e -> {
            verifyOneRowSelected();
            deleteCurrentRow();
        });

        btnClose.setOnAction(e -> {
            var stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        });
        refresh.setOnAction(e-> {
            try {
                refreshScene();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        ADDUsr.setOnAction(e -> addUser());
    }

    private void initTable() {
        tblConfigs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tblConfigs.getColumns().clear();

        TableColumn<Game, String> nameCol = new TableColumn<>("Naam");
        nameCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getGameName()));

        TableColumn<Game, String> categoryCol = new TableColumn<>("releasejaar");
        categoryCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(String.valueOf(cellData.getValue().getReleaseYear())));

        TableColumn<Game, String> priceCol = new TableColumn<>("Prijs");
        priceCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(String.valueOf(cellData.getValue().getPrice())));

        TableColumn<Game, String> somethingElseCol = new TableColumn<>("publisher");
        somethingElseCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(String.valueOf(cellData.getValue().getPublisherName())));

        tblConfigs.getColumns().addAll(nameCol, categoryCol, priceCol, somethingElseCol);


        List<Game> games = ProjectMainController.userDao.getAllGames();
        tblConfigs.setItems(FXCollections.observableArrayList(games));
    }

    private void addNewRow() {
        projectMainController.showBeheerScherm("scherm1");
    }



    private void deleteCurrentRow() {
        Game selectedGame = (Game) tblConfigs.getSelectionModel().getSelectedItem();
        if (selectedGame != null){
            tblConfigs.getItems().remove(selectedGame);
            ProjectMainController.userDao.deleteGameById(selectedGame.getGame_id());

        }else {
            showAlert("Hela!", "Eerst een record selecteren he.");
        }

    }



    public void showAlert(String title, String content) {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void verifyOneRowSelected() {
        if(tblConfigs.getSelectionModel().getSelectedCells().size() == 0) {
            showAlert("Hela!", "Eerst een record selecteren he.");
        }
    }


    private void refreshScene() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("beheerscherm3.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);


        Stage stage = (Stage) btnClose.getScene().getWindow();


        stage.setScene(scene);


        stage.show();
    }
    private void addUser() {
        projectMainController.showBeheerScherm("scherm5");
    }

}
