package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class NotificationView extends VaadinSampleAbstractView {

	public NotificationView() {
		VerticalLayout mainFrame = new VerticalLayout();
		Button helloButton = new Button("Greetings");
		helloButton.addClickListener(clickEvent -> {
			Notification.show("Hi!, you clicked the button " + clickEvent.getSource().getText());
		});
		mainFrame.add(helloButton);
		setContent(mainFrame);
		setSelectedTab(2);
	}
}
