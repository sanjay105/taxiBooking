package com.taxi.booking.chat.taxiBooking.connection;

import org.springframework.data.repository.CrudRepository;

import com.taxi.booking.chat.taxiBooking.model.User;

public interface UserDao extends CrudRepository<User, String> {
	
}
