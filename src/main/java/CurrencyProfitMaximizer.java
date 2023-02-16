import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

public class CurrencyProfitMaximizer {

    private static final Logger logger = Logger.getLogger(CurrencyProfitMaximizer.class.getName());

    public static void main(String[] args) {
        try {
            URL url = new URL("https://yfapi.net/v6/finance/quote?region=DE&lang=en&symbols=EURHUF%3DX%2CUSDHUF%3DX%2CGBPHUF%3DX%2C");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("accept", "application/json");
            con.setRequestProperty("X-API-KEY", "bpGXecqqrq1nGBBG6WHBg83Vu7X8VqxB8EMBkKBf");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject quotes = jsonResponse.getJSONObject("quoteResponse");
            JSONObject currencies = quotes.getJSONArray("result").getJSONObject(0);
            double eurHufGain = calculateGain(currencies.getDouble("regularMarketPrice"), currencies.getDouble("twoHundredDayAverage"));
            logger.log(Level.INFO, "EUR/HUF gain: {0}%", new Object[]{eurHufGain});

            currencies = quotes.getJSONArray("result").getJSONObject(1);
            double usdHufGain = calculateGain(currencies.getDouble("regularMarketPrice"), currencies.getDouble("twoHundredDayAverage"));
            logger.log(Level.INFO, "USD/HUF gain: {0}%", new Object[]{usdHufGain});

            currencies = quotes.getJSONArray("result").getJSONObject(2);
            double gbpHufGain = calculateGain(currencies.getDouble("regularMarketPrice"), currencies.getDouble("twoHundredDayAverage"));
            logger.log(Level.INFO, "GBP/HUF gain: {0}%", new Object[]{gbpHufGain});

            if (eurHufGain > usdHufGain && eurHufGain > gbpHufGain) {
                logger.log(Level.INFO, "Sell EUR to HUF to maximize profit");
            } else if (usdHufGain > eurHufGain && usdHufGain > gbpHufGain) {
                logger.log(Level.INFO, "Sell USD to HUF to maximize profit");
            } else if (gbpHufGain > eurHufGain && gbpHufGain > usdHufGain) {
                logger.log(Level.INFO, "Sell GBP to HUF to maximize profit");
            } else {
                logger.log(Level.INFO, "No clear winner for profit maximization");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while calculating the currency with maximum profit", e);
        }
    }

    public static double calculateGain(double price, double price200DayAvg) {
        return (price - price200DayAvg) / price200DayAvg * 100;
    }
}
