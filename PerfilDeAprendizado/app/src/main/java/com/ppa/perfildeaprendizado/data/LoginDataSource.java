package com.ppa.perfildeaprendizado.data;

import com.ppa.perfildeaprendizado.data.model.Aluno;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<Aluno> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            Aluno fakeUser =
                    new Aluno(
                            java.util.UUID.randomUUID().toString(),
                            "1622333GGG", "Jane Doe", "jane_doe@gmail.com", "BCC", 20, "Feminino", "123445");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
