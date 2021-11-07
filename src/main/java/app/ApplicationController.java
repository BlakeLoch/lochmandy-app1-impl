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
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;

public class ApplicationController implements Initializable {

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
  private Button editList;

  @FXML
  private TableColumn<ToDoItem, CheckBox> completeItem;

  @FXML
  private TextField newItemDescription;

  @FXML
  private DatePicker newItemDueDate;

  @FXML
  private ChoiceBox<String> showStatusBox;

  //make a new ToDoList object called toDoList
  private final ToDoList toDoList = new ToDoList();

  private  static final String ALL = "All";
  private static final String INCOMPLETE = "Incomplete";
  private static final String COMPLETE = "Complete";

  @FXML
  public void initialize(URL url, ResourceBundle rb) {

    toDoListTable.setEditable(false);

    newItemDueDate.setConverter(new StringConverter<>() {
      private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      @Override
      public String toString(LocalDate localDate) {
        if (localDate == null) {
          return "";
        }
        return dateTimeFormatter.format(localDate);
      }
      @Override
      public LocalDate fromString(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
          return null;
        }
        return LocalDate.parse(dateString, dateTimeFormatter);
      }
    });


    // bind ToDoListTable to toDoList
    itemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

    itemDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));



    itemDescription.setCellFactory(TextFieldTableCell.forTableColumn());
    itemDescription.setOnEditCommit(event -> {
      ToDoItem row = event.getRowValue();
      if (event.getNewValue().length() >= 1 && event.getNewValue().length() <= 256) {
        row.setDescription(event.getNewValue());
      } else {
        row.setDescription(event.getOldValue());
      }
      toDoListTable.refresh();
    });

    itemDueDate.setCellFactory(TextFieldTableCell.forTableColumn());
    itemDueDate.setOnEditCommit(event -> {
      ToDoItem row = event.getRowValue();
      if (event.getNewValue().matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$") || event.getNewValue().equals("")) {
        row.setDueDate(event.getNewValue());
      } else {
        row.setDueDate(event.getOldValue());
      }
      toDoListTable.refresh();
    });


    completeItem.setCellValueFactory(itemStatus -> {
      ToDoItem item = itemStatus.getValue();
      CheckBox checkBox = new CheckBox();
      checkBox.selectedProperty().setValue(item.isComplete());
      checkBox.selectedProperty().addListener((ov, oldVal, newVal) -> {
        item.toggleComplete();
        boolean newStatus = item.isComplete();

        if (showStatusBox.getValue().equals(INCOMPLETE) && newStatus) {
            toDoListTable.getItems().remove(item);
        }

        if (showStatusBox.getValue().equals(COMPLETE) && !newStatus) {
            toDoListTable.getItems().remove(item);
        }


      });
      return new SimpleObjectProperty<>(checkBox);
    });

    toDoListTable.getItems().setAll(toDoList.getItemList());


    // When removeItem is clicked
    removeItem.setCellFactory(ActionButtonTableCell.forTableColumn("X", (ToDoItem item) -> {
      // remove item from list
      toDoList.remove(item);
      // remove item from table
      toDoListTable.getItems().remove(item);
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
      if (!showStatusBox.getValue().equals(COMPLETE)) {
        toDoListTable.getItems().add(item);
      }
    }
  }

  // when editList is clicked
  @FXML
  void editListClicked(ActionEvent event) {

    // if button text is done
    if (editList.getText().equals("Done")) {
      // make table editable
      toDoListTable.setEditable(false);
      // change text to edit
      editList.setText("Edit");
    } else {
      // if button text is edit
        // make table editable
        toDoListTable.setEditable(true);
        // change text to done
        editList.setText("Done");
    }
  }

  // when clearList is clicked
  @FXML
  void clearListClicked(ActionEvent event) {
    // clear toDoList
    toDoList.clear();
    toDoListTable.getItems().clear();
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

    // clear list and gui
    toDoList.clear();
    toDoListTable.getItems().clear();

    // add items to list
    toDoList.loadList(inputFile);

    // add items to gui
    if (showStatusBox.getValue().equals(INCOMPLETE)) {
      toDoListTable.getItems().setAll(toDoList.showIncomplete());
    } else if (showStatusBox.getValue().equals(COMPLETE)) {
      toDoListTable.getItems().setAll(toDoList.showComplete());
    } else {
      toDoListTable.getItems().setAll(toDoList.getItemList());
    }

  }

  @FXML
  void statusSelected(ActionEvent event) {
    String choice = showStatusBox.getValue();

    // clear list
    toDoListTable.getItems().clear();

    // when showStatusBox = All
    if (choice.equals(ALL)) {
      // show all items on current to do list
      // populate toDoItems with (current to do list).getItems()
      toDoListTable.getItems().setAll(toDoList.getItemList());
    }

    // when showStatusBox = Incomplete
    if (choice.equals(INCOMPLETE)) {
      // populate toDoItems with (current to do list).showIncomplete()
      toDoListTable.getItems().setAll(toDoList.showIncomplete());
    }

    // when showStatusBox = Complete
    if (choice.equals(COMPLETE)) {
      // populate toDoItems with (current to do list).showComplete()
      toDoListTable.getItems().setAll(toDoList.showComplete());
    }
  }

}