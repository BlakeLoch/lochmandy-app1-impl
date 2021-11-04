package app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ToDoListTest {

  private String readFile(String file) {
    // read file as a string
    String data = "";
    try {
      data = Files.readString(Path.of(file));
    } catch (IOException e) {
      e.printStackTrace();
    }
    // return the string
    return data;
  }

  @Test
  void add() {
    // create ToDoList object
    ToDoList list = new ToDoList();
    // create ToDoItem
    ToDoItem item = new ToDoItem("D");

    // expected = create list of ToDoItems
    List<ToDoItem> expected = new ArrayList<>();
    // add ToDoItem to list
    expected.add(item);

    // ToDoList.add ToDoItems
    list.add(item);
    // actual = ToDoList.showAll
    List<ToDoItem> actual = list.showAll();
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void remove() {
    // create ToDoList object
    ToDoList list = new ToDoList();
    // create ToDoItem
    ToDoItem item1 = new ToDoItem("D");
    ToDoItem item2 = new ToDoItem("D");

    // expected = create list of ToDoItems
    List<ToDoItem> expected = new ArrayList<>();
    // add ToDoItem to list
    expected.add(item1);
    // add ToDoItem to list
    expected.add(item2);
    // remove ToDoItem from list
    expected.remove(item2);

    // ToDoList.add ToDoItem
    list.add(item1);
    // ToDoList.add ToDoItem
    list.add(item2);
    // ToDoList.remove ToDoItem
    list.remove(item2);
    // actual = ToDoList.showAll
    List<ToDoItem> actual = list.showAll();
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void clear() {
    // create ToDoList object
    ToDoList list = new ToDoList();
    // create ToDoItem
    ToDoItem item1 = new ToDoItem("D");
    ToDoItem item2 = new ToDoItem("D");
    ToDoItem item3 = new ToDoItem("D");

    // expected = create list of ToDoItems
    List<ToDoItem> expected = new ArrayList<>();

    // ToDoList.add ToDoItem
    list.add(item1);
    // ToDoList.add ToDoItem
    list.add(item2);
    // ToDoList.add ToDoItem
    list.add(item3);
    // ToDoList.clear ToDoItem
    list.clear();
    // actual = ToDoList.showAll
    List<ToDoItem> actual = list.showAll();
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void showAll() {
    // create ToDoList object
    ToDoList list = new ToDoList();
    // create ToDoItem
    ToDoItem item1 = new ToDoItem("D");
    ToDoItem item2 = new ToDoItem("D");
    ToDoItem item3 = new ToDoItem("D");

    // expected = create list of ToDoItems
    List<ToDoItem> expected = new ArrayList<>();
    // add ToDoItem to list
    expected.add(item1);
    // add ToDoItem to list
    expected.add(item2);
    // add ToDoItem to list
    expected.add(item3);

    // ToDoList.add ToDoItem
    list.add(item1);
    // ToDoList.add ToDoItem
    list.add(item2);
    // ToDoList.add ToDoItem
    list.add(item3);

    // actual = ToDoList.showAll
    List<ToDoItem> actual = list.showAll();

    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void showIncomplete() {
    // create ToDoList object
    ToDoList list = new ToDoList();
    // create ToDoItem
    ToDoItem item1 = new ToDoItem("D", "", false);
    ToDoItem item2 = new ToDoItem("D", "", true);
    ToDoItem item3 = new ToDoItem("D", "", false);
    ToDoItem item4 = new ToDoItem("D", "", true);

    // expected = create list of toDoItems
    List<ToDoItem> expected = new ArrayList<>();
    // add toDoItems
    expected.add(item1);
    expected.add(item3);

    // ToDoList.add toDoItems
    list.add(item1);
    list.add(item2);
    list.add(item3);
    list.add(item4);

    // actual = ToDoList.showIncomplete
    List<ToDoItem> actual = list.showIncomplete();

    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void showComplete() {
    // create ToDoList object
    ToDoList list = new ToDoList();
    // create ToDoItem
    ToDoItem item1 = new ToDoItem("D", "", false);
    ToDoItem item2 = new ToDoItem("D", "", true);
    ToDoItem item3 = new ToDoItem("D", "", false);
    ToDoItem item4 = new ToDoItem("D", "", true);

    // expected = create list of toDoItems
    List<ToDoItem> expected = new ArrayList<>();
    // add toDoItems
    expected.add(item2);
    expected.add(item4);

    // ToDoList.add toDoItems
    list.add(item1);
    list.add(item2);
    list.add(item3);
    list.add(item4);

    // actual = ToDoList.showComplete
    List<ToDoItem> actual = list.showComplete();

    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void saveList() {
    // create ToDoList object
    ToDoList list = new ToDoList();
    // create toDoItems
    ToDoItem item1 = new ToDoItem("Description", "2000-01-01", false);
    ToDoItem item2 = new ToDoItem("D", "2000-01-01", true);
    ToDoItem item3 = new ToDoItem("Description", "", false);
    // select test file
    String testFile = "testFiles"+ File.separator+"saveListTest.txt";

    // expected = string block in the save format
    /*
    To-Do List
    Item: Description, 2000-01-01, false
    Item: D, 2000-01-01, true
    Item: Description, , false
     */
    String expected = """
        To-Do List
        Description, 2000-01-01, false
        D, 2000-01-01, true
        Description, , false
        """;

    // add toDoItems to toDoList
    list.add(item1);
    list.add(item2);
    list.add(item3);

    // toDoList.saveList
    list.saveList(testFile);
    // actual = read saveTest as string
    String actual = readFile(testFile);
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void loadList() {
    // create ToDoList object
    ToDoList list = new ToDoList();
    // create toDoItems
    ToDoItem item1 = new ToDoItem("Description", "2000-01-01", false);
    ToDoItem item2 = new ToDoItem("D", "2000-01-01", true);
    ToDoItem item3 = new ToDoItem("Description", "", false);
    // select test file
    String testFile = "testFiles"+ File.separator+"loadListTest.txt";

    // expected = create list of toDoItems
    List<ToDoItem> expected = new ArrayList<>();
    // add toDoItems to list
    expected.add(item1);
    expected.add(item2);
    expected.add(item3);

    // ToDoList.loadList
    list.loadList(testFile);
    // actual = ToDoList.showAll
    List<ToDoItem> actual = list.showAll();

    // check if actual = expected

    //Item 1
    assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
    assertEquals(expected.get(0).getDueDate(), actual.get(0).getDueDate());
    assertEquals(expected.get(0).isComplete(), actual.get(0).isComplete());
    //Item 2
    assertEquals(expected.get(1).getDescription(), actual.get(1).getDescription());
    assertEquals(expected.get(1).getDueDate(), actual.get(1).getDueDate());
    assertEquals(expected.get(1).isComplete(), actual.get(1).isComplete());
    //Item 3
    assertEquals(expected.get(2).getDescription(), actual.get(2).getDescription());
    assertEquals(expected.get(2).getDueDate(), actual.get(2).getDueDate());
    assertEquals(expected.get(2).isComplete(), actual.get(2).isComplete());
  }


}