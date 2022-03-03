package com.bnm.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Sondage.
 */
@Entity
public class Sondage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nom;
    private String description;
    private Date dateLimite;
    @ElementCollection
    private List<Date> dates;
    private boolean ouvert;

    /**
     * Instantiates a new Sondage.
     */
    protected Sondage() {}


    /**
     * Instantiates a new Sondage.
     *  @param nom         the nom
     * @param description the description
     * @param dateLimite  the date limite
     * @param ouvert      the ouvert
     * @param dates       the dates
     */
    public Sondage(String nom, String description, Date dateLimite, boolean ouvert, List<Date> dates) {
        this.nom = nom;
        this.description = description;
        this.dateLimite = dateLimite;
        this.ouvert = ouvert;
        this.dates = dates;
    }

    /**
     * Instantiates a new Sondage.
     *
     * @param nom         the nom
     * @param description the description
     * @param dateLimite  the date limite
     * @param dates       the dates
     */
    public Sondage(String nom, String description, Date dateLimite, List<Date> dates) {
        this.nom = nom;
        this.description = description;
        this.dateLimite = dateLimite;
        this.dates = dates;
        this.ouvert = true;
    }

    /**
     * Gets Id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets nom.
     *
     * @param nom the nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets date limite.
     *
     * @return the date limite
     */
    public Date getDateLimite() {
        return dateLimite;
    }

    /**
     * Sets date limite.
     *
     * @param dateLimite the date limite
     */
    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    /**
     * Gets dates.
     *
     * @return the dates
     */
    public List<Date> getDates() {
        return dates;
    }

    /**
     * Sets dates.
     *
     * @param dates the dates
     */
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    /**
     * Is ouvert boolean.
     *
     * @return the boolean
     */
    public boolean isOuvert() {
        return ouvert;
    }

    /**
     * Sets ouvert.
     *
     * @param ouvert the ouvert
     */
    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
    }

    /**
     * Add a new Date
     *
     * @param newDate the newDate
     */
    public void addDate(Date newDate){
        this.dates.add(newDate);
    }

    /**
     * delete a Date
     *
     * @param oldDate the oldDate
     */
    public void deleteDate(int oldDate){
        this.dates.remove(oldDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sondage sondage = (Sondage) o;
        return id == sondage.id && ouvert == sondage.ouvert && Objects.equals(nom, sondage.nom) && Objects.equals(description, sondage.description) && Objects.equals(dateLimite, sondage.dateLimite) && Objects.equals(dates, sondage.dates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, description, dateLimite, dates, ouvert);
    }

    @Override
    public String toString() {
        return "Sondage{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateLimite=" + dateLimite +
                ", dates=" + dates +
                ", ouvert=" + ouvert +
                '}';
    }
}
