package club.pard.server.seminar03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import club.pard.server.seminar03.dto.ResponseDto;
import club.pard.server.seminar03.dto.SignInDto;
import club.pard.server.seminar03.dto.SignUpDto;
import club.pard.server.seminar03.entity.UserEntity;
import club.pard.server.seminar03.repository.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){ this.userRepository = userRepository; }

    public ResponseDto<UserEntity> signUp(SignUpDto dto)
    {
        UserEntity user = new UserEntity(dto);
        String userEmail = dto.getUserEmail();
        try
        {
            if(userRepository.existsByUserEmail(userEmail))
            {
                return ResponseDto.setFailure("Existent ID");
            }
            userRepository.save(user);
            return ResponseDto.setSuccess("You made it!", user);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("DB Error");
        }
    }

    public ResponseDto<List<UserEntity>> findAll()
    {
        List<UserEntity> users;
        try
        {
            users = userRepository.findAll();
            return ResponseDto.setSuccess("Successfully retrieved", users);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("DB Error");
        }
    }

    public ResponseDto<UserEntity> findOne(Integer userNum)
    {
        UserEntity user;
        try
        {
            user = userRepository.findById(userNum).get();
            return ResponseDto.setSuccess("Successfully found", user);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseDto.setFailure("DB Error");
        }
    }

    @Transactional
    public ResponseDto<UserEntity> update(Integer userNum, SignUpDto dto)
    {
        UserEntity user;
        try
        {
            user = userRepository.findById(userNum).get();
            if(dto.getUserEmail() != null && !dto.getUserEmail().isEmpty()) user.setUserEmail(dto.getUserEmail());
            if(dto.getUserPassword() != null && !dto.getUserPassword().isEmpty()) user.setUserPassword(dto.getUserPassword());

            return ResponseDto.setSuccess("Successfully updated", user);
        }
        catch(Exception e)
        {
            return ResponseDto.setFailure("DB Error");
        }
    }

    public ResponseDto<?> delete(Integer userNum)
    {
        try
        {
            if(userRepository.existsById(userNum))
            {
                userRepository.deleteById(userNum);
                return ResponseDto.setSuccess("Successfully Removed", null);
            }
            else return ResponseDto.setFailure("Non-existen Id");
        }
        catch(Exception e)
        {
            return ResponseDto.setFailure("DB Error");
        }
    }

    ///

    public ResponseDto<String> signIn(SignInDto dto)
    {
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        boolean existed = userRepository.existsByUserEmailAndUserPassword(userEmail, userPassword);
        if(!existed) return ResponseDto.setFailure("Non-existent account");
        else return ResponseDto.setSuccess("Log-in authorized.", null);
    }
}
