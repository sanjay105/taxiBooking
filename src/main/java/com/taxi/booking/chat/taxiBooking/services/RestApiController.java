package com.taxi.booking.chat.taxiBooking.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.taxi.booking.chat.taxiBooking.connection.TaxiDao;
import com.taxi.booking.chat.taxiBooking.connection.TaxiStatusDao;
import com.taxi.booking.chat.taxiBooking.connection.UserDao;
import com.taxi.booking.chat.taxiBooking.model.Driver;
import com.taxi.booking.chat.taxiBooking.model.TaxiInfo;
import com.taxi.booking.chat.taxiBooking.model.TaxiStatus;
import com.taxi.booking.chat.taxiBooking.model.User;
 
@RestController
@RequestMapping("/api")
public class RestApiController {
 
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
 
    @Autowired
	UserDao userDao;
    
    @Autowired
    TaxiDao taxiDao;
    
    @Autowired
    TaxiStatusDao taxiStatusDao;
    
//    @Autowired
//    DriverDao driverDoa;
//    //Service which will do all data retrieval/manipulation work
// 
    // -------------------Retrieve All Users---------------------------------------------
 
/*    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 */
    // -------------------Retrieve Single User------------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") String email) {
        logger.info("Fetching User with id {}", email);
        Iterable<User> users = userDao.findAll();
        User user;
        Iterator itr = users.iterator();
        while(itr.hasNext()) {
        	user = (User) itr.next();
        	if(user.getEmail().equals(email)) {
        		return new ResponseEntity<User>(user, HttpStatus.OK);
        	}
        }
        	logger.error("User with id {} not found.", email);
        	User err=new User();
        	err.setEmail(email);
            return new ResponseEntity(err, HttpStatus.NOT_FOUND);
       
        
    }
    // --------------------Get Taxi Details--------------------------------------
    
    @RequestMapping(value="/book/{id}",method=RequestMethod.GET)
    public void bookTaxi(@PathVariable("id") int id) {
    	EnterIntoDB(id);
    }
    
    @RequestMapping(value="/taxi",method=RequestMethod.GET)
    public ArrayList<TaxiInfo> getTaxi(){
    	Iterable<TaxiInfo> taxi = taxiDao.findAll();
    	ArrayList<TaxiInfo> res=new ArrayList<TaxiInfo>();
    	Iterator itr=taxi.iterator();
    	while(itr.hasNext()) {
    		TaxiInfo row=(TaxiInfo)itr.next();
    		if(status(row.getId())) {
    		res.add(row );
    		}
    	}
    	return res;
    }
    private boolean status(int id) {
		Iterable<TaxiStatus> stat=taxiStatusDao.findAll();
		Iterator itr=stat.iterator();
		int i=0;
		while(itr.hasNext()) {
			TaxiStatus r=(TaxiStatus) itr.next();
			if(r.getId()==id) {
				if(r.isStatus()) {
					return true;
				}else {
					return false;
				}
			}
		}
		EnterIntoDB(id);
		return true;
	}
	private void EnterIntoDB(int id) {
		// TODO Auto-generated method stub
		TaxiStatus taxiStat=new TaxiStatus();
		taxiStat.setId(id);
		taxiStat.setStatus(false);
		System.out.println(taxiStat);
		taxiStatusDao.save(taxiStat);
	}
	// -------------------Create a User-------------------------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        logger.info("Creating User : {}", user);
        userDao.save(user);
        //HttpHeaders headers = new HttpHeaders();
        //headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>( HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/fare/{km}",method=RequestMethod.GET)
    public ResponseEntity<Integer> calcFare(@PathVariable("km") Integer dist){
    	Random r=new Random();
    	int costPerKm=r.nextInt(5)+8;
    	return new ResponseEntity<Integer>(costPerKm*dist,HttpStatus.OK);
    }
    
    // ------------------- Update a User ------------------------------------------------
 /*
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        logger.info("Updating User with id {}", id);
 
        User currentUser = userService.findById(id);
 
        if (currentUser == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());
 
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);
 
        User user = userService.findById(id);
        if (user == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Users-----------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        logger.info("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }*/
 
}
