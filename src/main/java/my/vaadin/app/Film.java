package my.vaadin.app;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@SuppressWarnings("serial")
public class Film implements Serializable, Cloneable {

	private Long id;

	private String nazwa = "";

	private String opis = "";

	private String czasTrwania;

	private Sala sala;

	private String limitWiekowy = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getCzasTrwania() {
		return czasTrwania;
	}

	public void setCzasTrwania(String czasTrwania) {
		this.czasTrwania = czasTrwania;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public String getLimitWiekowy() {
		return limitWiekowy;
	}

	public void setLimitWiekowy(String limitWiekowy) {
		this.limitWiekowy = limitWiekowy;
	}

	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.nazwa == null) {
			return false;
		}

		if (obj instanceof Film && obj.getClass().equals(getClass())) {
			return this.nazwa.equals(((Film) obj).nazwa);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (nazwa == null ? 0 : nazwa.hashCode());
		return hash;
	}

	@Override
	public Film clone() throws CloneNotSupportedException {
		return (Film) super.clone();
	}

	@Override
	public String toString() {
		return nazwa + ": " + opis;
	}
}