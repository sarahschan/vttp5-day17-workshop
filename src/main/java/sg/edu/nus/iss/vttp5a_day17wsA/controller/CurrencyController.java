package sg.edu.nus.iss.vttp5a_day17wsA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import sg.edu.nus.iss.vttp5a_day17wsA.model.ConversionForm;
import sg.edu.nus.iss.vttp5a_day17wsA.model.Currency;
import sg.edu.nus.iss.vttp5a_day17wsA.service.CurrencyService;

@Controller
@RequestMapping(path={"", "/"})
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
        
        // Get the list of currencies and form template
        List<Currency> currencies = currencyService.getCurrencies();
        ConversionForm form = new ConversionForm();
            form.setFromCurrency("");   // These makes sure that the placeholders can be seen
            form.setToCurrency("");

        // Add these attributes to build the form page
        model.addAttribute("currencies", currencies);
        model.addAttribute("form", form);

        return "index";
    }


    @PostMapping("/convert")
    public String handleConversion(@Valid @ModelAttribute("form") ConversionForm form, BindingResult result, Model model){
        
        System.out.println("Recieved details: " + form);

        // check if all fields are good
        if (result.hasErrors()){
            List<Currency> currencies = currencyService.getCurrencies();
            model.addAttribute("currencies", currencies);
            model.addAttribute("form", form);

            return "index";
        }

        // if all is good, proceed with the conversion
        String fromCurrencyID = form.getFromCurrency();
        String toCurrencyID = form.getToCurrency();
        Double amount = form.getAmount();
        currencyService.makeConversionCall(fromCurrencyID, toCurrencyID, amount);

        return null;

    }
}
