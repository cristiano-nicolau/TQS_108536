package hw.tqs.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class CachingConfig {
     private static final Logger logger = LogManager.getLogger(CachingConfig.class);

    @Bean
    public CacheManager cacheManager() {
        logger.info("Creating cache manager");
        return new ConcurrentMapCacheManager("exchangeRates");
    }
}

