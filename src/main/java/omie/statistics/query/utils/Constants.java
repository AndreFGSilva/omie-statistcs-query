package omie.statistics.query.utils;

public class Constants {

    // VALUES
    public final static String ZERO_NUMBER = "0";
    
    // COUNTRIES
    public final static String PT = "PT";
    public final static String ES = "ES";

    // FILES
    public final static String FINAL_URL = ".1";
    public final static String INITIAL_URL = "https://www.omie.es/en/file-download?parents%5B0%5D=marginalpdbcpt&filename=marginalpdbcpt_";
    public final static String TEMP_FILE_PATH = "files\\dailyTemp.txt";

    public final static String REGEX_FILE = "^[\\d;\\.]*;";
    public final static String REGEX_FILE_LINE = "[\\d;]{11}(\\d+);(\\d+\\.?\\d+);(\\d+\\.?\\d+);";
}
