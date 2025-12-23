package model.lombok;

import lombok.Data;

@Data
public class CreateUserBodyLombokModel {

    int id, userStatus;
    String username, firstName, lastName, email, password, phone;


}