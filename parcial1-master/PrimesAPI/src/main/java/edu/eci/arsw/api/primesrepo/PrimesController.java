package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{
    
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = GET)
    public List<FoundPrime> getPrimes()
    {
        return primeService.getFoundPrimes();
    }
    
    @RequestMapping( value = "/primes/{primenumber}", method = GET)
    public FoundPrime getPrimes(@PathVariable("primenumber") String number)
    {
        return primeService.getPrime(number);
    }
    
    
    @RequestMapping( value = "/primes", method = POST)
    public void postPrime(@RequestBody FoundPrime fp)
    {
        primeService.addFoundPrime(fp);
    }
    //TODO implement additional methods provided by PrimeService



}
