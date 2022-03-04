package com.bnm.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * The type Carte magic.
 */
@Entity
public class CarteMagic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String manaCost;
    private Integer convertedManaCost;
    private String colors;
    @ElementCollection
    private Set<String> type;
    private String text;
    private String power;
    private String toughness;
    private String imageUrl;

    /**
     * Instantiates a new Carte magic.
     */
    protected CarteMagic(){}


    /**
     * Instantiates a new Carte magic.
     *
     * @param newCarte the new carte
     */
    public CarteMagic(CarteForm newCarte) {
        this.name = newCarte.getName();
        this.manaCost = newCarte.getManaCost();
        this.convertedManaCost = newCarte.getConvertedManaCost();
        this.colors = newCarte.getColors();
        this.type = newCarte.getType();
        this.text = newCarte.getText();
        this.power = newCarte.getPower();
        this.toughness = newCarte.getToughness();
        this.imageUrl = newCarte.getImageUrl();
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets mana cost.
     *
     * @return the mana cost
     */
    public String getManaCost() {
        return manaCost;
    }

    /**
     * Sets mana cost.
     *
     * @param manaCost the mana cost
     */
    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    /**
     * Gets converted mana cost.
     *
     * @return the converted mana cost
     */
    public Integer getConvertedManaCost() {
        return convertedManaCost;
    }

    /**
     * Sets converted mana cost.
     *
     * @param convertedManaCost the converted mana cost
     */
    public void setConvertedManaCost(Integer convertedManaCost) {
        this.convertedManaCost = convertedManaCost;
    }

    /**
     * Gets colors.
     *
     * @return the colors
     */
    public String getColors() {
        return colors;
    }

    /**
     * Sets colors.
     *
     * @param colors the colors
     */
    public void setColors(String colors) {
        this.colors = colors;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Set<String> getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Set<String> type) {
        this.type = type;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets power.
     *
     * @return the power
     */
    public String getPower() {
        return power;
    }

    /**
     * Sets power.
     *
     * @param power the power
     */
    public void setPower(String power) {
        this.power = power;
    }

    /**
     * Gets toughness.
     *
     * @return the toughness
     */
    public String getToughness() {
        return toughness;
    }

    /**
     * Sets toughness.
     *
     * @param toughness the toughness
     */
    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets image url.
     *
     * @param imageUrl the image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteMagic that = (CarteMagic) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(manaCost, that.manaCost) && Objects.equals(convertedManaCost, that.convertedManaCost) && Objects.equals(colors, that.colors) && Objects.equals(type, that.type) && Objects.equals(text, that.text) && Objects.equals(power, that.power) && Objects.equals(toughness, that.toughness) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, manaCost, convertedManaCost, colors, type, text, power, toughness, imageUrl);
    }

    @Override
    public String toString() {
        return "CarteMagic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manaCost='" + manaCost + '\'' +
                ", convertedManaCost=" + convertedManaCost +
                ", colors='" + colors + '\'' +
                ", type=" + type +
                ", text='" + text + '\'' +
                ", power='" + power + '\'' +
                ", toughness='" + toughness + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
