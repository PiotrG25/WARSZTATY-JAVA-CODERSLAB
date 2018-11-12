package pl.coderslab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    public User loadByName(String name){
        return userRepository.findUserByName(name);
    }

    public String saveToDb(User user){
        if(userRepository.returnIdWhenNameFound(user.getName()) != user.getId()){
            //Error when there is user with that name
            return "name";
        }
        if(userRepository.returnIdWhenEmailFound(user.getEmail()) != user.getId()){
            //Error when there is user with that email
            return "email";
        }

        if(user.getId() == 0){
            entityManager.persist(user);
        }else{
            entityManager.merge(user);
        }

        //return something
        return "ok";
    }
}
