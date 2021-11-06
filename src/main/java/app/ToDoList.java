/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

  private List<ToDoItem> itemList = new ArrayList<>();

  public void add(ToDoItem item) {
    // add item to itemList
    itemList.add(item);
  }

  public void remove(ToDoItem item) {
    // remove item from itemList
    itemList.remove(item);
  }

  public void clear() {
    // set itemList to blank
    itemList.clear();
  }

  public List<ToDoItem> getItemList() {
    // return itemList
    return itemList;
  }

  public List<ToDoItem> showIncomplete() {
    // create return list
    List<ToDoItem> returnList = new ArrayList<>();
    // for each item
    for (ToDoItem item : itemList) {
      // if incomplete (complete = false)
      if (!item.isComplete()) {
        // add item to list
        returnList.add(item);
      }
    }
    // return list
    return returnList;
  }

  public List<ToDoItem> showComplete() {
    // create return list
    List<ToDoItem> returnList = new ArrayList<>();
    // for each item
    for (ToDoItem item : itemList) {
      // if complete (complete = true)
      if (item.isComplete()) {
        // add item to list
        returnList.add(item);
      }
    }
    // return list
    return returnList;
  }

  public void saveList(File outputFile) {
    String filePath = outputFile.getAbsolutePath();
    outputFile = new File(filePath + ".txt");
    // create outputFile in try with resources
    try (FileWriter save = new FileWriter(outputFile)) {
      // write "To-Do List\n"
      save.write("To-Do List\n");
      // for each item
      for (ToDoItem item : itemList) {
        // write "description + ", " + dueDate + ", " + complete
        save.write(
            item.getDescription() + ", " + item.getDueDate() + ", " + item.isComplete() + "\n");
      }
    }
    // catch error
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadList(File inputFile) {
    // open inputFile in try with resources
    // scan the line
    try (Scanner readFile = new Scanner(inputFile)) {
      // trim file to after "To-Do\n"
      readFile.nextLine();
      while (readFile.hasNextLine()) {
        // tokenize the input line
        String[] itemTokens = readFile.nextLine().split(", ");
        // create an item with token1 = description, token2 = dueDate, token3 (parsed as boolean) = complete
        ToDoItem item = new ToDoItem(itemTokens[0], itemTokens[1],
            Boolean.parseBoolean(itemTokens[2]));
        // add item to itemList
        itemList.add(item);
      }

      // catch error
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean equivalentTo(ToDoList list) {
    boolean returnValue = true;
    for (ToDoItem item : list.getItemList()) {
      int index = list.getItemList().indexOf(item);
      if (!item.equivalentTo(itemList.get(index))) {
        returnValue = false;
      }
    }
    return returnValue;
  }

}
