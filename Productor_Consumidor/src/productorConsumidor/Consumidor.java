/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package productorConsumidor;

/**
 *
 * @author Stream
 */
public class Consumidor extends Thread{
    private Almacen almacen;

    public Consumidor(String name, Almacen almacen) {
        super(name);
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while(true){
            almacen.consumir(this.getName());
        }
    }
}
