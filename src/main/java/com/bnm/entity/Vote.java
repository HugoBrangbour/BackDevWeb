package com.bnm.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * The type Vote.
 */
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Sondage sondage;
    @ManyToOne
    private User user;
    private Date dateChoisie;
    private VoteEnum choix;

    /**
     * Instantiates a new Vote.
     *  @param sondage the sondage
     * @param user    the user
     * @param dateChoisie the date choisie
     * @param choix   the choix
     */
    public Vote(Sondage sondage, User user, Date dateChoisie, VoteEnum choix) {
        this.sondage = sondage;
        this.user = user;
        this.dateChoisie = dateChoisie;
        this.choix = choix;
    }

    /**
     * Instantiates a new Vote.
     */
    protected Vote() {}

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

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets choix.
     *
     * @return the choix
     */
    public VoteEnum getChoix() {
        return choix;
    }

    /**
     * Sets choix.
     *
     * @param choix the choix
     */
    public void setChoix(VoteEnum choix) {
        this.choix = choix;
    }

    /**
     * Gets date choisie.
     *
     * @return the date choisie
     */
    public Date getDateChoisie() {
        return dateChoisie;
    }

    /**
     * Sets date choisie.
     *
     * @param dateChoisie the date choisie
     */
    public void setDateChoisie(Date dateChoisie) {
        this.dateChoisie = dateChoisie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote1 = (Vote) o;
        return id == vote1.id && Objects.equals(sondage, vote1.sondage) && Objects.equals(user, vote1.user) && Objects.equals(choix, vote1.choix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sondage, user, choix);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", sondage=" + sondage +
                ", user=" + user +
                ", choix='" + choix + '\'' +
                '}';
    }
}
