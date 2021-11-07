/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;


public class ToDoItem {

  private String description;
  private String dueDate;
  private boolean complete;



  // Class constructor

  public ToDoItem(String description, String dueDate, boolean complete) {
    // set description to description
    this.description = description;
    // set dueDate to dueDate
    this.dueDate = dueDate;
    // set complete to false
    this.complete = complete;
    // set delete to false
  }

  public ToDoItem(String description, String dueDate) {
    // set description to description
    this.description = description;
    // set dueDate to dueDate
    this.dueDate = dueDate;
    // set complete to false
    complete = false;
    // set delete to false
  }

  public ToDoItem(String description) {
    // set description to description
    this.description = description;
    // set dueDate to ""
    dueDate = "";
    // set complete to false
    complete = false;
    // set delete to false
  }

  public String getDescription() {
    // return description
    return description;
  }

  public void setDescription(String description) {
    // if description is between 1 and 256 characters
    if (description.length() >= 1 && description.length() <= 256) {
      // set description to description
      this.description = description;
    }
    // else
    else {
      // throw an error
    }
  }

  public String getDueDate() {
    // return dueDate
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    // if dueDate is in format YYYY-MM-DD and a valid date
    if (/*valid date in format*/ true) {
      // set dueDate to dueDate
      this.dueDate = dueDate;
    }
    // else
    else {
      // throw an error
    }
  }

  public boolean isComplete() {
    // return complete
    return complete;
  }

  public void markAsComplete() {
    // set complete to true
    complete = true;
  }

  public void markAsIncomplete() {
    // set complete to false
    complete = false;
  }

  public boolean equivalentTo(ToDoItem item) {
    return item.getDescription().equals(this.description) && item.getDueDate().equals(this.dueDate) && item.isComplete() == this.complete;
  }

  public void toggleComplete() {
    if (complete) {
      markAsIncomplete();
    } else {
      markAsComplete();
    }
  }
}
