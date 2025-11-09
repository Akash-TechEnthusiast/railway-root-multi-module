package com.india.railway.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.master.Country;
import com.india.railway.model.master.State;
import com.india.railway.repository.mysql.CountryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Optional<Country> country = countryRepository.findById(id);
        return country.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Country createCountry(@RequestBody Country country) {

        return countryRepository.save(country);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country updatedCountry) {
        return countryRepository.findById(id)
                .map(country -> {
                    country.setName(updatedCountry.getName());
                    country.setCode(updatedCountry.getCode());
                    return ResponseEntity.ok(countryRepository.save(country));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
