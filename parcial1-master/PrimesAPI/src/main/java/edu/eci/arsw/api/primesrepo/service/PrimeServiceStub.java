package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import javafx.beans.binding.ListBinding;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
public class PrimeServiceStub implements PrimeService
{   
    List<FoundPrime> primes = new ArrayList<FoundPrime>();
    
    @Override
    public void addFoundPrime( FoundPrime foundPrime )
    {
        primes.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        return primes;
    }

    @Override
    public FoundPrime getPrime( String prime )
    {
        FoundPrime temp = null;
        for (FoundPrime prime1 : primes) {
            if(prime1.getPrime() == prime){
                temp = prime1;
            }
        }
        return temp;
    }
}
