package se.experis.tidsbankenbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    /* All methods return a response entity comprising of data and a HttpStatus code.
     Each Method also has a requirement to check whether or not the requested data is empty,
     and if it is then a HttpStatus.NO_CONTENT or HttpStatus.NOT_FOUND is sent in the response entity.*/
    @Autowired
    private UserRepository userRepository;
    //Retrieves all users from the User Table in the database, returning them in as a List within a Response Entity
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
    }
    //Returns a user by a specified Id from the User Table in the database, returning it as an Object within a Response Entity
    public ResponseEntity<User> getUserById(Long id) {
        var returnUser = new User();
        if(userRepository.existsById(id)){
            returnUser = userRepository.findById(id).get();
            return new ResponseEntity<>(returnUser,HttpStatus.OK);
        } else return new ResponseEntity<>(returnUser, HttpStatus.NOT_FOUND);
    }

    //Adds a new User object to the database, returning it as an Object within a Response Entity
    public ResponseEntity<User> addUser(User user){
        var returnUser = new User();
        if(user != null) {
            returnUser = userRepository.save(user);
            return new ResponseEntity<>(returnUser, HttpStatus.CREATED);
        } else return new ResponseEntity<>(returnUser, HttpStatus.NO_CONTENT);
    }
    //Updates a user with a User Object, returning it as an Object within a Response Entity
    public ResponseEntity<User> updateUser(User user) {
        var returnUser = new User();
        if(!userRepository.existsById(user.getId())){
            return new ResponseEntity<>(returnUser,HttpStatus.BAD_REQUEST);
        }
        returnUser = userRepository.save(user);
        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }
    //Deletes a specified user based upon their id, returning the id within a Response Entity
    public ResponseEntity<Long> deleteUser(Long id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }else return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }
}
