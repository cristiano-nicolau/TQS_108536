package hw.tqs.services;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import hw.tqs.config.ConfigUtils;
import hw.tqs.http.HttpClient;


@Service
@CacheConfig(cacheNames = "exchangeRates")
public class ExchangeRateService {

    private HttpClient httpClient;

    String url = "https://api.exchangeratesapi.io/latest";

    // https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_fl0HmrwcSrZVVUYsQP0sIGIZYpI6WJGuaF7nqCHt&currencies=EUR%2CUSD%2CCAD
    String baseCurrency = "USD";

    String api_key = "fca_live_fl0HmrwcSrZVVUYsQP0sIGIZYpI6WJGuaF7nqCHt";

    public ExchangeRateService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    





}
