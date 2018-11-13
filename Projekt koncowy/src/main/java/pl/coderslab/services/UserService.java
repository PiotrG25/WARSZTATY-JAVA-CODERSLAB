package pl.coderslab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    public String saveToDb(User user){
        if(userRepository.returnIdWhenNameFound(user.getName()) != user.getId()){
            return "name";
        }
        if(userRepository.returnIdWhenEmailFound(user.getEmail()) != user.getId()){
            return "email";
        }

        if(user.getId() == null){
            entityManager.persist(user);
        }else{
            entityManager.merge(user);
        }
        return "ok";
    }
}
