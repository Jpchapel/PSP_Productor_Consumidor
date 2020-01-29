/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productorConsumidor;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Almacen {

    private final int MAX_LIMITE = 20;
    private int producto = 0;
    private Semaphore productor = new Semaphore(MAX_LIMITE);
    private Semaphore consumidor = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public void producir(String nombreProductor) {
        System.out.println(nombreProductor + " intentando almacenar un producto");
        try {
            
                productor.acquire();
                mutex.acquire();

                producto++;
                System.out.println(nombreProductor + " almacena un producto. "
                        + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));
                mutex.release();

                Thread.sleep(500);
           
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            consumidor.release();
        }

    }

    public void consumir(String nombreConsumidor) {
        System.out.println(nombreConsumidor + " intentando retirar un producto");
        try {
            
                consumidor.acquire();
                mutex.acquire();

                producto--;
                System.out.println(nombreConsumidor + " retira un producto. "
                        + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));
                mutex.release();

                Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            productor.release();

        }
    }

}
