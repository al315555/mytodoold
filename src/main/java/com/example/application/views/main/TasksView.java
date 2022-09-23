package com.example.application.views.main;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("tasks")
public class TasksView extends VaadinSampleAbstractView {

	public TasksView() {
		VerticalLayout mainFrame = new VerticalLayout();
		VerticalLayout todosList = new VerticalLayout();
		TextField taskField = new TextField();
		Button addButton = new Button("Add");
		Button helloButton = new Button("Greetings");
		helloButton.addClickListener(clickEvent -> {
			Notification.show("Hi!, you clicked the button " + clickEvent.getSource().getText());
		});
		addButton.addClickListener(click -> {
			Checkbox checkbox = new Checkbox(taskField.getValue());
			todosList.add(checkbox);
		});
		addButton.addClickShortcut(Key.ENTER);
		mainFrame.add(new H3("Tasks"), todosList, new HorizontalLayout(taskField, addButton));
		setContent(mainFrame);
		setSelectedTab(1);
	}
}
