package com.example.application.views.main;

import com.example.application.component.PersonGrid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("persons")
public class PersonDataView extends VaadinSampleAbstractView {

	public PersonDataView() {
		VerticalLayout mainFrame = new VerticalLayout();
		// Person Data Grid
		VerticalLayout dataLayout = new VerticalLayout();
		dataLayout.add(new PersonGrid());
		dataLayout.setWidth("90%");
		dataLayout.setMargin(true);
		mainFrame.add(new H3("Person List"), dataLayout);
		setContent(mainFrame);
		setSelectedTab(0);
	}
}
