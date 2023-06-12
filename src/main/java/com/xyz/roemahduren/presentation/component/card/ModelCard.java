package com.xyz.roemahduren.presentation.component.card;

import javax.swing.*;

public class ModelCard {

    private Icon icon;
    private String title;
    private String values;
    private String description;

    public ModelCard(Icon icon, String title, String values, String description) {
        this.icon = icon;
        this.title = title;
        this.values = values;
        this.description = description;
    }

    public ModelCard(Icon icon, String title, String values) {
        this.icon = icon;
        this.title = title;
        this.values = values;
    }

    public ModelCard() {
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
