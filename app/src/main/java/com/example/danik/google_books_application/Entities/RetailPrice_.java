
package com.example.danik.google_books_application.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailPrice_ {

    @SerializedName("amountInMicros")
    @Expose
    private Float amountInMicros;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    public Float getAmountInMicros() {
        return amountInMicros;
    }

    public void setAmountInMicros(Float amountInMicros) {
        this.amountInMicros = amountInMicros;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}
