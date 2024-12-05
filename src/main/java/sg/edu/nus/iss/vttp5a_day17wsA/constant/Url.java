package sg.edu.nus.iss.vttp5a_day17wsA.constant;

public class Url {
    
    public static final String API_URL = "https://free.currconv.com/api/v7/countries";
    
    public static final String API_KEY = "59ab9859e27b8f5447e6";

    public static final String URL_AND_KEY = API_URL + "?apiKey=" + API_KEY;

    public static final String CONVERT_URL = "https://free.currconv.com/api/v7/convert?q=%s_%s&compact=ultra&apiKey=" + API_KEY;

}
