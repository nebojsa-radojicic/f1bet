package com.sporty.f1bet.listener;

import com.sporty.f1bet.entity.AccountEntity;
import com.sporty.f1bet.entity.DriverEntity;
import com.sporty.f1bet.repository.AccountRepository;
import com.sporty.f1bet.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupListener {

    private final AccountRepository accountRepository;
    private final DriverRepository driverRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        if (accountRepository.findById(1).isEmpty()) {
            accountRepository.save(new AccountEntity(1, "Nebojsa Radojicic", 100));
            accountRepository.save(new AccountEntity(2, "Anna Dyga", 100));
            accountRepository.save(new AccountEntity(3, "John Doe", 100));
        }
        if (driverRepository.findById(1).isEmpty()) {
            driverRepository.save(new DriverEntity(1, "Max Verstappen", "Red Bull Racing", "Netherlands"));
            driverRepository.save(new DriverEntity(4, "Lando Norris", "McLaren", "United Kingdom"));
            driverRepository.save(new DriverEntity(5, "Gabriel Bortoleto", "Sauber Motorsport", "Brazil"));
            driverRepository.save(new DriverEntity(6, "Isack Hadjar", "Racing Bulls", "France"));
            driverRepository.save(new DriverEntity(10, "Pierre Gasly", "Alpine F1 Team", "France"));
            driverRepository.save(new DriverEntity(12, "Kimi Antonelli", "Mercedes-Benz in Formula One", "Italy"));
            driverRepository.save(new DriverEntity(14, "Fernando Alonso", "Aston Martin in Formula One", "Spain"));
            driverRepository.save(new DriverEntity(16, "Charles Leclerc", "Scuderia Ferrari", "Monaco"));
            driverRepository.save(new DriverEntity(18, "Lance Stroll", "Aston Martin in Formula One", "Canada"));
            driverRepository.save(new DriverEntity(22, "Yuki Tsunoda", "Red Bull Racing", "Japan"));
            driverRepository.save(new DriverEntity(23, "Alex Albon", "Williams Racing", "Thailand"));
            driverRepository.save(new DriverEntity(27, "Nico Hülkenberg", "Sauber Motorsport", "Germany"));
            driverRepository.save(new DriverEntity(30, "Liam Lawson", "Racing Bulls", "New Zealand"));
            driverRepository.save(new DriverEntity(31, "Esteban Ocon", "Haas F1 Team", "France"));
            driverRepository.save(new DriverEntity(43, "Franco Colapinto", "Alpine F1 Team", "Argentina"));
            driverRepository.save(new DriverEntity(44, "Lewis Hamilton", "Scuderia Ferrari", "United Kingdom"));
            driverRepository.save(new DriverEntity(55, "Carlos Sainz Jr.", "Williams Racing", "Spain"));
            driverRepository.save(new DriverEntity(63, "George Russell", "Mercedes-Benz in Formula One", "United Kingdom"));
            driverRepository.save(new DriverEntity(81, "Oscar Piastri", "McLaren", "Australia"));
            driverRepository.save(new DriverEntity(87, "Oliver Bearman", "Haas F1 Team", "United Kingdom"));
        }
    }

}