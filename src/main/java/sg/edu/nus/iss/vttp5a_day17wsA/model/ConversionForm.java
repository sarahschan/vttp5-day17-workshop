package sg.edu.nus.iss.vttp5a_day17wsA.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ConversionForm {
    
        @NotNull(message = "Please select a currency")
    private String fromCurrency;

        @NotNull(message = "Please select a currency")
    private String toCurrency;

        @NotNull(message = "Please enter an amount")
        @Positive(message = "Amount must be a positive number")
    private Double amount;

    
    public ConversionForm() {
    }


    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return fromCurrency + "," + toCurrency + "," + amount;
    }

    

}
