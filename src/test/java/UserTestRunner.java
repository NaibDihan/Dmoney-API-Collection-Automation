import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.apache.commons.configuration.ConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner extends Setup {
    @Test(priority = 1)
    public void doLogin() throws IOException, ConfigurationException {
        UserController userController=new UserController();
        userController.doLogin("admin@roadtocareer.net","1234");
    }
    @Test(priority = 2)
    public void createUserCustomer1() throws IOException, ConfigurationException, ParseException {
        Faker faker=new Faker();
        UserController userController=new UserController();
        UserModel model=new UserModel();
        model.setName(faker.name().fullName());
        model.setEmail(faker.internet().emailAddress().toLowerCase());
        model.setPassword("P@ssword123");
        String phoneNumber="01502"+Utils.generateRandomId(100000,999999);
        model.setPhone_number(phoneNumber);
        model.setNid(String.valueOf(Utils.generateRandomId(100000000,999999999)));
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Define user role|| Press A for Agent or Press C for Customer");
//        String role = sc.nextLine();
//        if(role.contains("A")){
//            model.setRole("Agent");
//        } else if (role.contains("C")) {
//            model.setRole("Customer");
//        }
        model.setRole("Customer");
        JsonPath jsonPath= userController.createUser(model);
        int userId= jsonPath.get("user.id");
        Utils.setEvnVar("userId",String.valueOf(userId));
        Utils.saveUsers(model);
    }
    @Test(priority = 3)
    public void createUserCustomer2() throws IOException, ConfigurationException, ParseException {
        Faker faker=new Faker();
        UserController userController=new UserController();
        UserModel model=new UserModel();
        model.setName(faker.name().fullName());
        model.setEmail(faker.internet().emailAddress().toLowerCase());
        model.setPassword("P@ssword123");
        String phoneNumber="01502"+Utils.generateRandomId(100000,999999);
        model.setPhone_number(phoneNumber);
        model.setNid(String.valueOf(Utils.generateRandomId(100000000,999999999)));
        model.setRole("Customer");
        JsonPath jsonPath= userController.createUser(model);
        int userId= jsonPath.get("user.id");
        Utils.setEvnVar("userId",String.valueOf(userId));
        Utils.saveUsers(model);
    }
    @Test(priority = 4)
    public void createUserAgent() throws IOException, ConfigurationException, ParseException {
        Faker faker=new Faker();
        UserController userController=new UserController();
        UserModel model=new UserModel();
        model.setName(faker.name().fullName());
        model.setEmail(faker.internet().emailAddress().toLowerCase());
        model.setPassword("P@ssword123");
        String phoneNumber="01502"+Utils.generateRandomId(100000,999999);
        model.setPhone_number(phoneNumber);
        model.setNid(String.valueOf(Utils.generateRandomId(100000000,999999999)));
        model.setRole("Agent");
        JsonPath jsonPath= userController.createUser(model);
        int userId= jsonPath.get("user.id");
        Utils.setEvnVar("userId",String.valueOf(userId));
        Utils.saveUsers(model);
    }

    String agentPhoneNumber="";
    String customerPhoneNumber1="";
    String customerPhoneNumber2="";
    String marchantPhoneNumber="";
    @Test(priority = 6)
    public void findUserNumber() throws IOException, ParseException {
        JSONArray userArray = Utils.getUser();
        for(Object userObj:userArray){
            JSONObject user = (JSONObject) userObj;
            String role =(String) user.get("role");
            if("Agent".equals(role)){
                agentPhoneNumber = (String) user.get("phone_number");
                break;
            }
        }
        for(Object userObj:userArray){
            JSONObject user = (JSONObject) userObj;
            String role =(String) user.get("role");
            if("Customer".equals(role)){
                customerPhoneNumber1 = (String) user.get("phone_number");
                break;
            }
        }

        for(Object userObj:userArray){
            JSONObject user = (JSONObject) userObj;
            String role =(String) user.get("role");
            if ("Customer".equals(role)) {
                customerPhoneNumber2 = (String) user.get("phone_number");

                if (!customerPhoneNumber2.equals(customerPhoneNumber1)) {
                    break;
                }
            }
        }

        for(Object userObj:userArray){
            JSONObject user = (JSONObject) userObj;
            String role =(String) user.get("role");
            if("Marchant".equals(role)){
                marchantPhoneNumber = (String) user.get("phone_number");
                break;
            }
        }


    }
    @Test(priority = 7)
    public void systemToAgentTrnx() throws IOException, ParseException {
        UserController userController=new UserController();
        userController.systemToAgentTrnx(agentPhoneNumber);

    }

    @Test(priority = 8)
    public void customerToAgentTrnx() throws IOException, ParseException {
        UserController userController=new UserController();
        userController.customerToAgentTrnx(customerPhoneNumber1,agentPhoneNumber);

    }

    @Test(priority = 9)
    public void customerToAgentWithdraw() throws IOException, ParseException {
        UserController userController=new UserController();
        userController.customerToAgentWithdraw(customerPhoneNumber1,agentPhoneNumber);

    }
    @Test(priority = 10)
    public void customerToCustomerSendMoney() throws IOException, ParseException {
        UserController userController = new UserController();
        userController.customerToCustomerSendMoney(customerPhoneNumber1,customerPhoneNumber2);
    }
    @Test(priority = 11)
    public void customerToMarchantPayment() throws IOException, ParseException {
        UserController userController = new UserController();
        //String marchantPhoneNumber = (String) "01686606905";
        userController.customerToMarchantPayment(customerPhoneNumber1,"01686606905");
    }
    @Test(priority = 12)
    public void customerCheckBalance() throws IOException, ParseException {
        UserController userController = new UserController();
        userController.customerCheckBalance(customerPhoneNumber1);
    }
}
