package sk.kosickaacademic.simon.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathController {
    @RequestMapping(path = "/example/calc", method = RequestMethod.POST)
    public ResponseEntity<String> calcExample(@RequestBody String input){
        try{
            JSONObject obj = (JSONObject) new JSONParser().parse(input);
            int a = Integer.parseInt(String.valueOf(obj.get("a")));
            int b = Integer.parseInt(String.valueOf(obj.get("b")));
            int result = a*b;
            JSONObject objResult = new JSONObject();
            objResult.put("result", result);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(objResult.toJSONString());
        }catch (ParseException e){
            e.printStackTrace();
        }catch (NumberFormatException e){
            JSONObject objError = new JSONObject();
            objError.put("error", "Invalid request body");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(objError.toJSONString());
        }
        return null;
    }

    @GetMapping("/example/calc/alt")
    public ResponseEntity<String> calcExampleAlt(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b){
        int result = a*b;
        JSONObject objResult = new JSONObject();
        objResult.put("result", result);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(objResult.toJSONString());
    }
}
