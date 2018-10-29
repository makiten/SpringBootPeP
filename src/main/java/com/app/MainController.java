/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.app.business.PersonService;
import com.app.business.SecurityService;
import com.app.entity.Person;
import io.swagger.annotations.Api;
import java.util.Random;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@CrossOrigin(origins = "*", maxAge = 36000)  // must have thsi to avoid cros scripting erros if accessing directly.
@RestController    // using @RestController and not @Controller so we don't have to use @responsebody in our methods 
@RequestMapping(path="/customer") // This means URL's start with /demo (after Application path)
@Api(value="Customer Master", description="Operations pertaining to Customer Master Data")
public class MainController {
    
    @Autowired
    private PersonService personService;
	
    @Autowired
    private SecurityService securityService;
     
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);	 
    
    //public OutboundResponse ping(@RequestHeader(value="User-Agent") String userAgent, @RequestParam(value = "ID", defaultValue = "") String id, @RequestBody InboundRequest pinger)
    /*@RequestMapping(value = "/zing", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)  
    public OutboundResponse zing(@RequestParam String token, @RequestHeader(value="Origin") String origin, @RequestBody InboundRequest pinger) {
     logger.info("--zing--");
    return new OutboundResponse(token+": Springboot is up "+pinger.getInbound()+" from "+origin);
    }
    */
    
    @RequestMapping(value = "/ping", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)  
    public OutboundResponse ping() {
    Random random = new Random();
    return new OutboundResponse(String.valueOf(random.nextInt(1000)));
    }   
    
    @RequestMapping(value = "/getToken", method= RequestMethod.POST)  
	public OutboundResponse getToken (@RequestParam String acct, @RequestParam String routingno) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
         logger.debug("--getting token--");
            
	return new OutboundResponse(securityService.getToken(acct, routingno));
	}


    @RequestMapping(value = "/add", method= RequestMethod.POST)  
	public OutboundResponse addNewPerson (@RequestBody Person person) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
         logger.debug("--Adding person--");
            
	return new OutboundResponse(String.valueOf(personService.OnboardPerson(person)));
	}

    @RequestMapping(value = "/update", method= RequestMethod.PUT)  
	public OutboundResponse updatePerson (@RequestBody Person person) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
         logger.debug("--updating person--");
            
	return new OutboundResponse("true");
	}        
  
    @RequestMapping(value = "/getOne/{token}", method= RequestMethod.GET)
	public Person getThisUser(@PathVariable Integer token) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

        logger.debug("Inside getting on person with token: "+token);              
		return personService.getThisUser(token);
                
	}

    @RequestMapping(value = "/deleteOne/{token}", method= RequestMethod.DELETE)
        public OutboundResponse deleteThisUser(@PathVariable Integer token) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

        logger.debug("Inside deleteing a person ");              
	return new OutboundResponse("true");
	}
        
    @RequestMapping(value = "/getAll", method= RequestMethod.GET)
	public Iterable<Person> getAllUsers() {
		// This returns a JSON or XML with the users
        logger.error("Not really an error just testing");
		return personService.getAllUsers();
	}
        

}
