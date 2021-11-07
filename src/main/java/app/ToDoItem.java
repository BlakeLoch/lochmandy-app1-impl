/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app;


public class ToDoItem {

  private String description;
  private String dueDate;
  private boolean complete;

  // Class constructors
  public ToDoItem(String description, String dueDate, boolean complete) {
    // set description to description
    this.description = description;
    // set dueDate to dueDate
    this.dueDate = dueDate;
    // set complete to false
    this.complete = complete;
  }

  public ToDoItem(String description, String dueDate) {
    // set description to description
    this.description = description;
    // set dueDate to dueDate
    this.dueDate = dueDate;
    // set complete to false
    complete = false;
  }

  public ToDoItem(String description) {
    // set description to description
    this.description = description;
    // set dueDate to ""
    dueDate = "";
    // set complete to false
    complete = false;
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
  }

  public String getDueDate() {
    // return dueDate
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    // if dueDate is in format YYYY-MM-DD and a valid date
    if (dueDate.matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$") || dueDate.equals(
        "")) {
      // set dueDate to dueDate
      this.dueDate = dueDate;
    }
  }

  public boolean isComplete() {
    // return complete
    return complete;
  }

  public void toggleComplete() {
    // set complete to opposite value
    complete = !complete;
  }

  public boolean equivalentTo(ToDoItem item) {
    return item.getDescription().equals(this.description) && item.getDueDate().equals(this.dueDate)
        && item.isComplete() == this.complete;
  }

}
