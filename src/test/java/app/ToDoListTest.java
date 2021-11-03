package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToDoListTest {

  @Test
  void add() {
    // create ToDoList object

    // expected = create list of ToDoItems
    // add ToDoItem to list

    // ToDoList.add ToDoItems
    // actual = ToDoList.showAll

    // check if actual = expected
  }

  @Test
  void remove() {
    // create ToDoList object

    // expected = create list of ToDoItems
    // add ToDoItem to list
    // add ToDoItem to list
    // remove ToDoItem from list

    // ToDoList.add ToDoItem
    // ToDoList.add ToDoItem
    // ToDoList.remove ToDoItem
    // actual = ToDoList.showAll

    // check if actual = expected
  }

  @Test
  void clear() {
    // create ToDoList object

    // expected = create list of ToDoItems

    // ToDoList.add ToDoItem
    // ToDoList.add ToDoItem
    // ToDoList.add ToDoItem
    // ToDoList.clear ToDoItem
    // actual = ToDoList.showAll

    // check if actual = expected
  }

  @Test
  void showAll() {
    // create ToDoList object

    // expected = create list of toDoItems
    // actual = ToDoList.getToDoLists

    // check if actual = expected
  }

  @Test
  void showIncomplete() {
    // create ToDoList object

    // expected = create list of toDoItems
    // add toDoItems

    // ToDoList.add toDoItems
    // set some toDoItems to complete
    // actual = ToDoList.showIncomplete

    // check if actual = expected
  }

  @Test
  void showComplete() {
    // create ToDoList object

    // expected = create list of toDoItems
    // add toDoItems
    // set toDoItems to complete

    // ToDoList.add toDoItems
    // set some toDoItems to complete
    // actual = ToDoList.showComplete

    // check if actual = expected
  }

  @Test
  void saveList() {
    // create ToDoList object

    // expected = string block in the save format
    /*
    To-Do List
    Item: Description, 2000-01-01, false
    Item: D, 2000-01-01, true
    Item: Description, , false
     */
    // add toDoItems to toDoList
    // toDoList.saveList
    // actual = read saveTest as string

    // check if actual = expected
  }

  @Test
  void loadList() {
    // create ToDoList object

    // expected = create list of toDoItems
    // add toDoItems to list

    // ToDoList.readLists
    // actual = ToDoList.showAll

    // check if actual = expected
  }


}