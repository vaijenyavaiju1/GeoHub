package com.example.geohub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CountryRepository;
import com.example.geohub.model.Country;

@Service
public class CountryJpaService implements CountryRepository {
    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<Country> getCountries() {
        List<Country> countrylist = countryJpaRepository.findAll();
        ArrayList<Country> countries = new ArrayList<>(countrylist);
        return countries;
    }

    @Override
    public Country getCountryById(int countryId) {
        try {
            Country country = countryJpaRepository.findById(countryId).get();
            return country;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Country addCountry(Country country) {
        countryJpaRepository.save(country);
        return country;
    }

    @Override
    public Country updateCountry(int countryId, Country country) {
        try {
            Country newC = countryJpaRepository.findById(countryId).get();
            if (country.getCountryName() != null) {
                newC.setCountryName(country.getCountryName());
            }
            if (country.getCurrency() != null) {
                newC.setCurrency(country.getCurrency());
            }
            if (country.getPopulation() != 0) {
                newC.setPopulation(country.getPopulation());
            }
            if (country.getLatitude() != null) {
                newC.setLatitude(country.getLatitude());
            }
            if (country.getLongitude() != null) {
                newC.setLongitude(country.getLongitude());
            }
            countryJpaRepository.save(newC);
            return newC;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteCountry(int countryId) {
        try {
            countryJpaRepository.deleteById(countryId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}