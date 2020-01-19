package edu.note.noteservice.security;

import com.google.gson.Gson;
import edu.note.noteservice.service.UserStorageRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthServiceImpl implements AuthService {


    private UserStorageRepository userStorageRepository;

    public AuthServiceImpl(UserStorageRepository userStorageRepository) {
        this.userStorageRepository = userStorageRepository;
    }

    @Override
    public boolean authenticate(String token) {
        User user = parseJwtToken(token);
        return userStorageRepository.getById(user.getId()) != null;
    }

    private User parseJwtToken(String token) {
        String[] split_string = token.split("\\.");
        String base64EncodedBody = split_string[1];
        String body = new String(Base64.getDecoder().decode(base64EncodedBody));
        return convertJsonToUser(body);
    }

    private User convertJsonToUser(String body) {
        Gson gson = new Gson();
        return gson.fromJson(body, User.class);
    }
}
