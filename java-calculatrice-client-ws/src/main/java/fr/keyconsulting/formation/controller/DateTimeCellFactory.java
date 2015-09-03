package fr.keyconsulting.formation.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

final class DateTimeCellFactory<T>
		implements Callback<TableColumn<T, LocalDateTime>, TableCell<T, LocalDateTime>> {
	
	@Override
	public TableCell<T, LocalDateTime> call(TableColumn<T, LocalDateTime> param) {
		return new TableCell<T, LocalDateTime>() {

			@Override
			protected void updateItem(LocalDateTime item, boolean empty) {
				super.updateItem(item, empty);
				if (!empty) {
					setText(item.format(DateTimeFormatter.ofPattern("d MMM uuuu HH:mm:ss")));
				} else {
					setText(null);
				}
			}
			
		};
	}
}