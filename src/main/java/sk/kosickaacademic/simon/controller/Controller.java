package sk.kosickaacademic.simon.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {
    @RequestMapping("/{name}")
    public String printHello(@PathVariable String name){
        return "Sup " +name;
    }

    @RequestMapping(path = "/name/yourname", method = RequestMethod.POST)
    public String printName(@RequestBody String input) throws Exception{
        JSONObject obj = (JSONObject) new JSONParser().parse(input);
        return "Hey " +obj.get("name") +"! Your age is " +obj.get("age");
    }

    @RequestMapping("/time/current")
    public String printTime(){
        return new Date().toString();
    }
}
