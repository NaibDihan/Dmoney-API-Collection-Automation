import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static void setEvnVar(String key, String value) throws ConfigurationException {
        PropertiesConfiguration config=new PropertiesConfiguration("./src/test/resources/config.properties");
        config.setProperty(key,value);
        config.save();
    }
    public static int generateRandomId(int min, int max){
        double random= Math.random()*(max-min)+min;
        int randId= (int) random;
        return randId;

    }
    public static void saveUsers(UserModel model) throws IOException, ParseException {
        String filelocation = "./src/test/resources/users.json";

        JSONParser parser = new JSONParser();
        JSONArray userArray = (JSONArray) parser.parse(new FileReader(filelocation));
        JSONObject userObj = new JSONObject();
        userObj.put("name",model.getName());
        userObj.put("email",model.getEmail());
        userObj.put("password",model.getPassword());
        userObj.put("phone_number",model.getPhone_number());
        userObj.put("NID",model.getNid());
        userObj.put("role",model.getRole());

        userArray.add(userObj);

        FileWriter writer = new FileWriter(filelocation);
        writer.write(userArray.toJSONString());
        writer.flush();
        writer.close();

    }

    public static JSONArray getUser() throws IOException, ParseException {
        String filelocation = "./src/test/resources/users.json";
        JSONParser parser = new JSONParser();
        JSONArray userArray = (JSONArray) parser.parse(new FileReader(filelocation));
        return userArray;

    }

}
