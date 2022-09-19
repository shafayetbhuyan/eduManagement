package common.entity;

import java.util.ArrayList;
import java.util.List;

public class Users{

    public List<User> userList;

    public Users(){
        userList = new ArrayList<>();
        userList.add(new User(1,"Mohammad Nadim","CSE","Amazing Teacher", "nadim", "1234", 0));
        userList.add(new User(10,"Hossain Abir","CSE","Average Student", "abir", "1234", 1));
        userList.add(new User(11,"Abdullah Noyeem","CSE","Hard-working", "noyeem", "1234", 1));
        userList.add(new User(12,"Mamun Bhuyan","CSE","Good Student", "mamun", "1234", 1));
        userList.add(new User(13,"Akbor Hossain","CSE","Brilliant", "akbor", "1234", 1));
        userList.add(new User(14,"Jahed Hasan","CSE","Brilliant", "jahed", "1234", 1));
        userList.add(new User(15,"Anamul Hoque","CSE","Brilliant", "anamul", "1234", 1));
        userList.add(new User(16,"Nawsheen Ahmed Chowdhury","CSE","Brilliant", "nawsheen", "1234", 1));
        userList.add(new User(15,"Sharmin Akter","CSE","Brilliant", "sharmin", "1234", 1));
    }

    public List<User> getUserList() {
        return userList;
    }
}
