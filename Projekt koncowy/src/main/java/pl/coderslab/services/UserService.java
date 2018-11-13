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
        Long tempId = userRepository.returnIdWhenNameFound(user.getName());
        if(tempId != user.getId() && tempId != null){
            return "name";
        }
        tempId = userRepository.returnIdWhenEmailFound(user.getEmail());
        if(tempId != user.getId() && tempId != null){
            return "email";
        }

        userRepository.save(user);

        return "ok";
    }
}
