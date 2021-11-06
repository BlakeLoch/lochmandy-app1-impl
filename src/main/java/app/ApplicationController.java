/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;

import app.importedClasses.ActionButtonTableCell;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

public class ApplicationController implements Initializable {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TableView<ToDoItem> toDoListTable;

  @FXML
  private Button addItem;

  @FXML
  private Button clearList;

  @FXML
  private Button saveList;

  @FXML
  private Button loadList;

  @FXML
  private TableColumn<ToDoItem, String> itemDescription;

  @FXML
  private TableColumn<ToDoItem, String> itemDueDate;

  @FXML
  private TableColumn<ToDoItem, Button> removeItem;

  @FXML
  private TableColumn<ToDoItem, Button> editItem;

  @FXML
  private TableColumn<ToDoItem, Boolean> completeItem;

  @FXML
  private TextField newItemDescription;

  @FXML
  private DatePicker newItemDueDate;

  @FXML
  private TextField saveFileName;

  @FXML
  private ChoiceBox<String> showStatusBox;



  //make a new ToDoList object called toDoList
  private ToDoList toDoList = new ToDoList();

  @FXML
  public void initialize(URL url, ResourceBundle rb) {

    newItemDueDate.setConverter(new StringConverter<LocalDate>()
    {
      private DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");

      @Override
      public String toString(LocalDate localDate)
      {
        if(localDate==null)
          return "";
        return dateTimeFormatter.format(localDate);
      }

      @Override
      public LocalDate fromString(String dateString)
      {
        if(dateString==null || dateString.trim().isEmpty())
        {
          return null;
        }
        return LocalDate.parse(dateString,dateTimeFormatter);
      }
    });

    toDoList.add(new ToDoItem("D", "1111-11-11", false));

    // bind ToDoListTable to toDoList
    itemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    itemDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    completeItem.setCellValueFactory(new PropertyValueFactory<>("complete"));
    completeItem.setCellFactory(column -> new CheckBoxTableCell<>());
    toDoListTable.getItems().setAll(toDoList.getItemList());



    // When removeItem is clicked
    removeItem.setCellFactory(ActionButtonTableCell.<ToDoItem>forTableColumn("X", (ToDoItem item) -> {
      // remove item from list
      toDoList.remove(item);
      // remove item from table
      toDoListTable.getItems().remove(item);
      return item;
    }));

    // Edit Item
    editItem.setCellFactory(ActionButtonTableCell.<ToDoItem>forTableColumn("E", (ToDoItem item) -> {

      return item;
    }));

  }

  // when addItem is clicked
  @FXML
  void addItemClicked(ActionEvent event) {

    // get description from newItemDescription
    String description = newItemDescription.getText();
    // get dueDate from newItemDueDate
    String dueDate = String.valueOf(newItemDueDate.getValue());
    // Set null values to blank
    dueDate = (dueDate.equals("null")) ? "" : dueDate;

    // clear fields
    newItemDescription.clear();
    newItemDueDate.setValue(null);
    // make new ToDoItem with description and dueDate

    if (description.length() >= 1) {
      if (description.length() > 256) {
        description = description.substring(0, 256);
      }
      ToDoItem item = new ToDoItem(description, dueDate);
      // add new ToDoItem to toDoList
      toDoList.add(item);
      // add new ToDoItem to table
      toDoListTable.getItems().add(item);
    }


  }

  // when clearList is clicked
  @FXML
  void clearListClicked(ActionEvent event) {
    // clear toDoList
    toDoList.clear();
    toDoListTable.getItems().clear();
    // remove item
  }


  // when saveList is clicked
  @FXML
  void saveListClicked(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save List");
    // open fileChooser
    File outputFile = fileChooser.showSaveDialog(saveList.getScene().getWindow());
    // toDoList.saveList(outputFile)
    toDoList.saveList(outputFile);
  }

  // when loadList is clicked
  @FXML
  void loadListClicked(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Load List");
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);
    // open fileChooser and choose inputFile
    File inputFile = fileChooser.showOpenDialog(loadList.getScene().getWindow());
    // toDoList.loadList(file)
    toDoList.loadList(inputFile);
    toDoListTable.getItems().setAll(toDoList.getItemList());

  }

  // when editItem is clicked
  // if mode = save
  // check if description is appropriate length
  // check if dueDate is appropriate format and valid date
  // update description and dueDate in item
  // replace textField and datePicker with description and dueDate strings
  // set button text to edit
  // mode = edit
  // if mode = edit (Default)
  // save item description and due date to local variables
  // replace description with textField
  // replace dueDate with datePicker
  // put saved description and dueDate in corresponding fields
  // set button text to save
  // mode = save


  // When completeItem is clicked
  // if item is incomplete
  // mark item as complete
  // else
  // mark item as incomplete

  @FXML
  void statusSelected(ActionEvent event) {
    // when showStatusBox = All
    // show all items on current to do list
    // populate toDoItems with (current to do list).showAll()

    // when showStatusBox = Incomplete
    // show all incomplete items on current to do list
    // populate toDoItems with (current to do list).showIncomplete()

    // when showStatusBox = Complete
    // show all complete items on current to do list
    // populate toDoItems with (current to do list).showComplete()
  }

}