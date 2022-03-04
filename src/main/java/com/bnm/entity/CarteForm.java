package com.bnm.entity;

import java.util.Objects;
import java.util.Set;

/**
 * The type Carte form.
 */
public class CarteForm {
    private String name;
    private String manaCost;
    private Integer convertedManaCost;
    private String colors;
    private Set<String> type;
    private String text;
    private String power;
    private String toughness;
    private String imageUrl;

    /**
     * Instantiates a new Carte form.
     *
     * @param name              the name
     * @param manaCost          the mana cost
     * @param convertedManaCost the converted mana cost
     * @param colors            the colors
     * @param type              the type
     * @param text              the text
     * @param power             the power
     * @param toughness         the toughness
     * @param imageUrl          the image url
     */
    public CarteForm(String name, String manaCost, Integer convertedManaCost, String colors, Set<String> type, String text, String power, String toughness, String imageUrl) {
        this.name = name;
        this.manaCost = manaCost;
        this.convertedManaCost = convertedManaCost;
        this.colors = colors;
        this.type = type;
        this.text = text;
        this.power = power;
        this.toughness = toughness;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

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
        CarteForm carteForm = (CarteForm) o;
        return Objects.equals(name, carteForm.name) && Objects.equals(manaCost, carteForm.manaCost) && Objects.equals(convertedManaCost, carteForm.convertedManaCost) && Objects.equals(colors, carteForm.colors) && Objects.equals(type, carteForm.type) && Objects.equals(text, carteForm.text) && Objects.equals(power, carteForm.power) && Objects.equals(toughness, carteForm.toughness) && Objects.equals(imageUrl, carteForm.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manaCost, convertedManaCost, colors, type, text, power, toughness, imageUrl);
    }

    @Override
    public String toString() {
        return "CarteForm{" +
                "name='" + name + '\'' +
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
