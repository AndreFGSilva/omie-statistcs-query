package omie.statistics.query.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Price {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "price_hour")
    private int hour;
    
    @Column(name = "price_mwh")
    private float priceMWh;

    @Column(name = "price_kwh")
    private float priceKWh;
}
