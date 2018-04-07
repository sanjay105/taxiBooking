package com.taxi.booking.chat.taxiBooking.connection;

import org.springframework.data.repository.CrudRepository;

import com.taxi.booking.chat.taxiBooking.model.TaxiInfo;

public interface TaxiDao extends CrudRepository<TaxiInfo, Long>{

}
