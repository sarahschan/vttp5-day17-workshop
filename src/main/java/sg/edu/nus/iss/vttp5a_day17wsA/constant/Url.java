package sg.edu.nus.iss.vttp5a_day17wsA.constant;

public class Url {
    
    public static final String API_URL = "https://free.currconv.com/api/v7/countries";
    
    public static final String API_KEY = "3aca7ff794e876ae6610";

    public static final String URL_AND_KEY = API_URL + "?apiKey=" + API_KEY;

    public static final String CONVERT_URL = "https://free.currconv.com/api/v7/convert?q=%s_%s&compact=ultra&apiKey=" + API_KEY;

    public static final String CONVERT_BASE = "https://free.currconv.com";
}
