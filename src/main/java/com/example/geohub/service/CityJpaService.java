package com.example.geohub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

import com.example.geohub.repository.CityJpaRepository;
import com.example.geohub.repository.CityRepository;
import com.example.geohub.repository.CountryJpaRepository;
import com.example.geohub.repository.CountryJpaRepository;

import com.example.geohub.model.City;
import com.example.geohub.model.Country;

@Service
public class CityJpaService implements CityRepository {
    @Autowired
    private CityJpaRepository cityJpaRepository;

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Override
    public ArrayList<City> getCities() {
        List<City> citylist = cityJpaRepository.findAll();
        ArrayList<City> cities = new ArrayList<>(citylist);
        return cities;
    }

    @Override
    public City getCityById(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            return city;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override

    public City addCity(City city) {
        Country country = city.getCountry();
        int countryId = country.getCountryId();
        try {
            country = countryJpaRepository.findById(countryId).get();
            city.setCountry(country);
            cityJpaRepository.save(city);
            return city;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
    }

    @Override

    public City updateCity(int cityId, City city) {
        try {
            City newC = cityJpaRepository.findById(cityId).get();
            if (city.getCountry() != null) {
                Country country = city.getCountry();
                int countryId = country.getCountryId();
                Country newCountry = countryJpaRepository.findById(countryId).get();
                newC.setCountry(newCountry);

            }
            if (city.getCityName() != null) {
                newC.setCityName(city.getCityName());
            }
            if (city.getPopulation() != 0) {
                newC.setPopulation(city.getPopulation());

            }
            if (city.getLatitude() != null) {
                newC.setLatitude(city.getLatitude());
            }
            if (city.getLongitude() != null) {
                newC.setLongitude(city.getLongitude());
            }

            cityJpaRepository.save(newC);
            return newC;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override

    public void deleteCity(int cityId) {
        try {
            cityJpaRepository.deleteById(cityId);
           
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
         throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

    @Override

    public Country getCountry(int cityId) {
        try {
            City city = cityJpaRepository.findById(cityId).get();
            Country country = city.getCountry();
            return country;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}