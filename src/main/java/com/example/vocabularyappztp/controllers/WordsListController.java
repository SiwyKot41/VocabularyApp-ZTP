package com.example.vocabularyappztp.controllers;

import com.example.vocabularyappztp.VocabularyApplication;
import com.example.vocabularyappztp.model.Word;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class WordsListController {

    private ArrayList<Word> words;

    private ObservableList data;
    @FXML
    public Label actionStatus;
    public TableView wordsTable;
    public TableColumn polishColumn;
    public TableColumn englishColumn;
    public TableColumn learnStateColumn;
    private Stage stage;


    public void initialize(Stage stage) throws Exception {
        this.stage = stage;
        this.words = Utils.readWords();

        data = getInitialTableData();
        wordsTable.setItems(data);
        wordsTable.setEditable(true);


        englishColumn.setCellValueFactory(new PropertyValueFactory("englishWord"));
        englishColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        englishColumn.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) cellEditEvent ->
                ((Word) cellEditEvent.getTableView().getItems().get(
                cellEditEvent.getTablePosition().getRow())
        ).setEnglishWord((String) cellEditEvent.getNewValue()));

        polishColumn.setCellValueFactory(new PropertyValueFactory("polishWord"));
        polishColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        polishColumn.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) cellEditEvent ->
                ((Word) cellEditEvent.getTableView().getItems().get(
                cellEditEvent.getTablePosition().getRow())
        ).setPolishWord((String) cellEditEvent.getNewValue()));

        learnStateColumn.setCellValueFactory(new PropertyValueFactory("learnState"));
        learnStateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        learnStateColumn.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) cellEditEvent ->
                ((Word) cellEditEvent.getTableView().getItems().get(
                cellEditEvent.getTablePosition().getRow())
        ).setLearnState((String) cellEditEvent.getNewValue()));

        wordsTable.getColumns().setAll(englishColumn, polishColumn, learnStateColumn);
        wordsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        wordsTable.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            int ix = t1.intValue();

            if (ix == data.size()) {

                return; // invalid data
            }

            Word word = (Word) data.get(ix);
            actionStatus.setText(word.toString());
        });

//        wordsTable.getSelectionModel().;
//        Word word = (Word) wordsTable.getSelectionModel().getSelectedItem();
//        actionStatus.setText(word.toString());

    } 

    private ObservableList getInitialTableData() {
        return FXCollections.observableList(words);
    }

    public void onClickAdd(ActionEvent actionEvent) throws Exception {

        Word word = new Word("...", "...", "...");
        data.add(word);
        int row = data.size() - 1;

        wordsTable.requestFocus();
        wordsTable.getSelectionModel().select(row);
        wordsTable.getFocusModel().focus(row);

        actionStatus.setText("wpisz nowe slowo");

    }

    public void onClickDelete(ActionEvent actionEvent) {
        int ix = wordsTable.getSelectionModel().getSelectedIndex();
        Word word = (Word) wordsTable.getSelectionModel().getSelectedItem();
        data.remove(ix);
        actionStatus.setText("usunieto: " + word.toString());


        if (wordsTable.getItems().size() == 0) {

            actionStatus.setText("brak słówek!");
            return;
        }

        if (ix != 0) {

            ix = ix -1;
        }

        wordsTable.requestFocus();
        wordsTable.getSelectionModel().select(ix);
        wordsTable.getFocusModel().focus(ix);
    }

    public void onClickBackButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        MenuController menuController = fxmlLoader.<MenuController>getController();
        menuController.initialize(stage);

        stage.setScene(scene);
        stage.setHeight(436.0);
        stage.setWidth(655.0);
        stage.show();
    }

    public void onClickSave(ActionEvent actionEvent) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("./src/main/java/words.txt");

        for(int i = 0; i < wordsTable.getItems().size(); i++){
            wordsTable.getSelectionModel().select(i);
            Word word = (Word) wordsTable.getSelectionModel().getSelectedItem();
            writer.println(word.toString());
        }

        writer.close();
    }
}


