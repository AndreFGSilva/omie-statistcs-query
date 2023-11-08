package omie.statistics.query.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MonthlyPrices {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "prices")
    private List<DailyPrices> prices;
}
