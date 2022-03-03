package com.bnm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type User.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nom;
    @OneToMany
    private List<Vote> votes;

    /**
     * Instantiates a new User.
     */
    protected User(){}

    /**
     * Instantiates a new User.
     *
     * @param nom   the nom
     * @param votes the votes
     */
    public User(String nom, List<Vote> votes) {
        this.nom = nom;
        this.votes = votes;
    }

    /**
     * Instantiates a new User.
     *
     * @param nom the nom
     */
    public User(String nom){
        this.nom = nom;
        this.votes = new ArrayList<>();
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
     * Gets votes.
     *
     * @return the votes
     */
    public List<Vote> getVotes() {
        return votes;
    }

    /**
     * Sets votes.
     *
     * @param votes the votes
     */
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && nom.equals(user.nom) && Objects.equals(votes, user.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, votes);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", votes=" + votes +
                '}';
    }
}
