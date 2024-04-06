package hw.tqs.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import hw.tqs.cache.CachingConfig;
import hw.tqs.http.HttpClient;

@Service
public class ExchangeRateService {

    private final HttpClient httpClient;
    private final Map<String, Double> exchangeRatesCache;

    private static final Logger logger = LogManager.getLogger(ExchangeRateService.class);


    // https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_fl0HmrwcSrZVVUYsQP0sIGIZYpI6WJGuaF7nqCHt&currencies=EUR
    //https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_fl0HmrwcSrZVVUYsQP0sIGIZYpI6WJGuaF7nqCHt&currencies=CAD&base_currency=EUR

    private final String apiUrl = "https://api.freecurrencyapi.com/v1/latest";
    private final String apiKey = "fca_live_fl0HmrwcSrZVVUYsQP0sIGIZYpI6WJGuaF7nqCHt";

    public ExchangeRateService(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.exchangeRatesCache = new HashMap<>();
    }

    @Cacheable(cacheNames = "exchangeRates", key = "#baseCurrency + #targetCurrency")
    public double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException, ParseException {
        String cacheKey = baseCurrency + targetCurrency;

        logger.info("Getting exchange rate from " + baseCurrency + " to " + targetCurrency);

        if (exchangeRatesCache.containsKey(cacheKey)) {
            logger.info("Cache hit for " + baseCurrency + " to " + targetCurrency + " exchange rate value: " + exchangeRatesCache.get(cacheKey));
            return exchangeRatesCache.get(cacheKey);
        } else {
            logger.info("Cache miss for " + baseCurrency + " to " + targetCurrency + " exchange rate");
            String requestUrl = apiUrl + "?apikey=" + apiKey + "&currencies=" + targetCurrency + "&base_currency=" + baseCurrency;
            String response = httpClient.doHttpGet(requestUrl);
            double exchangeRate = parseExchangeRateFromResponse(response, targetCurrency);

            logger.info("Exchange rate from " + baseCurrency + " to " + targetCurrency + " is " + exchangeRate + " retrieved from API");

            exchangeRatesCache.put(cacheKey, exchangeRate);

            logger.info("Cache updated with " + baseCurrency + " to " + targetCurrency + " exchange rate value: " + exchangeRate);

            return exchangeRate;
        }
    }

    @CacheEvict(cacheNames = "exchangeRates", allEntries = true)
    public void clearCache() {
        exchangeRatesCache.clear();
    }

    private double parseExchangeRateFromResponse(String response, String targetCurrency) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONObject data = (JSONObject) jsonObject.get("data");
        Double exchangeRate = (Double) data.get(targetCurrency);
    
        return exchangeRate != null ? exchangeRate : 0.0;
    }
    
}