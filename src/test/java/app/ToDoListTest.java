package app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ToDoListTest {

  private String readFile(File file) {
    // read file as a string
    String data = "";
    try {
      data = Files.readString(file.toPath());
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
    List<ToDoItem> actual = list.getItemList();
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
    List<ToDoItem> actual = list.getItemList();
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
    List<ToDoItem> actual = list.getItemList();
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void getItemList() {
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

    // actual = ToDoList.getItemList
    List<ToDoItem> actual = list.getItemList();

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
    File testFile = new File("testFiles"+ File.separator+"saveListTest.txt");

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
    // create toDoItems
    ToDoItem item1 = new ToDoItem("Description", "2000-01-01", false);
    ToDoItem item2 = new ToDoItem("D", "2000-01-01", true);
    ToDoItem item3 = new ToDoItem("Description", "", false);
    // select test file
    File testFile = new File("testFiles"+ File.separator+"loadListTest.txt");

    //  expected = create ToDoList object
    ToDoList expected = new ToDoList();
    // add toDoItems to list
    expected.add(item1);
    expected.add(item2);
    expected.add(item3);

    // actual = create ToDoList object
    ToDoList actual = new ToDoList();
    // actual.loadList
    actual.loadList(testFile);

    // check if actual = expected
    assertTrue(actual.equivalentTo(expected));
  }

}