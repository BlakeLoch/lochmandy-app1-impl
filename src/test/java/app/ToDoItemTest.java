/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToDoItemTest {

  @Test
  void getDescription() {
    // create ToDoItem with a constructor
    ToDoItem item = new ToDoItem("Description");
    // expected = description
    String expected = "Description";
    // actual = ToDoItem.getDescription
    String actual = item.getDescription();
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void getDueDate() {
    // create ToDoItem with a constructor
    ToDoItem item = new ToDoItem("Description", "2000-01-01");
    // expected = 2000-01-01
    String expected = "2000-01-01";
    // actual = ToDoItem.getDueDate
    String actual = item.getDueDate();
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void isCompleteFalse() {
    // create ToDoItem with a constructor
    ToDoItem item = new ToDoItem("Description", "2000-01-01", false);
    // actual = ToDoItem.isComplete
    boolean actual = item.isComplete();
    // check if actual is false
    assertFalse(actual);
  }

  @Test
  void isCompleteTrue() {
    // create ToDoItem with a constructor
    ToDoItem item = new ToDoItem("Description", "2000-01-01", true);
    // actual = ToDoItem.isComplete
    boolean actual = item.isComplete();
    // check if actual is true
    assertTrue(actual);
  }


  @Test
  void setDescription() {
    // create ToDoItem with a constructor
    ToDoItem item = new ToDoItem("Old Description");
    // expected = new description
    String expected = "New Description";
    // ToDoItem.setDescription
    item.setDescription("New Description");
    // actual = ToDoItem.getDescription
    String actual = item.getDescription();
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void setDueDate() {
    // create ToDoItem with a constructor
    ToDoItem item = new ToDoItem("Description", "2000-01-01");
    // expected = 2000-01-02
    String expected = "2000-01-02";
    // ToDoItem.setDueDate
    item.setDueDate("2000-01-02");
    // actual = ToDoItem.getDueDate
    String actual = item.getDueDate();
    // check if actual = expected
    assertEquals(expected, actual);
  }

  @Test
  void toggleCompleteFT() {
    // create ToDoItem with a constructor
    ToDoItem item = new ToDoItem("Description","",false);
    // ToDoItem.toggleComplete
    item.toggleComplete();
    // actual = get complete
    boolean actual = item.isComplete();
    // assert if actual is true
    assertTrue(actual);
  }

  @Test
  void toggleCompleteTF() {
    // create ToDoItem with a constructor
    ToDoItem item = new ToDoItem("Description","",true);
    // ToDoItem.toggleComplete
    item.toggleComplete();
    // actual = get complete
    boolean actual = item.isComplete();
    // assert if actual is false
    assertFalse(actual);
  }


  @Test
  void equivalentToTrue() {
    ToDoItem item1 = new ToDoItem("Description", "2000-01-01", false);
    ToDoItem item2 = new ToDoItem("Description", "2000-01-01", false);
    assertTrue(item1.equivalentTo(item2));
  }

  @Test
  void equivalentToFalse() {
    ToDoItem item1 = new ToDoItem("Description1", "2022-01-01", false);
    ToDoItem item2 = new ToDoItem("Description2", "2000-01-01", true);
    assertFalse(item1.equivalentTo(item2));
  }
}