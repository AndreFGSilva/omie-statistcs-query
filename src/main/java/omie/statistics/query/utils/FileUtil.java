package omie.statistics.query.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {
    
    public String getFile() throws IOException {
        // Build daily url string
        String url = buildDailyURL();

        // Create temporary file in local system
        downloadFile(url);

        // Read file content to a String
        return Files.readString(Path.of(Constants.TEMP_FILE_PATH));
    }

    private String buildDailyURL() {
        // Get information about the actual day
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        // Correct month value
        String monthString = Integer.toString(month);
        if (month < 10) {
            monthString = Constants.ZERO_NUMBER + monthString;
        }

        // Build final url
        return Constants.INITIAL_URL + year + monthString + day + Constants.FINAL_URL;
    }

    private void downloadFile(String url) throws IOException {
        // Open byte channel
        ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
        
        // Create temporary file and write to it
        FileOutputStream fileOutputStream = new FileOutputStream(Constants.TEMP_FILE_PATH);
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileChannel.transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

        fileOutputStream.close();
    }
}