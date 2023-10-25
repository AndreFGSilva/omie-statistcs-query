package omie.statistics.query;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import omie.statistics.query.utils.FileUtil;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(FileUtil fileUtil) {
		return runner -> {
			testStuff(fileUtil);
		};
	}

	private void testStuff(FileUtil fileUtil) {
		try {
			String file = fileUtil.getFile();
			System.out.println(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}