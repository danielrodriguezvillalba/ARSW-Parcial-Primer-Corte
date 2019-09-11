/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2115253
 */
public class Hilo extends Thread {

    BigInteger a, b;
    PrimesResultSet pr;
    boolean pausa;

    public Hilo(BigInteger a, BigInteger b, PrimesResultSet pr) {
        this.pr = pr;
        this.a = a;
        this.b = b;
        pausa = false;
    }

    @Override
    public void run() {
        if (!pausa) {
            PrimeFinder.findPrimes(a, b, pr);
        } else {
            pausar();
        }
    }
    
    public void setPausa(boolean pausa){
        this.pausa = pausa;
    }
    
    public void pausar() {
        if (pausa) {
            synchronized (PrimesFinderTool.monitor) {
                try {
                    PrimesFinderTool.monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
