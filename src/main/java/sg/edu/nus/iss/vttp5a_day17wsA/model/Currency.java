package sg.edu.nus.iss.vttp5a_day17wsA.model;

public class Currency {
    
    private String country;
    private String id;
    private String currencyName;
    private String currencySymbol;


    public Currency() {
    }

    public Currency(String country, String id, String currencyName, String currencySymbol) {
        this.country = country;
        this.id = id;
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }


    public String getCountry() {
        return country;
    }

    public String getId() {
        return id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }


    @Override
    public String toString() {
        return country + "," + id + "," + currencyName + "," + currencySymbol;
    }


    
}
