/**
 * Esta clase es para guardar los detalles de un solo cuarto 
 * 
 * @author Jaime Madrigal
 * @version 22/05/21
 */

//package sistemaUrgencias; // agregar la clase al paquete

import java.io.Serializable;

public class Cuarto implements Serializable {
   //atributos
   private int numero; 
   private boolean estadoCuarto; 

   /**
    * Constructor pone el numero de cuarto
    * @param numeroEn Usado para poner el numero de cuarto
    */
   public Cuarto(int numeroEn) {
      if (numeroEn < 1) {
         throw new ExcepcionUrgencias ("Numero de cuartos invalido");
      }
      numero = numeroEn;
      estadoCuarto = false;  // inicialmente el cuarto esta desocupado 
   }

   /**
    * Regresa el numero de cuarto 
    *
    */
   public int getNumero() {
      return numero;
   }

   /**
    * Revisar si el cuarto esta ocupado
    */
   public boolean verEstado() {
      return estadoCuarto;
   }

   /** 
    * Cambiar el estado del cuarto
    */
   public void ocupar() {
      estadoCuarto = true;
   }
 
   /**
    * Cambiar el estado del cuarto
    */
   public void desocupar() {
      estadoCuarto = false;
   }
}