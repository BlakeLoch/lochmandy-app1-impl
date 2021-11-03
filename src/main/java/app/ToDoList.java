/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

  private List<ToDoItem> itemList = new ArrayList<>();

  public void add(ToDoItem item) {
    // add item to itemList
  }

  public void remove(ToDoItem item) {
    // remove item from itemList
  }

  public void clear() {
    // set itemList to blank
  }

  public List<ToDoItem> showAll() {
    // return itemList
    return null;
  }

  public List<ToDoItem> showIncomplete() {
    // for each item
    // if incomplete (complete = false)
    // add item to list
    // return list
    return null;
  }

  public List<ToDoItem> showComplete() {
    // for each item
    // if complete (complete = true)
    // add item to list
    // return list
    return null;
  }

  public void saveList(String outputFile) {
    // create outputFile in try with resources
    // write "To-Do List\n"
    // for each item
    // write "description + ", " + dueDate + ", " + complete
    // catch error
  }

  public void loadList(String inputFile) {

    // open inputFile in try with resources
    // scan the line
    // trim file to after "To-Do\n"
    // tokenize the input line
    // create an item with token1 = description, token2 = dueDate, token3 (parsed as boolean) = complete
    // add item to itemList

    // catch error
  }


}
