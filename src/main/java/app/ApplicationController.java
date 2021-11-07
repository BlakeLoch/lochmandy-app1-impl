/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;

import app.importedclasses.ActionButtonTableCell;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

public class ApplicationController implements Initializable {

  // create strings for ALL INCOMPLETE and COMPLETE
  private static final String ALL = "All";
  private static final String INCOMPLETE = "Incomplete";
  private static final String COMPLETE = "Complete";
  //make a new ToDoList object called toDoList
  private final ToDoList toDoList = new ToDoList();

  @FXML
  private TableView<ToDoItem> toDoListTable;
  @FXML
  private Button editList;
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
  private TableColumn<ToDoItem, CheckBox> completeItem;
  @FXML
  private TextField newItemDescription;
  @FXML
  private DatePicker newItemDueDate;
  @FXML
  private ChoiceBox<String> showStatusBox;

  @FXML
  public void initialize(URL url, ResourceBundle rb) {

    // prevent table from being edited until editItem is clicked
    toDoListTable.setEditable(false);

    // Set newItemDueDate datePicker to YYYY-MM-DD
    newItemDueDate.setConverter(new StringConverter<>() {
      // create YYYY-MM-DD format
      private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      //override toString method
      @Override
      public String toString(LocalDate localDate) {
        // if localDate is null
        if (localDate == null) {
          // set to empty string
          return "";
        }
        // return formatted date
        return dateTimeFormatter.format(localDate);
      }

      //override fromString method
      @Override
      public LocalDate fromString(String dateString) {
        // if string is null or empty
        if (dateStringNullOrEmpty(dateString)) {
          //return null
          return null;
        }
        //return parsed dateString
        return LocalDate.parse(dateString, dateTimeFormatter);
      }
    });

    // bind ToDoListTable to toDoList
    // set itemDescription to item descriptions
    itemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
    // set itemDueDate to item due dates
    itemDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

    // make itemDescription editable
    itemDescription.setCellFactory(TextFieldTableCell.forTableColumn());
    // when itemDescription is edited
    itemDescription.setOnEditCommit(event -> {
      // get edited item
      ToDoItem row = event.getRowValue();
      // if new description is 1-256 characters

      // if newDescription is 1-256 characters set description to newDescription else set description to oldDescription
      String description =
          (description1to256Characters(event)) ? event.getNewValue() : event.getOldValue();
      //set item description
      row.setDescription(description);

      // refresh the table
      toDoListTable.refresh();
    });
    // make itemDueDate editable
    itemDueDate.setCellFactory(TextFieldTableCell.forTableColumn());
    // when itemDueDate is edited
    itemDueDate.setOnEditCommit(event -> {
      // get edited item
      ToDoItem row = event.getRowValue();
      // if new due date is in YYYY-MM-DD format
      // if newDueDate is in YYYY-MM-DD format or blank set dueDate to newDueDate else set dueDate to oldDueDate
      String dueDate = (dateInFormatOrBlank(event)) ? event.getNewValue() : event.getOldValue();
      //set item dueDate
      row.setDueDate(dueDate);

      // refresh the table
      toDoListTable.refresh();
    });

    // create completeItem column
    completeItem.setCellValueFactory(itemStatus -> {
      // get item for row
      ToDoItem item = itemStatus.getValue();
      // create a checkbox
      CheckBox checkBox = new CheckBox();
      // bind checkbox to item.isComplete()
      checkBox.selectedProperty().setValue(item.isComplete());
      // when checkbox is clicked
      checkBox.selectedProperty().addListener((ov, oldVal, newVal) -> {
        // toggle complete status
        item.toggleComplete();
        // get new status
        boolean newStatus = item.isComplete();

        // if list is showing incomplete items and item is marked as complete
        if (showingIncompleteAndMarkedAsComplete(showStatusBox, newStatus)) {
          // remove item from tableview
          toDoListTable.getItems().remove(item);
        }

        // if list is showing complete items and item is marked as incomplete
        if (showingCompleteAndMarkedAsIncomplete(showStatusBox, newStatus)) {
          // remove item from tableview
          toDoListTable.getItems().remove(item);
        }
      });
      // return checkbox
      return new SimpleObjectProperty<>(checkBox);
    });

    // When removeItem is clicked
    removeItem.setCellFactory(ActionButtonTableCell.forTableColumn("X", (ToDoItem item) -> {
      // remove item from list
      toDoList.remove(item);
      // remove item from table
      toDoListTable.getItems().remove(item);
      return item;
    }));

    // add all items to tableview
    toDoListTable.getItems().setAll(toDoList.getItemList());
  }

  // when addItem is clicked
  @FXML
  private void addItemClicked() {
    // get description from newItemDescription
    String description = newItemDescription.getText();
    // get dueDate from newItemDueDate
    String dueDate = String.valueOf(newItemDueDate.getValue());
    // Set null values to blank
    dueDate = (dueDate.equals("null")) ? "" : dueDate;

    // clear fields
    newItemDescription.clear();
    newItemDueDate.setValue(null);

    // if description length is at least 1
    if (description.length() >= 1) {
      // if description length is 256 characters or fewer
      if (description.length() >= 256) {
        // trim to 256 characters
        description = description.substring(0, 256);
      }
      // make new ToDoItem with description and dueDate
      ToDoItem item = new ToDoItem(description, dueDate);
      // add new ToDoItem to toDoList
      toDoList.add(item);

      // if table is not showing complete items
      if (!showStatusBox.getValue().equals(COMPLETE)) {
        // add new ToDoItem to table
        toDoListTable.getItems().add(item);
      }
    }
  }

  // when editList is clicked
  @FXML
  private void editListClicked() {

    // if button text is done
    if (editList.getText().equals("Done")) {
      // make table editable
      toDoListTable.setEditable(false);
      // change text to edit
      editList.setText("Edit");
    }
    // if button text is edit
    else {
      // make table editable
      toDoListTable.setEditable(true);
      // change text to done
      editList.setText("Done");
    }
  }

  // when clearList is clicked
  @FXML
  private void clearListClicked() {
    // clear toDoList
    toDoList.clear();
    toDoListTable.getItems().clear();
  }

  // when saveList is clicked
  @FXML
  private void saveListClicked() {
    // create a fileChooser
    FileChooser fileChooser = new FileChooser();
    // set title to "Save List"
    fileChooser.setTitle("Save List");
    // open fileChooser
    File outputFile = fileChooser.showSaveDialog(saveList.getScene().getWindow());
    // if file is selected
    if (outputFile != null) {
      // toDoList.saveList(outputFile)
      toDoList.saveList(outputFile);
    }
  }

  // when loadList is clicked
  @FXML
  private void loadListClicked() {
    // create a fileChooser
    FileChooser fileChooser = new FileChooser();
    // set title to "Load List"
    fileChooser.setTitle("Load List");
    // set to only take txt files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)",
        "*.txt");
    fileChooser.getExtensionFilters().add(extFilter);
    // open fileChooser and choose inputFile
    File inputFile = fileChooser.showOpenDialog(loadList.getScene().getWindow());

    // if file is selected
    if (inputFile != null) {
      // clear list and gui
      toDoList.clear();
      toDoListTable.getItems().clear();
      // add items to list
      toDoList.loadList(inputFile);

      // add items to gui
      // if showing incomplete items
      if (showStatusBox.getValue().equals(INCOMPLETE)) {
        // add incomplete items to tableView
        toDoListTable.getItems().setAll(toDoList.showIncomplete());
      }
      // if showing complete items
      else if (showStatusBox.getValue().equals(COMPLETE)) {
        // add complete items to tableView
        toDoListTable.getItems().setAll(toDoList.showComplete());
      }
      // if showing all items
      else {
        // add all items to tableView
        toDoListTable.getItems().setAll(toDoList.getItemList());
      }
    }
  }

  @FXML
  private void statusSelected() {
    // get value of showStatusBox
    String choice = showStatusBox.getValue();

    // clear list
    toDoListTable.getItems().clear();

    // when showStatusBox = All
    if (choice.equals(ALL)) {
      // populate toDoItems with toDoList.getItems()
      toDoListTable.getItems().setAll(toDoList.getItemList());
    }

    // when showStatusBox = Incomplete
    if (choice.equals(INCOMPLETE)) {
      // populate toDoItems with toDoList.showIncomplete()
      toDoListTable.getItems().setAll(toDoList.showIncomplete());
    }

    // when showStatusBox = Complete
    if (choice.equals(COMPLETE)) {
      // populate toDoItems with toDoList.showComplete()
      toDoListTable.getItems().setAll(toDoList.showComplete());
    }
  }

  private boolean dateStringNullOrEmpty(String dateString) {
    // return true if dateString is null or empty
    return (dateString == null || dateString.trim().isEmpty());
  }

  private boolean description1to256Characters(CellEditEvent<ToDoItem, String> event) {
    // return true if description is 1-256 characters
    return event.getNewValue().length() >= 1 && event.getNewValue().length() <= 256;
  }

  private boolean dateInFormatOrBlank(CellEditEvent<ToDoItem, String> event) {
    // return true if dueDate is in YYYY-MM-DD or blank
    return event.getNewValue().matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
        || event.getNewValue().equals("");
  }

  private boolean showingIncompleteAndMarkedAsComplete(ChoiceBox<String> showStatusBox,
      boolean newStatus) {
    // return true if showStatusBox is incomplete and the item is marked as complete
    return (showStatusBox.getValue().equals(INCOMPLETE) && newStatus);
  }

  private boolean showingCompleteAndMarkedAsIncomplete(ChoiceBox<String> showStatusBox,
      boolean newStatus) {
    // return true if showStatusBox is complete and the item is marked as incomplete
    return (showStatusBox.getValue().equals(COMPLETE) && !newStatus);
  }

}