package com.india.railway.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.master.Country;
import com.india.railway.model.master.District;
import com.india.railway.model.master.State;
import com.india.railway.model.master.StateDTO;
import com.india.railway.repository.mysql.CountryRepository;
import com.india.railway.repository.mysql.StateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<State>> getStateByCountryId(@PathVariable Long countryId) {
        // List<State> stateList = stateRepository.findByCountryId(countryId);

        List<State> rawStates = stateRepository.findByCountryId(countryId);
        List<State> states = new ArrayList<>();

        for (State st : rawStates) {
            String statecode = st.getCode();
            String stateName = st.getName();
            states.add(new State(statecode, stateName));
        }
        return ResponseEntity.ok(states);
    }

    @GetMapping("/{id}")
    public ResponseEntity<State> getStateById(@PathVariable Long id) {
        Optional<State> state = stateRepository.findById(id);
        return state.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createState(@RequestBody State state) {
        if (state.getCountry() != null && state.getCountry().getId() != null) {
            Optional<Country> country = countryRepository.findById(state.getCountry().getId());
            if (country.isPresent()) {
                state.setCountry(country.get());
                return ResponseEntity.ok(stateRepository.save(state));
            } else {
                return ResponseEntity.badRequest().body("Invalid country ID");
            }
        } else {
            return ResponseEntity.badRequest().body("Country must be provided with valid ID");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateState(@PathVariable Long id, @RequestBody State updatedState) {
        return stateRepository.findById(id).map(state -> {
            state.setName(updatedState.getName());
            state.setCode(updatedState.getCode());

            if (updatedState.getCountry() != null && updatedState.getCountry().getId() != null) {
                Optional<Country> country = countryRepository.findById(updatedState.getCountry().getId());
                if (country.isPresent()) {
                    state.setCountry(country.get());
                } else {
                    return ResponseEntity.badRequest().body("Invalid country ID");
                }
            }

            return ResponseEntity.ok(stateRepository.save(state));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
