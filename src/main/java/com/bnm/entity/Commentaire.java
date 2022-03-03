package com.bnm.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Commentaire.
 */
@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String texte;
    @ManyToOne
    private Sondage sondage;

    /**
     * Instantiates a new Commentaire.
     */
    protected Commentaire(){}

    /**
     * Instantiates a new Commentaire.
     *
     * @param texte the texte
     */
    public Commentaire(String texte, Sondage sondage) {
        this.texte = texte;
        this.sondage = sondage;
    }

    /**
     * Gets id.
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
     * Gets texte.
     *
     * @return the texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * Sets texte.
     *
     * @param texte the texte
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Gets sondage.
     *
     * @return the sondage
     */
    public Sondage getSondage() {
        return sondage;
    }

    /**
     * Sets sondage.
     *
     * @param sondage the sondage
     */
    public void setSondage(Sondage sondage) {
        this.sondage = sondage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentaire that = (Commentaire) o;
        return id == that.id && Objects.equals(texte, that.texte) && Objects.equals(sondage, that.sondage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texte, sondage);
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", texte='" + texte + '\'' +
                ", sondage=" + sondage +
                '}';
    }
}
