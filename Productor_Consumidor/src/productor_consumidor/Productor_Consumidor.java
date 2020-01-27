/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productor_consumidor;

/**
 *
 * @author Stream
 */
public class Productor_Consumidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PRODUCTORES = 3;
        final int CONSUMIDORES = 10;

        Almacen almacen = new Almacen();
        
        for (int i = 0; i < PRODUCTORES; i++) {
            new Productor("Productor " + i, almacen).start();
        }
        
        for (int i = 0; i < CONSUMIDORES; i++) {
            new Consumidor("Consumidor " + i, almacen).start();
        }
    }
    
}
