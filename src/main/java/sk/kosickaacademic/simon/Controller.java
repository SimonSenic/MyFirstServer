package sk.kosickaacademic.simon;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Controller {
    @RequestMapping("/{name}")
    public String printHello(@PathVariable String name){
        return "Sup " +name;
    }

    @RequestMapping("/time")
    public String printTime(){
        return new Date().toString();
    }
}
