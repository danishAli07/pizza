package com.synuptest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PizzaModel {

    @SerializedName("variants")
    @Expose
    private Variants variants;

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

}
