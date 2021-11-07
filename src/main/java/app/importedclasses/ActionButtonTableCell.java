/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Blake Lochmandy
 */

package app.importedclasses;

import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

// Imported class from https://stackoverflow.com/questions/29489366/how-to-add-button-in-javafx-table-view (Answer by Darrel K.)
// with minor tweaks by SonarLint

public class ActionButtonTableCell<S> extends TableCell<S, Button> {

  private final Button actionButton;

  public ActionButtonTableCell(String label, Function<S, S> function) {
    this.getStyleClass().add("action-button-table-cell");

    this.actionButton = new Button(label);
    this.actionButton.setOnAction((ActionEvent e) -> function.apply(getCurrentItem()));
    this.actionButton.setMaxWidth(Double.MAX_VALUE);
  }

  public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(
      String label, Function<S, S> function) {
    return param -> new ActionButtonTableCell<>(label, function);
  }

  public S getCurrentItem() {
    return getTableView().getItems().get(getIndex());
  }

  @Override
  public void updateItem(Button item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      setGraphic(null);
    } else {
      setGraphic(actionButton);
    }
  }
}
