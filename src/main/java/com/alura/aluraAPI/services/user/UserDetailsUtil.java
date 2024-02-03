package com.alura.aluraAPI.services.user;

import com.alura.aluraAPI.models.person.TypeRole;
import com.alura.aluraAPI.models.person.User;
import com.alura.aluraAPI.projections.UserDetailsProjection;
import com.alura.aluraAPI.services.exceptions.ValidationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsUtil {
    public static UserDetails load(User user, List<UserDetailsProjection> result) {
        setAttributesInUser(result, user);
        validateStudent(user);
        return user;
    }

    private static User setAttributesInUser(List<UserDetailsProjection> result, User user) {
        user.setEmail(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        user.setIsAccountNonLocked(result.get(0).getIs_Non_Locked());
        user.setIsAccountNonExpired(result.get(0).getIs_Non_Expired());
        user.setIsCredentialsNonExpired(result.get(0).getIs_Credentials_Non_Expired());
        user.setIsEnabled(result.get(0).getIs_Enabled());
        for (UserDetailsProjection projection : result) {
            user.addRole(new TypeRole(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }

    private static void validateStudent(User user) {
        if (!user.getIsAccountNonExpired() || !user.getIsAccountNonLocked() || !user.getIsEnabled() || !user.getIsCredentialsNonExpired())
            throw new ValidationException("Account unreachable");
    }
}
