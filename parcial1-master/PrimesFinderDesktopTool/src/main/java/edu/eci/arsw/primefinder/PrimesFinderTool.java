package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

    static Object monitor;

    public static void main(String[] args) {
        monitor = new Object();
        int maxPrim = 1000;

        PrimesResultSet prs = new PrimesResultSet("john");

        List<Hilo> hilos = new ArrayList<>();

        Hilo h1 = new Hilo(new BigInteger("1"), new BigInteger("2500"), prs);
        Hilo h2 = new Hilo(new BigInteger("2501"), new BigInteger("5000"), prs);
        Hilo h3 = new Hilo(new BigInteger("5001"), new BigInteger("7500"), prs);
        Hilo h4 = new Hilo(new BigInteger("7501"), new BigInteger("10000"), prs);

        hilos.add(h1);
        hilos.add(h2);
        hilos.add(h3);
        hilos.add(h4);

        for (Hilo h : hilos) {
            h.start();
        }

        boolean flag = false;
        int i = 0;
        while (i < 10000) {
            try {
                //check every 10ms if the idle status (10 seconds without mouse
                //activity) was reached. 
                Thread.sleep(10);
                if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement() > 10000) {
                    if (flag) {
                        for (Hilo hilo : hilos) {
                            hilo.setPausa(false);
                            synchronized (monitor) {
                                monitor.notifyAll();
                            }
                        }
                        flag = false;
                    }

                } else {
                    if (!flag) {
                        for (Hilo hilo : hilos) {
                            hilo.setPausa(true);
                        }
                        flag = true;
                    }
                }
                i++;
            } catch (InterruptedException ex) {
                Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Prime numbers found:");

        System.out.println(prs.getPrimes());
    }

}
