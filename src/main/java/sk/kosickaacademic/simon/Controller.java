package sk.kosickaacademic.simon;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {
    @RequestMapping("/{name}")
    public String printHello(@PathVariable String name){
        return "Sup " +name;
    }

    @RequestMapping(path = "/name/yourname", method = RequestMethod.POST)
    public String printName(@RequestBody String name){
        return "Hey " +name +"!";
    }

    @RequestMapping("/time/current")
    public String printTime(){
        return new Date().toString();
    }
}
