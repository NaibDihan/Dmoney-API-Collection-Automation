import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserController extends Setup {
    public UserController() throws IOException {
        initConfig();
    }
    public void doLogin(String email, String password) throws ConfigurationException {
        RestAssured.baseURI= prop.getProperty("baseUrl");
        UserModel model=new UserModel();
        model.setEmail(email);
        model.setPassword(password);
        Response res=given().contentType("application/json").body(model).post("/user/login");
        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
        String token= jsonPath.get("token");
        System.out.println(token);
        Utils.setEvnVar("token",token);

    }
    public JsonPath searchUser(String userId) throws IOException {
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=given().contentType("application/json").header("Authorization","Bearer "+((prop.getProperty("token"))))
                .when().get("/user/search/id/"+userId);
        return res.jsonPath();
    }
    public JsonPath createUser(UserModel model) throws ConfigurationException {
        RestAssured.baseURI= prop.getProperty("baseUrl");
        Response res=given().contentType("application/json").body(model)
                .header("Authorization","Bearer "+((prop.getProperty("token"))))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .when().post("/user/create");
        System.out.println(prop.getProperty("token"));
        System.out.println(res.asString());
        return res.jsonPath();


    }
    public void systemToAgentTrnx(String agentPhoneNumber){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        Response res=given().contentType("application/json").body("{\n" +
                        "  \"from_account\": \"SYSTEM\",\n" +
                        "  \"to_account\": \""+agentPhoneNumber+"\",\n" +
                        "  \"amount\": 2000\n" +
                        "}")
                .header("Authorization","Bearer "+((prop.getProperty("token"))))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .when().post("/transaction/deposit");
        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(message);
        System.out.println(agentPhoneNumber);
    }

    public void customerToAgentTrnx(String customerPhoneNumber, String agentPhoneNumber){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        Response res=given().contentType("application/json").body("{\n" +
                        "  \"from_account\": \""+agentPhoneNumber+"\",\n" +
                        "  \"to_account\": \""+customerPhoneNumber+"\",\n" +
                        "  \"amount\": 1500\n" +
                        "}")
                .header("Authorization","Bearer "+((prop.getProperty("token"))))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .when().post("/transaction/deposit");
        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(message);
        System.out.println(customerPhoneNumber);
        System.out.println(agentPhoneNumber);
    }

    public void customerToAgentWithdraw(String customerPhoneNumber, String agentPhoneNumber){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        Response res=given().contentType("application/json").body("{\n" +
                        "  \"from_account\": \""+customerPhoneNumber+"\",\n" +
                        "  \"to_account\": \""+agentPhoneNumber+"\",\n" +
                        "  \"amount\": 500\n" +
                        "}")
                .header("Authorization","Bearer "+((prop.getProperty("token"))))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .when().post("/transaction/withdraw");
        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(message);
        System.out.println(customerPhoneNumber);
        System.out.println(agentPhoneNumber);
    }

    public void customerToCustomerSendMoney(String customerPhoneNumber1, String customerPhoneNumber2){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        Response res=given().contentType("application/json").body("{\n" +
                        "  \"from_account\": \""+customerPhoneNumber1+"\",\n" +
                        "  \"to_account\": \""+customerPhoneNumber2+"\",\n" +
                        "  \"amount\": 500\n" +
                        "}")
                .header("Authorization","Bearer "+((prop.getProperty("token"))))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .when().post("/transaction/sendmoney");
        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(message);
        System.out.println(customerPhoneNumber1);
        System.out.println(customerPhoneNumber2);
    }

    public void customerToMarchantPayment(String customerPhoneNumber1, String marchantPhoneNumber){
        RestAssured.baseURI= prop.getProperty("baseUrl");

        Response res=given().contentType("application/json").body("{\n" +
                        "  \"from_account\": \""+customerPhoneNumber1+"\",\n" +
                        "  \"to_account\": \""+marchantPhoneNumber+"\",\n" +
                        "  \"amount\": 100\n" +
                        "}")
                .header("Authorization","Bearer "+((prop.getProperty("token"))))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .when().post("/transaction/payment");
        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
        String message = jsonPath.get("message");
        System.out.println(message);
        System.out.println(customerPhoneNumber1);
        System.out.println(marchantPhoneNumber);
    }

    public void customerCheckBalance(String customerNumber1){
        RestAssured.baseURI=prop.getProperty("baseUrl");
        Response res=given().contentType("application/json")
                .header("Authorization","Bearer "+((prop.getProperty("token"))))
                .header("X-AUTH-SECRET-KEY",prop.getProperty("partnerKey"))
                .when().get("/transaction/balance/"+customerNumber1);
        System.out.println(res.asString());
        JsonPath jsonPath=res.jsonPath();
        String message = jsonPath.get("message");
        int balance = jsonPath.get("balance");
        System.out.println(message+": " +balance);
    }
    



}
