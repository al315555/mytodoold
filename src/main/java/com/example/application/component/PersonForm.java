package com.example.application.component;

import static com.example.application.model.PersonDTO.ADRESSE;
import static com.example.application.model.PersonDTO.NAME;
import static com.example.application.model.PersonDTO.VERBORNDATUM;
import static com.example.application.model.PersonDTO.VORNAME;
import static com.example.application.model.PersonDTO.SALARY;
import static com.example.application.model.PersonDTO.AdresseDTO.INFO;
import static com.example.application.model.PersonDTO.AdresseDTO.LAND;
import static com.example.application.model.PersonDTO.AdresseDTO.NUMMER;
import static com.example.application.model.PersonDTO.AdresseDTO.POSTLEITZAHL;
import static com.example.application.model.PersonDTO.AdresseDTO.STADT;
import static com.example.application.model.PersonDTO.AdresseDTO.STRASSE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.application.converter.SalaryConverter;
import com.example.application.model.PersonDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.StringLengthValidator;

public class PersonForm extends FormLayout {

	private static final Logger LOG = LoggerFactory.getLogger(PersonForm.class);

	private Binder<PersonDTO> binder;
	private PersonDTO personData;

	private TextField nameField;
	private TextField vornameField;
	private TextField salaryField;
	private IntegerField handyNummerField;
	private DatePicker verbornDatumField;

	/** Adresse Fields **/
	private TextField strasseField;
	private IntegerField strasseNummerField;
	private TextField postleitzahlField;
	private ComboBox<String> stadtField;
	private ComboBox<String> landField;
	private TextField infoField;

	/** Action buttons **/
	private Button saveAction;
	private Button resetAction;
	private Button cleanAction;

	public PersonForm(final PersonDTO personDataToLoad) {
		this();
		personData = personDataToLoad;
		binder.setBean(personDataToLoad);
	}
	
	public PersonForm() {
		LOG.debug("Creating Form instance...");
		binder = new Binder<>(PersonDTO.class);
		loadEmptyBean();

		saveAction = new Button("Save");
		resetAction = new Button("Reset");
		cleanAction = new Button("Clean");

		nameField = new TextField();
		vornameField = new TextField();
		salaryField = new TextField();
		handyNummerField = new IntegerField();
		verbornDatumField = new DatePicker();

		strasseField = new TextField();
		strasseNummerField = new IntegerField();
		postleitzahlField = new TextField();
		stadtField = new ComboBox<String>();
		landField = new ComboBox<String>();
		infoField = new TextField();

		stadtField.setItems("Münschen", "Madrid", "Castelló");
		landField.setItems("España", "Deutschland");
		salaryField.setSuffixComponent(new Span("€"));
		strasseNummerField.setStep(1);

		HorizontalLayout buttons = new HorizontalLayout();
		saveAction.addClickListener(click -> {
			LOG.info("Save action clicked");
			try {
				binder.writeBean(personData);
				binder.readBean(personData);
			} catch (ValidationException e) {
				LOG.error("Error during the save action: ", e);
			}
		});

		resetAction.addClickListener(click -> {
			LOG.info("Reset action clicked");
			binder.readBean(personData);

		});

		cleanAction.addClickListener(click -> {
			LOG.info("Clean action clicked");
			loadEmptyBean();
			resetAction.click();
		});
		saveAction.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		resetAction.addThemeVariants(ButtonVariant.LUMO_ERROR);
		cleanAction.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		buttons.add(saveAction, resetAction, cleanAction);

		binder.bind(nameField, NAME);
		binder.bind(vornameField, VORNAME);
		binder.bind(handyNummerField, person -> person.getHandyNummer() > 0 ? person.getHandyNummer() : null,
				(person, newHandyNummer) -> person.setHandyNummer(newHandyNummer));
		binder.bind(verbornDatumField, VERBORNDATUM);
		binder.forField(salaryField).withConverter(new SalaryConverter()).bind(SALARY);
		binder.bind(strasseField, ADRESSE + "." + STRASSE);
		binder.bind(infoField, ADRESSE + "." + INFO);
		binder.bind(strasseNummerField, ADRESSE + "." + NUMMER);
		binder.bind(postleitzahlField, ADRESSE + "." + POSTLEITZAHL);
		binder.bind(stadtField, ADRESSE + "." + STADT);
		binder.bind(landField, ADRESSE + "." + LAND);

		binder.forField(postleitzahlField).withValidator(new StringLengthValidator("Min 1 and max 9 character", 1, 9))
				.bind(ADRESSE + "." + POSTLEITZAHL);

		postleitzahlField.setMaxLength(9);
		handyNummerField.setMax(999999999);

		addFormItem(nameField, "Name");
		addFormItem(vornameField, "Vorname");
		addFormItem(handyNummerField, "Handy Nummer");
		addFormItem(verbornDatumField, "Verbon Datum");
		addFormItem(salaryField, "Salary");
		addFormItem(new H3("Adresse"), new String());
		addFormItem(strasseField, "Straße");
		addFormItem(infoField, "Weitere Info zur Lieferung");
		addFormItem(strasseNummerField, "Straße Nummer");
		addFormItem(postleitzahlField, "Postleitzahl");
		addFormItem(stadtField, "Stadt");
		addFormItem(landField, "Land");
		addFormItem(buttons, "Aktionen");
		LOG.debug("Created Form instance.");

	}

	private void loadEmptyBean() {
		personData = new PersonDTO();
		binder.setBean(new PersonDTO());
	}
	
	public void setData(final PersonDTO data) {
		personData = data;
		binder.setBean(data);
	}

}
