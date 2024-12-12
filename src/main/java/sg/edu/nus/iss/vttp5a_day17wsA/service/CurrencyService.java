package sg.edu.nus.iss.vttp5a_day17wsA.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.vttp5a_day17wsA.constant.Constant;
import sg.edu.nus.iss.vttp5a_day17wsA.model.Currency;

@Service
public class CurrencyService {
    
    @Value("${api.key}")
    private String apiKey;
    
    RestTemplate restTemplate = new RestTemplate(); // Initialize rest template to read the JSON data from the API

    List<Currency> currencies = new ArrayList<>();  // Prepare a list to store the extracted data

    @PostConstruct  // method will be called automatically when application starts, ensuring that the currencies list is always populated at startup
    public void loadCurrencies(){
        
        // Build final url to call
        String countryCallUrl = Constant.COUNTRY_BASE + apiKey;

        try{
            // call the API data source to get all currency data
            String currencyDataRaw = restTemplate.getForObject(countryCallUrl, String.class);

            // Read the raw Json String
            JsonReader jReader = Json.createReader(new StringReader(currencyDataRaw));
            JsonObject jObject = jReader.readObject();
            
            // Extract the "results" Json object
            JsonObject resultsJsonObject = jObject.getJsonObject("results");

            // Now extracting the K:V of CountryCode:CurrencyData
            Set<Entry<String, JsonValue>> entries = resultsJsonObject.entrySet();

            // For each entry (aka CountryCode), extract the needed fields from CurrencyData
            for (Entry<String, JsonValue> entry : entries) {
                String country = entry.getValue().asJsonObject().getString("name");
                String id = entry.getValue().asJsonObject().getString("currencyId");
                String currencyName = entry.getValue().asJsonObject().getString("currencyName");
                String currencySymbol = entry.getValue().asJsonObject().getString("currencySymbol");

                // Using extracted details, create a new currency POJO and add it to the list
                Currency currency = new Currency(country, id, currencyName, currencySymbol);
                currencies.add(currency);
            }
        
        } catch (Exception e) {
            // Log and/or handle errors gracefully
            System.out.println("Error loading currencies: " + e.getMessage());
        }

    }


    public List<Currency> getCurrencies(){
        return currencies;
    }


    public Currency getCurrencyPOJOByID(String currencyID){

        Currency foundCurrency = new Currency();

        // loop through list of currencies and match the id
        for (Currency currency : currencies){
            if (currency.getId().equals(currencyID)){
                foundCurrency = currency;        
            }
        }

        return foundCurrency;
    }


    public Double makeConversionCall(String fromCurrencyID, String toCurrencyID, Double amount){

                // Alternative reference:
                // String manualBuild = UriComponentsBuilder.fromUriString(Url.CONVERT_BASE)                           // https://free.currconv.com
                //                                          .pathSegment("api", "v7", "convert")       // https://free.currconv.com/api/v7/convert
                //                                          .queryParam("q", fromCurrencyID + "_" + toCurrencyID) // https://free.currconv.com/api/v7/convert?q=SGD_JPY
                //                                          .queryParam("compact", "ultra")             // https://free.currconv.com/api/v7/convert?q=SGD_JPY&compact=ultra
                //                                          .queryParam("apiKey", Url.API_KEY)                    // https://free.currconv.com/api/v7/convert?q=SGD_JPY&compact=ultra&apiKey=abc123
                //                                          .toUriString();
                // System.out.println(manualBuild);

        // Build your URL to call the API
        String callUrl = String.format(Constant.CONVERSION_BASE, fromCurrencyID, toCurrencyID, apiKey);
        System.out.println("Making conversion call to: " + callUrl);

        // Get the response from the API and extract the conversion rate (reference below)
        String conversionRateString = restTemplate.getForObject(callUrl, String.class);
        String jsonObjectKey = fromCurrencyID + "_" + toCurrencyID;
        JsonReader jReader = Json.createReader(new StringReader(conversionRateString));
        JsonObject jObject = jReader.readObject();
        Double conversionRate = jObject.getJsonNumber(jsonObjectKey).doubleValue();
        System.out.println(conversionRate);

        // Using conversion rate, do the math
        Double convertedAmount = amount * conversionRate;
        System.out.println(convertedAmount);

        return convertedAmount;
    }
}


// Reference for result of conversion call to https://free.currconv.com/api/v7/convert?q=USD_PHP&compact=ultra&apiKey=59ab9859e27b8f5447e6
// {
// "USD_PHP": 57.902038
// }