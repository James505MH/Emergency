/** 
 * Esta clase guarda los detalles de un solo paciente
 * 
 * @autor Jaime Madrigal 
 * @version 22/05/21 
 */

//package sistemaUrgencias; // agregar la clase al paquete

import java.io.Serializable;

public class Paciente implements Serializable {
    // atributos
    private String numeroCuarto;
    private String nombrePaciente;
    private int edad; 
    private double costoConsulta;
    private double medicamentos; 
    private EstadoPaciente estadoPcte; 
    private EstadoPaciente condicion;
    private Cuarto cuarto; // implementar asociacion a Cuarto
    
    // metodos
    
    /**
     * Constructos pone los datos iniciales del paciente para su registro
     * 
     * @param nombrePacienteEn El nombre del paciente a registrar
     * @param edadEn La edad del paciente a registrar
     * @param costos Los gastos aplicados a la consulta
     * @param medicamentosEn Extras a los gastos sobre la consulta
     */
    public Paciente (String nombrePacienteEn, int edadEn, 
                        double costos, double medicamentosEn) { 
        nombrePaciente = nombrePacienteEn;
        edad = edadEn;
        costoConsulta = costos;
        medicamentos = medicamentosEn;
        estadoPcte = EstadoPaciente.ESPERANDO; // estado inicial puesto a ESPERANDO
        condicion = EstadoPaciente.REGULAR; // estado de salud puesto a REGULAR
        cuarto = null; // indica que no se ha asignado cuarto
    }
     
    /** 
     * Devolver el numero de paciente de la persona
     */
    public String getNombre() {
        return nombrePaciente;
    }
    
    /**
     * Devolver la edad del paciente 
     */
    public int getEdad() {
        return edad;
    }
    
    /** 
     * Devuelve la condicion del paciente
     */
    public EstadoPaciente getCondicion() {
       return condicion;
    }
    
    /** 
     * Devuelve el estado del paciente
     */
    public EstadoPaciente getEstado() {
       return estadoPcte;
    }

    /** 
     * Permite escribir el atributo costoConsulta
     */
    public void setCostoConsulta(double costoConsultaEn) {
       costoConsulta = costoConsultaEn;
    }

    /** 
     * Devuelve el costo de consulta del paciente
     */
    public double getCostoConsulta() {
       return costoConsulta;
    }

    /** 
     * Permite escribir el atributo medicamentos
     */
    public void setMedicamentos(double medicamentosEn) {
       medicamentos = medicamentosEn;
    }

    /** 
     * Devuelve el costo de medicamentos del paciente
     */
    public double getMedicamentos() {
       return medicamentos;
    }

    /**
     * Devuelve el numero de cuarto ocupado por el paciente
     * @throws ExcepcionUrgencias si el cuarto esta vacio
     */
    public int getNumeroCuarto() {
       if (cuarto == null) {
          throw new ExcepcionUrgencias ("Cuarto" + numeroCuarto +
                                     "No esta ocupada");
       }
       return cuarto.getNumero();
    }

    /**
     * Asignar un cuarto al Paciente
     * 
     * @throws ExcepcionUrgencias si el parametro cuarto es null 
     *                            o si el cuarto esta ocupado
     */
    public void asignarCuarto(Cuarto cuartoEn) throws ExcepcionUrgencias {
        if (cuarto == null) {
            throw new ExcepcionUrgencias ("no hay cuarto para asignar");
        }
        cuarto = cuartoEn;
        cuarto.ocupar();
    }

    /**
    * Revisa si el paciente tiene cuarto asignado
    * @return    Devuelve true si el paciente tiene un cuarto asignado
    *            y otro caso false
    */
   public boolean estaAsignadoCuarto() {
      return cuarto!=null;
   }

    /**
     * Desocupar el cuarto actual
     * 
     * @throws ExcepcionUrgencias si no hay cuarto ocupado
     */
    public void desocuparCuarto() { 
       if (cuarto == null) {
          throw new ExcepcionUrgencias ("El cuarto no esta ocupado");
       }
       cuarto.desocupar();
    } 
 
    /**
     * Actualiza el estado del paciente
     */
    public void cambiarEstadoPcte() {
       switch(estadoPcte) {
       case ESPERANDO: estadoPcte = EstadoPaciente.ATENDIENDO; break;
       case ATENDIENDO: estadoPcte = EstadoPaciente.ALTA; break;
       case ALTA: 
          throw new ExcepcionUrgencias (
                 "No se puede acualizar estado ALTA");
       }
    }

    /**
     * Actualiza la condicion del paciente
     */
    public void actualizarCondicion() {
       switch(estadoPcte) {
       case REGULAR: estadoPcte = EstadoPaciente.MEJORANDO; break;
       case MEJORANDO: estadoPcte = EstadoPaciente.ALTA; break;
       case DEFUNCION: 
          throw new ExcepcionUrgencias (
                 "El paciente ha fallecido");
       }
    }
 
    /** 
     * Regresa una representacion cadena del paciente
     */
    @Override
    public String toString() {
       String out = "Nombre: " + nombrePaciente+
          "\tEdad: " + edad+
          "\tEstado: " + condicion;
       if (cuarto!=null) {
          out = out +"\tCuarto: "+cuarto;
       }
       return out;
    }
   
    /**
     * Revisa si el cuarto es igual al objeto dado
     */
    //@Override
    //public boolean equals(Object objEn) {
    //   if (objEn!=null) {
    //   Cuarto p = (Cuarto)objEn;
    //   return p.numeroCuarto.equals(numeroCuarto);
    //   }
    //   else {
    //     return false;
    //   }
    //}

    /**
    * Regresa un valor hashcode
    */
   @Override
   public int hashCode() {
      return numeroCuarto.hashCode();
   }
}
