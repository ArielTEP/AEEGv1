package edu.lips.espindola.aeeg.views.login.repository;

import edu.lips.espindola.aeeg.domain.ConnectionTask;

/**
 * Created by Ariel on 9/20/16.
 */
public interface LoginRepository {
    void signUp(String name,String lastName,
                String email, String password,
                String userType, int college);
    void signIn(String email, String password);
    void checkSession();
}
