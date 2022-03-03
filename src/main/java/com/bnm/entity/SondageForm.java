package com.bnm.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Sondage form.
 */
public class SondageForm {

    private String nom;
    private String description;
    private Date dateLimite;
    private List<Date> dates;

    /**
     * Instantiates a new Sondage form.
     *
     * @param nom         the nom
     * @param description the description
     * @param dateLimite  the date limite
     * @param dates       the dates
     */
    public SondageForm(String nom, String description, Date dateLimite, List<Date> dates) {
        this.nom = nom;
        this.description = description;
        this.dateLimite = dateLimite;
        this.dates = dates;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SondageForm that = (SondageForm) o;
        return Objects.equals(nom, that.nom) && Objects.equals(description, that.description) && Objects.equals(dateLimite, that.dateLimite) && Objects.equals(dates, that.dates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, description, dateLimite, dates);
    }

    @Override
    public String toString() {
        return "SondageForm{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", dateLimite=" + dateLimite +
                ", dates=" + dates +
                '}';
    }


}
