package pl.coderslab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String saveToDb(User user){
        if(userRepository.returnIdWhenNameFound(user.getName()) != user.getId()){
            return "name";
        }
        if(userRepository.returnIdWhenEmailFound(user.getEmail()) != user.getId()){
            return "email";
        }

        userRepository.save(user);

        return "ok";
    }
}
