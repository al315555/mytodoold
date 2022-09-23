package com.example.application.component;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.application.model.PersonDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.LocalDateRenderer;

public class PersonGrid extends VerticalLayout {

	private static final Logger LOG = LoggerFactory.getLogger(PersonGrid.class);

	private List<PersonDTO> dataPersonList;
	private PersonForm dataInnerFormSelected; 
	private PersonDTO selectedPerson; 

	public PersonGrid() {
		setWidth("100%");
		generatedByDefaultData();

		final Grid<PersonDTO> data = new Grid<>();
		data.setWidth("89%");
		data.getEditor().setBinder(new Binder<>());
		data.setItems(dataPersonList);
		data.addColumn(PersonDTO::getId).setHeader("Id");
		data.addColumn(PersonDTO::getVorName).setHeader("Vorname");
		data.addColumn(PersonDTO::getName).setHeader("Name");
		data.addColumn(new LocalDateRenderer<>(PersonDTO::getVerbornDatum)).setHeader("VerbornDatum");

		VerticalLayout personForm = new VerticalLayout();
		dataInnerFormSelected = new PersonForm();
		personForm.add(dataInnerFormSelected);
		Dialog personPopup = new Dialog();
		personPopup.setHeaderTitle("Person data");
		personPopup.add(personForm);
		Button cancelButton = new Button("Close", clickAction -> {
			LOG.info("Person data hidden");
			LOG.debug("Person data hidden: ",
					dataPersonList.get(0).getName() + " -> " + dataPersonList.get(0).getSalary());
			data.getEditor().editItem(selectedPerson);
			personPopup.close();
		});
		personPopup.getFooter().add(cancelButton);
		cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

		data.addItemClickListener(clickAction -> {
			LOG.info("Person data shown");
			LOG.debug("Person data shown: ",
					dataPersonList.get(0).getName() + " -> " + dataPersonList.get(0).getSalary());
			selectedPerson = clickAction.getItem();
			dataInnerFormSelected.setData(selectedPerson);
			personPopup.open();
		});
		
		Button button = new Button("New Person", clickAction -> {
			LOG.info("New Person button clicked");
			selectedPerson = new PersonDTO(); 
			dataPersonList.add(selectedPerson);
			data.setItems(dataPersonList);
			dataInnerFormSelected.setData(selectedPerson); 
			data.getEditor().editItem(selectedPerson); 
			personPopup.open();
		});
		add(button, data);
	}

	private void generatedByDefaultData() {
		PersonDTO person = new PersonDTO();
		person.setName("sampleName1");
		person.setVorName("sampleVorName1");
		person.setSalary(12345.67);
		// TODO: set birthday
		dataPersonList = new ArrayList<>();
		dataPersonList.add(person);
	}
}
