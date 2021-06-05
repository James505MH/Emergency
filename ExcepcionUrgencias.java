//package sistemaUrgencias; // agregar al paquete

/**
 * Excepcion Especıfica de la Aplicacion
 *
 * @author Jaime Madrigal
 * @version 22/05/21
 */

public class ExcepcionUrgencias extends RuntimeException {
   /**
    * Constructor por defecto
    */
    public ExcepcionUrgencias () {
       super(" Error : Violacion del sistema de Urgencias ");
    }
    /**
     * Constructor que acepta un mensaje de error
     */
    public ExcepcionUrgencias ( String mensaje ) {
       super( mensaje );
    }
}