package com.india.railway.controller.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.india.railway.model.master.District;
import com.india.railway.model.master.State;
import com.india.railway.repository.mysql.DistrictRepository;
import com.india.railway.repository.mysql.StateRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private StateRepository stateRepository;

    @GetMapping("/state/{stateId}")
    public ResponseEntity<List<District>> getDistrictsByStateId(@PathVariable Long stateId) {
        List<District> districts = districtRepository.findByStateId(stateId);

        List<District> districtslist = new ArrayList<>();

        for (District st : districts) {
            Long districtid = st.getId();
            String districtcode = st.getCode();
            String districtName = st.getName();
            districtslist.add(new District(districtid, districtcode, districtName));
        }
        return ResponseEntity.ok(districtslist);

    }

    // Get all districts
    @GetMapping
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    // Get district by ID
    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(@PathVariable Long id) {
        return districtRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new district with state ID
    @PostMapping
    public ResponseEntity<District> createDistrict(@RequestBody District district) {
        // Ensure state exists
        State state = stateRepository.findById(district.getState().getId()).orElse(null);
        if (state == null) {
            return ResponseEntity.badRequest().body(null);
        }
        district.setState(state);
        return ResponseEntity.ok(districtRepository.save(district));
    }

    // Update district
    @PutMapping("/{id}")
    public ResponseEntity<District> updateDistrict(@PathVariable Long id, @RequestBody District updatedDistrict) {
        return districtRepository.findById(id)
                .map(district -> {
                    district.setName(updatedDistrict.getName());
                    district.setCode(updatedDistrict.getCode());
                    if (updatedDistrict.getState() != null) {
                        State newState = stateRepository.findById(updatedDistrict.getState().getId()).orElse(null);
                        if (newState != null) {
                            district.setState(newState);
                        }
                    }
                    return ResponseEntity.ok(districtRepository.save(district));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete district
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDistrict(@PathVariable Long id) {
        return districtRepository.findById(id)
                .map(district -> {
                    districtRepository.delete(district);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}