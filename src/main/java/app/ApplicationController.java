/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ApplicationController implements Initializable {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TableView<?> ToDoListTable;

  @FXML
  private Button addItem;

  @FXML
  private Button clearList;

  @FXML
  private Button saveList;

  @FXML
  private Button loadList;

  @FXML
  private TableColumn<?, ?> itemDescription;

  @FXML
  private TableColumn<?, ?> removeItem;

  @FXML
  private TableColumn<?, ?> editItem;

  @FXML
  private TableColumn<?, ?> completeItem;

  @FXML
  private TextField newItemDescription;

  @FXML
  private DatePicker newItemDueDate;

  @FXML
  private TextField saveFileName;

  @FXML
  private ChoiceBox<?> showStatusBox;


  @FXML
  public void initialize(URL url, ResourceBundle rb) {

    //make a new ToDoList object called toDoList

    // sync ToDoListTable with toDoList

    // when addItem is clicked
    // get description from newItemDescription
    // get dueDate from newItemDueDate
    // make new ToDoItem with description and dueDate
    // add new ToDoItem to toDoList

    // when clearList is clicked
    // for each item in toDoList
    // remove item

    // when saveList is clicked
    // open fileChooser
    // get outputFileName from saveFileName and choose directory
    //  outputFile = "directory/outputFileName"
    // toDoList.saveList(outputFile)

    // when loadList is clicked
    // open fileChooser and choose inputFile
    // toDoList.loadList(file)

    // when removeItem is clicked
    // toDoList.remove() the corresponding item

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