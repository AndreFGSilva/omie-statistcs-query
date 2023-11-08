package omie.statistics.query;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import omie.statistics.query.entities.Price;
import omie.statistics.query.utils.FileUtil;
import omie.statistics.query.utils.Parser;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(FileUtil fileUtil, Parser parser) {
		return runner -> {
			testStuff(fileUtil, parser);
		};
	}

	private void testStuff(FileUtil fileUtil, Parser parser) {
		try {
			String file = fileUtil.getFile();
			
			Map<String, List<Price>> parseDailyPrices = parser.parseDailyPrices(file);
			
			System.out.println(parseDailyPrices);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}