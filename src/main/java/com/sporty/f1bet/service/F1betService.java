package com.sporty.f1bet.service;

import com.sporty.f1bet.dto.*;
import com.sporty.f1bet.entity.AccountEntity;
import com.sporty.f1bet.entity.BetEntity;
import com.sporty.f1bet.entity.OutcomeEntity;
import com.sporty.f1bet.provider.openf1.OpenF1Client;
import com.sporty.f1bet.repository.AccountRepository;
import com.sporty.f1bet.repository.BetRepository;
import com.sporty.f1bet.repository.DriverRepository;
import com.sporty.f1bet.repository.OutcomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
@RequiredArgsConstructor
public class F1betService {

    private final DriverRepository driverRepository;
    private final AccountRepository accountRepository;
    private final BetRepository betRepository;
    private final OutcomeRepository outcomeRepository;

    private final OpenF1Client openF1Client;

    public Collection<ProviderEvents> getEvents(String type, Integer year, String country) {
        return List.of(getEventsOpenF1(type, year, country));
    }

    private ProviderEvents getEventsOpenF1(String type, Integer year, String country) {
        Collection<Event> events = openF1Client.getSessions(type, year, country).stream().map(event -> new Event(
                event.session_key(),
                event.location(),
                event.date_start(),
                event.date_end(),
                event.session_type(),
                event.country_name(),
                event.circuit_short_name(),
                getDrivers()
        )).toList();
        return new ProviderEvents("OPENF1", events);
    }

    private Collection<Driver> getDrivers() {
        return driverRepository.findAll().stream().map(driver -> new Driver(
                driver.getId(),
                driver.getName(),
                ThreadLocalRandom.current().nextInt(3) + 2
        )).toList();
    }

    public Integer placeBet(Bet bet) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(bet.account());
        if (accountEntity.isEmpty()) {
            throw new RuntimeException("This account does not exist!");
        }
        AccountEntity account = accountEntity.get();
        if (account.getBalance() < bet.amount()) {
            throw new RuntimeException("Amount is greater than balance!");
        }
        if (outcomeRepository.findByProviderAndEvent(bet.provider(), bet.event()).isPresent()) {
            throw new RuntimeException("This event is already finished!");
        }

        BetEntity betEntity = new BetEntity();
        betEntity.setProvider(bet.provider());
        betEntity.setEvent(bet.event());
        betEntity.setDriver(bet.driver());
        betEntity.setOdds(bet.odds());
        betEntity.setAccount(bet.account());
        betEntity.setAmount(bet.amount());
        betEntity.setStatus("PLACED");
        betRepository.save(betEntity);

        account.setBalance(account.getBalance() - bet.amount());
        accountRepository.save(account);

        return betEntity.getId();
    }

    public Integer eventOutcome(Outcome outcome) {
        if (outcomeRepository.findByProviderAndEvent(outcome.provider(), outcome.event()).isPresent()) {
            throw new RuntimeException("This event outcome already exists!");
        }

        OutcomeEntity outcomeEntity = new OutcomeEntity();
        outcomeEntity.setProvider(outcome.provider());
        outcomeEntity.setEvent(outcome.event());
        outcomeEntity.setDriver(outcome.driver());
        outcomeEntity.setProcessed("N");
        outcomeRepository.save(outcomeEntity);

        return outcomeEntity.getId();
    }

    public void processEventOutcomes() {
        outcomeRepository.findByProcessed("N").forEach(outcome -> {
            betRepository.findByProviderAndEvent(outcome.getProvider(), outcome.getEvent()).forEach(bet -> {
                if (bet.getDriver().equals(outcome.getDriver())) {
                    bet.setStatus("WON");
                    Optional<AccountEntity> accountEntity = accountRepository.findById(bet.getAccount());
                    if (accountEntity.isPresent()) {
                        AccountEntity account = accountEntity.get();
                        account.setBalance(account.getBalance() + bet.getAmount() * bet.getOdds());
                        accountRepository.save(account);
                    }
                }
                else {
                    bet.setStatus("LOST");
                }
                betRepository.save(bet);
            });
            outcome.setProcessed("Y");
            outcomeRepository.save(outcome);
        });
    }

}