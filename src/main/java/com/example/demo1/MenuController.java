package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MenuController {
    @FXML
    private TableView<Book> menu;
    @FXML
    private TableColumn<Book, Integer> col_id;
    @FXML
    private TableColumn<Book, String> col_name;

    @FXML
    private TableColumn<Book, String> col_genre;

    @FXML
    private TableColumn<Book, String> col_author;

    @FXML
    private TableColumn<Book, Integer> col_quantity;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField quantityField;
    @FXML
    private Button addButton;
    @FXML
    private Button instructionButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField filterField;
    @FXML
    private Button searchButton;
    @FXML
    private Label wrongInput;

    public void instruction(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changescene("Instruction.fxml");
    }

    public final ObservableList<Book> list = FXCollections.observableArrayList();
    public ObservableList<Book> getBook(){
        try {
            FileReader fr = new FileReader("D:/data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                String[] txt = line.split(";");
                Integer id = Integer.parseInt(txt[0]);
                String name = txt[1];
                String author = txt[2];
                String genre = txt[3];
                int quantity = Integer.parseInt(txt[4]);
                list.add(new Book(id, name, author, genre, quantity));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void initialize() {
        initialize(null, null);
    }

    public void initialize(URL url, ResourceBundle rb){
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu.setItems(getBook());
        menu.setEditable(true);
        col_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_genre.setCellFactory(TextFieldTableCell.forTableColumn());
        col_author.setCellFactory(TextFieldTableCell.forTableColumn());
        col_quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        menu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void search(){
        String search = filterField.getText();
        String lowerCaseFilter = search.toLowerCase();
        List <Book> searchResult = list.stream().filter(boo -> boo.getName()
                        .toLowerCase(Locale.ROOT).contains(lowerCaseFilter))
                        .collect(Collectors.toList());
        ObservableList <Book> searchResultConverted = FXCollections.observableList(searchResult);
        menu.setItems(searchResultConverted);
    }

    public void addBook(){
        try{
            Book book1 = new Book(Integer.parseInt(idField.getText()), nameField.getText(),
                                genreField.getText(),authorField.getText(), Integer.parseInt(quantityField.getText()));
            menu.getItems().add(book1);
            FileWriter fw = new FileWriter("D:/data.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(book1.toString());
            bw.newLine();
            bw.close();
            fw.close();
        }catch (IllegalArgumentException | IOException e) {
            wrongInput.setText("Please check all fields");
        }
    }
    public void writeBook(){
        try{
            FileWriter fw = new FileWriter("D:/data.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for(Book boo : list ){
                bw.write(boo.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        }catch (Exception e){
        }
    }
    public void deleteObject(){
        ObservableList<Book> selectedRows, allBook;
        allBook = menu.getItems();
        selectedRows = menu.getSelectionModel().getSelectedItems();
        for (Book book : selectedRows){
            allBook.remove(book);
        }
        list.remove(selectedRows);
        writeBook();
    }

    public void changeName(TableColumn.CellEditEvent edittedCell){
        Book bookSelected = menu.getSelectionModel().getSelectedItem();
        bookSelected.setName(edittedCell.getNewValue().toString());
        writeBook();
    }

    public void changeGenre(TableColumn.CellEditEvent edittedCell){
        Book bookSelected = menu.getSelectionModel().getSelectedItem();
        bookSelected.setGenre(edittedCell.getNewValue().toString());
        writeBook();
    }

    public void changeAuthor(TableColumn.CellEditEvent edittedCell){
        Book bookSelected = menu.getSelectionModel().getSelectedItem();
        bookSelected.setAuthor(edittedCell.getNewValue().toString());
        writeBook();
    }

    public void changeID(TableColumn.CellEditEvent edittedCell){
        Book bookSelected = menu.getSelectionModel().getSelectedItem();
        bookSelected.setId((Integer)edittedCell.getNewValue());
        writeBook();
    }

    public void changeQuantity(TableColumn.CellEditEvent edittedCell){
        Book bookSelected = menu.getSelectionModel().getSelectedItem();
        bookSelected.setQuantity((Integer)edittedCell.getNewValue());
        writeBook();
    }
}
