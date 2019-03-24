package controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class ReadJsonFile {

    public JSONObject readJson(String filePath) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            Object object = jsonParser.parse(new FileReader(filePath+".json"));
            jsonObject = (JSONObject) object;
        } catch (FileNotFoundException fo) {
            fo.printStackTrace();
        } catch (Exception pe) {
            pe.printStackTrace();
        }

        return jsonObject;
    }

    public String getJsonContentInString(String filePath) {
        JSONObject jsonObject = readJson(filePath);
        return jsonObject.toJSONString();
    }

    public String getJsonContentInString(JSONObject jsonObject) {
        return jsonObject.toJSONString();
    }
}
