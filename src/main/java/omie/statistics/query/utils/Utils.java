package omie.statistics.query.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    
    // Convert Eur/MWh to Eur/KWh
    public float convertMWhToKWh(double priceMWh) {
        return (float) (priceMWh / 1000);
    }
}
