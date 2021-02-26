package com.project.exchange_api;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeDataLoader {

        public static List<String> getLiveRates() {

            String key = "ebce46bdf6365b9debdec66ba93da086";

            URL url = null;
            try {
                url = new URL("http://api.currencylayer.com/live?access_key=" + key);
            } catch (MalformedURLException e) {
                System.out.println("Couldn't create url for api");
            }

            String data = null;
            try {
                assert url != null;
                data = IOUtils.toString(url);
            } catch (IOException e) {
                System.out.println("Error while parsing url to string");
            }

            List<String> ratesList = new ArrayList<>();
            assert data != null;
            JSONObject rates = new JSONObject(data);
            ratesList.add("EUR - " + rates.getJSONObject("quotes").getDouble("USDEUR"));
            ratesList.add("GBP - " + rates.getJSONObject("quotes").getDouble("USDGBP"));
            ratesList.add("NZD - " + rates.getJSONObject("quotes").getDouble("USDNZD"));
            ratesList.add("AUD - " + rates.getJSONObject("quotes").getDouble("USDAUD"));
            ratesList.add("JPY - " + rates.getJSONObject("quotes").getDouble("USDJPY"));

            return ratesList;
        }

}
