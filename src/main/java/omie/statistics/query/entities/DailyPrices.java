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
public class DailyPrices {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "day")
    private int day;

    @Column(name = "prices_pt")
    private List<Price> pricesPT;

    @Column(name = "prices_es")
    private List<Price> pricesES;
}
