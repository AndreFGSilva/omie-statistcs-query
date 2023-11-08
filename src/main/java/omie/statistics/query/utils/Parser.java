package omie.statistics.query.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import omie.statistics.query.entities.Price;

@Component
public class Parser {

    private final static int DAILY_PRICES_CAPACITY = 2; // Number of different countries obtain in the file (currently ES and PT)
    private final static int PRICES_CAPACITY = 24;  // Number of prices (rows) in the file

    @Autowired
    Utils utils;
    
    public Map<String, List<Price>> parseDailyPrices(String fileString) {
        Map<String, List<Price>> dailyPrices = new HashMap<>(DAILY_PRICES_CAPACITY);

        // Create pattern to match each line
        Pattern pattern = Pattern.compile(Constants.REGEX_FILE, Pattern.MULTILINE);

        // Match with the received file string
        Matcher matcher = pattern.matcher(fileString);
        while (matcher.find()) {
            // Group the results
            List<String> results = matcher.results().map(MatchResult::group).collect(Collectors.toList());
            
            // For each result (line) parse the price and add it to the respective list
            parseLines(results, dailyPrices);
        }

        return dailyPrices;
    }

    private void parseLines(List<String> lines, Map<String, List<Price>> dailyPrices) {
        List<Price> pricesPT = new ArrayList<>(PRICES_CAPACITY);
        List<Price> pricesES = new ArrayList<>(PRICES_CAPACITY);

        // Create pattern to match hour, pricePT and priceES
        Pattern pattern = Pattern.compile(Constants.REGEX_FILE_LINE);

        // For each line parse the price
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches() && matcher.groupCount() == 3) {
                Price pricePT = parsePrice(matcher.group(1), matcher.group(2));
                Price priceES = parsePrice(matcher.group(1), matcher.group(3));

                pricesPT.add(pricePT);
                pricesES.add(priceES);
            }
        }

        // Add to the received map
        dailyPrices.put(Constants.PT, pricesPT);
        dailyPrices.put(Constants.ES, pricesES);
    }

    private Price parsePrice(String hour, String priceMWh) {
        Price price = new Price();
        price.setHour(Integer.parseInt(hour));
        price.setPriceMWh(Float.parseFloat(priceMWh));
        price.setPriceKWh(utils.convertMWhToKWh(Float.parseFloat(priceMWh)));
 
        return price;
    }
}
