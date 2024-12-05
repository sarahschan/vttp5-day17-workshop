package sg.edu.nus.iss.vttp5a_day17wsA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.vttp5a_day17wsA.model.Currency;
import sg.edu.nus.iss.vttp5a_day17wsA.service.CurrencyService;

@Controller
@RequestMapping()
public class CurrencyController {
    
    @Autowired
    CurrencyService currencyService;


    // // testing if I was able to properly extract the data from API source
    // @GetMapping()
    // @ResponseBody
    // public List<Currency> getAllCurrencies(){
    //     List<Currency> currencies = currencyService.getCurrencies();
    //     return currencies;
    // }

    @GetMapping()
    public String homePage(Model model){
        
        List<Currency> currencies = currencyService.getCurrencies();
        model.addAttribute("currencies", currencies);
        
        return "index";
    }

}
