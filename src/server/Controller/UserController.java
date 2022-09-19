package server.Controller;

import com.request_response.Response;
import common.entity.User;
import common.entity.Users;
import server.RequestHandler;

import java.util.Map;
import java.util.Optional;

public class UserController implements IController {
    @Override
    public void addMappings() {
        RequestHandler.addMappin("/login", request -> {

            Response response = new Response();
            Map<String, Object>requestDataMap =   request.getData();

            String userName = requestDataMap.get("userName").toString();
            String password = requestDataMap.get("password").toString();

            Users users = new Users();
            Optional<User> user = users.getUserList().stream()
                                        .filter(usr -> usr.getUserName().equals(userName) && usr.getPassword().equals(password))
                                        .findFirst();
            if(user.isPresent()){
                response.setCode(200);
                response.setData(user.get());
                response.setMessage("Success");
            }
            else{
                response.setCode(404);
                response.setMessage("User not found");
            }

           return response;
        });
    }
}
