package com.example.application.model;

import java.time.LocalDate;

import com.vaadin.flow.data.binder.PropertyId;

public class PersonDTO {

	private static int nextId = 1;
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String VORNAME = "vorName";
	public static final String HANDYNUMMER = "handyNummer";
	public static final String VERBORNDATUM = "verbornDatum";
	public static final String SALARY = "salary";
	public static final String ADRESSE = "adresse";

	@PropertyId(ID)
	private int id;
	@PropertyId(NAME)
	private String name;
	@PropertyId(VORNAME)
	private String vorName;
	@PropertyId(HANDYNUMMER)
	private int handyNummer;
	@PropertyId(SALARY)
	private double salary;
	@PropertyId(VERBORNDATUM)
	private LocalDate verbornDatum;
	@PropertyId(ADRESSE)
	private AdresseDTO adresse;

	public PersonDTO() {
		id = nextId++;
		verbornDatum = LocalDate.MIN;
		adresse = new AdresseDTO();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorName() {
		return vorName;
	}

	public void setVorName(String vorName) {
		this.vorName = vorName;
	}

	public int getHandyNummer() {
		return handyNummer;
	}

	public void setHandyNummer(int handyNummer) {
		this.handyNummer = handyNummer;
	}

	public LocalDate getVerbornDatum() {
		return verbornDatum;
	}

	public void setVerbornDatum(LocalDate verbornDatum) {
		this.verbornDatum = verbornDatum;
	}

	public AdresseDTO getAdresse() {
		return adresse;
	}

	public void setAdresse(AdresseDTO adresse) {
		this.adresse = adresse;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static class AdresseDTO {
		
		public static final String STRASSE = "strasse";
		public static final String NUMMER = "nummer";
		public static final String INFO = "info";
		public static final String POSTLEITZAHL = "postleitzahl";
		public static final String STADT = "stadt";
		public static final String LAND = "land";
		
		@PropertyId(STRASSE)
		private String strasse;
		@PropertyId(NUMMER)
		private int nummer;
		@PropertyId(INFO)
		private String info;
		@PropertyId(POSTLEITZAHL)
		private String postleitzahl;
		@PropertyId(STADT)
		private String stadt;
		@PropertyId(LAND)
		private String land;

		public String getStrasse() {
			return strasse;
		}

		public void setStrasse(String strasse) {
			this.strasse = strasse;
		}

		public int getNummer() {
			return nummer;
		}

		public void setNummer(int nummer) {
			this.nummer = nummer;
		}

		public String getPostleitzahl() {
			return postleitzahl;
		}

		public void setPostleitzahl(String postleitzahl) {
			this.postleitzahl = postleitzahl;
		}

		public String getStadt() {
			return stadt;
		}

		public void setStadt(String stadt) {
			this.stadt = stadt;
		}

		public String getLand() {
			return land;
		}

		public void setLand(String land) {
			this.land = land;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}
	}
}
