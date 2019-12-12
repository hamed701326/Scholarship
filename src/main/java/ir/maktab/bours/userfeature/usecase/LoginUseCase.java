package ir.maktab.bours.userfeature.usecase;

import ir.maktab.bours.model.User;

public interface LoginUseCase {
    User login(String username, String password);
}
