package com.taxi.booking.chat.taxiBooking.connection;

import org.springframework.data.repository.CrudRepository;

import com.taxi.booking.chat.taxiBooking.model.TaxiStatus;

public interface TaxiStatusDao extends CrudRepository<TaxiStatus,Long> {

}
