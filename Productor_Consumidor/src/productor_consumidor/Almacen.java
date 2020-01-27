/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productor_consumidor;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stream
 */
public class Almacen {

    private final int MAX_LIMITE = 20;
    private int producto = 0;
    private Semaphore semaforoProductor = new Semaphore(MAX_LIMITE);
    private Semaphore semaforoConsumidor = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    public void producir(String nombreProductor) {
        System.out.println(nombreProductor + " intentando almacenar un producto");
        try {
            
                semaforoProductor.acquire();
                mutex.acquire();

                producto++;
                System.out.println(nombreProductor + " almacena un producto. "
                        + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));
                mutex.release();

                Thread.sleep(500);
           
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            semaforoConsumidor.release();
        }

    }

    public void consumir(String nombreConsumidor) {
        System.out.println(nombreConsumidor + " intentando retirar un producto");
        try {
            
                semaforoConsumidor.acquire();
                mutex.acquire();

                producto--;
                System.out.println(nombreConsumidor + " retira un producto. "
                        + "Almacén con " + producto + (producto > 1 ? " productos." : " producto."));
                mutex.release();

                Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            semaforoProductor.release();

        }
    }

}
