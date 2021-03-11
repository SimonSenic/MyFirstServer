package sk.kosickaacademic.simon.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@RestController
public class JokeController {
    ArrayList<String> arr = new ArrayList<>();

    public JokeController() {
        arr.add("Where does Batman go to the bathroom? The batroom");
        arr.add("How can you make God laugh? Tell him your plans.");
        arr.add("What type of sandals do frogs wear? Open-toad.");
    }

    @RequestMapping("/jokes")
    public ResponseEntity<String> printJokes(){
        JSONArray jArr = new JSONArray();
        for(String temp : arr) jArr.add(temp);
        JSONObject jObj = new JSONObject();
        jObj.put("jokes", jArr);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jObj.toJSONString());
    }

    @RequestMapping("/joke")
    public ResponseEntity<String> printRandomJoke(){
        int i = new Random().nextInt(arr.size());
        JSONObject jObj = new JSONObject();
        jObj.put("joke", arr.get(i));
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jObj.toJSONString());
    }

    @RequestMapping("/joke/{n}")
    public ResponseEntity<String> printJoke(@PathVariable int n){
        JSONObject jObj = new JSONObject();
        if(n<1 || n>arr.size()){
            jObj.put("error", "Invalid joke.");
            return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(jObj.toJSONString());
        }
        jObj.put("joke", arr.get(n-1));
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(jObj.toJSONString());
    }

    @RequestMapping(path = "/joke/add", method = RequestMethod.POST)
    public ResponseEntity<String> addJoke(@RequestBody String input){
        try {
            JSONObject obj = (JSONObject) new JSONParser().parse(input);
            String joke = String.valueOf(obj.get("joke"));
            JSONObject jObj = new JSONObject();
            int status;
            if(joke!=null && !joke.equals("") && joke.length()>7 && joke.length()<100){
                arr.add(joke);
                jObj.put("message", "Joke added successfully."); status = 200;
            }else{
                jObj.put("error", "Invalid joke format."); status = 400;
            }
            return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(jObj.toJSONString());
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
