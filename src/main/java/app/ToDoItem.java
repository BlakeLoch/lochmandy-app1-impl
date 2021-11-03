/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;

public class ToDoItem {

  private String description;
  private String dueDate;
  private boolean complete;
  private boolean delete;

  // Class constructor
  public ToDoItem(String description, String dueDate) {
    // set description to description
    // set dueDate to dueDate
    // set complete to false
    // set delete to false
  }

  public ToDoItem(String description) {
    // set description to description
    // set dueDate to ""
    // set complete to false
    // set delete to false
  }

  public String getDescription() {
    // return description
    return "";
  }

  public String getDueDate() {
    // return dueDate
    return "";
  }

  public boolean isComplete() {
    // return complete
    return false;
  }

  public boolean getDelete() {
    // return delete
    return false;
  }

  public void setDescription(String description) {
    // if description is between 1 and 256 characters
    // set description to description
    // else
    // throw an error
  }

  public void setDueDate(String dueDate) {
    // if dueDate is in format YYYY-MM-DD and a valid date
    // set dueDate to dueDate
    // else
    // throw an error
  }

  public void markAsComplete() {
    // set complete to true
  }

  public void markAsInComplete() {
    // set complete to false
  }

  public void delete() {
    // set delete to true
  }

  public void removeDelete() {
    // set delete to false
  }


}
