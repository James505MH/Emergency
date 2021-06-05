package sistemaUrgencias; 

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;

/**
 * Clase para proporcionar la funionalidad del sistema Urgencias
 *
 */
  
public class Urgencias {
   // atributos
   private Map<String, Paciente> consultas; // consultas registradas
   private List<Paciente> listaConsultas; // Nombre de los pacientes para consulta
   private Cuarto[] cuarto; // cuartos asignados a Urgencias

   // metodos

   /** 
    * Constructor para cargar datos de Urgencias de un archivo
    *  
    * @param archivoEn El nombre del archivo
    * @throws IOException si hay un problema para abrir o cargar el archivo
    * @throws ClassNotFoundException si los objetos del archivo no son
    *         del tipo correcto
    */
    public Aeropuerto(String archivoEn) {
         throws IOException, ClassNotFoundException {
       cargar(archivoEn);
    }
  
    /**
     * Constructor para crear una coleccion vacia de consultas,
     * y asignar cuartos para Urgencias
     * 
     * @param numeroEn la cantidad de cuartos
     * @throws ExcepcionUrgencias si se uso numero negativo de cuartos
     */
    public Urgencias (int numeroEn) {
       try {
          // inicializa los cuartos
          cuarto = new Cuarto[numeroEn];
          for (int i =0; i<numeroEn; i++)
             cuarto[i] = new Cuarto (i+1);
          }
          // inicialmente no hay cuartos asignados en Urgencias
          consultas = new hashMap<>();
          listaConsultas = new ArrayList<>();
       }
       catch (Exception e) {
          // se lanza excepcion desde una clausula catch
          throw new ExcepcionUrgencias(
                "Cantidad de cuartos invalidos, aplicacion cerrando");
       }
    }

    /**
     * Registrar un paciente en Urgencias
     *
     * @param nombrePacienteEn El nombre del paciente
     * @param edadE La edad del paciente
     * @param costosConsultaEn Costos por la consulta del paciente
     * @param medicamentosEn Costos de los medicamentos
     * @throw ExcepcionUrgencias si el paciente ha sido registrado
     */
    public void registrarPaciente(String nombrePacienteEn, int edadEn, 
                         double costosConsultaEn, double medicamentosEn) {
       if (consultas.containsKey(nombrePacienteEn)) {
          throws new ExcepcionUrgencias (
                 "Paciente " +nombrePacienteEn+ " ya esta registrado");
       }
       Paciente pacienteNuevo = new Paciente(nombrePacienteEn, edadEn, 
                                  costosConsultaEn, medicamentosEn);
       consultas.put(nombrePacienteEn, pacienteNuevo);
    }
  
    /**
     * Devolver el paciente registrado con el nombre del paciente
     *
     * @throws ExcepcionUrgencias si el nombre del paciente no esta registrado
     */
    public Paciente getPaciente(String nombrePacienteEn) {
       if (!consultas.containsKey(nombrePacienteEn)) {
          throw new ExcepcionUrgencias(
                "Paciente de nombre: "+nombrePacienteEn+ 
                            " aun no ha sido registrado"
       }
       return consultas.get(nombrePacienteEn);
    }
     
    /**
     * Preparar al paciente
     *
     * @param nombreEn El nombre del paciente que sera atendido
     * @param numeroCuartoEn El numero de cuaro asignado
     * @throw ExcepcionUrgencias si el paciente no ha sido registrado
     */
    public void listoConsulta(String nombrePacienteEn, String numeroCuartoEn) { 
       // lanza excepcion si el paciente no esta registrado
       Paciente paciente = getPaciente(nombrePacienteEn);
       if (paciente.getNombre() != consultas.containsKey(paciente.getNombre()) {
          throw new ExcepcionUrgencias(
                "El paciente no ha sido registrado");
       }
       if (paciente.getEstado() == EstadoPaciente.ATENDIENDO) {
          throw new ExcepcionUrgencias (
              "El paciente esta siendo atendido");
       }
       if (paciente.getEstado() == EstadoPaciente.ALTA) {
          throw new ExcepcionUrgencias(
              "El paciente ha sido dado de alta")
       }   
    }



    /**
     * Asignar cuarto al paciente que llega a Urgencias
     *
     * @param pacienteEn El nombre del paciente 
     * @param cuartoEn El cuarto donde el paciente sera atendido
     * @thorow ExcepcionUrgencias si el paciente no ha sido registrado 
                                o si ya fue dado de alta
                                o si ya se le asigno un cuarto
     */ 
                                     // Nombre Paciente
    public void atenderPaciente (String pacienteEn, Cuarto cuartoEn) {
       // lanza excepcion si no esta registrado 
       Paciente paciente = getPaciente(pacienteEn);
       if (paciente.getEstado() == EstadoPaciente.ALTA) {
          throw new ExcepcionUrgencias(
              "El paciente ya ha sido dado de alta");
       }
       if (paciente..estaAsignadoCuarto()) {
          throw new ExcepcionUrgencias (
          "El paciente ya tiene cuarto asignado en " +paciente.getNumeroCuarto());
       }
       paciente.asignarCuarto(cuartoEn);
       // estado actualizado de ESPERANDO A ATENDIENDO
       if (paciente.getEstado()== EstadoPaciente.ESPERANDO) {
          paciente.cambiarEstadoPcte();
       }
    }

    /**
     * Realizar costos por la consulta
     *
     * @param nombrePcteEn Recibe el nombre de un paciente
     *        para recuperarlo del Map 
     */
    public double dineroConsulta(String nombrePcteEn);
       // obtiene al paciente
       Paciente paciente = get(nombrePcteEn);
       Random num = new Random();
       double costoC = num.nextInt( (1500+1) - 400);
       costoC = costoC + 400 * 0.987;
  
       // modifica el atributo del paciente 
       paciente.setCostoConsulta(costoC); 
       return costoC;
    }
       
    /**
     * Realizar costos por la medicina
     *
     * @param nombrePcteEn Recibe el nombre de un paciente
     *        para recuperarlo del Map 
     */
    public double dineroMedicamentos(String nombrePcteEn);
       Paciente paciente = get(nombrePcteEn);
       Random num = new Random();
       double costoM = num.nextInt( (2000+1) - 500); 
       costoM = costoM + 500 * 0.987;

       // modifica el atributo Medicamentos del paciente
       paciente.setMedicamentos(costoM);
       return costoM;
    }

    /**
     * Devuelve el costo total de la consulta y medicamentos
     *  
     * @param nombrePcteEn Recibe el nombre del paciente
     * @throws ExcepcionUrgencias si el paciente tiene costos en $0
     *         o si el paciente no a sido dado de Alta
     */
    public double costoTotal (String nombrePcteEn) {
       Paciente paciente = get.(nombrePcteEn);
       if (paciente.getCostoConsulta()==0  || paciente.getMedicamentos==0) {
          throw new ExcepcionUrgencias (
              "El paciente aun no genera costos");
       }
       if (paciente.getEstado() != EstadoPaciente.ALTA) {
           throw new ExcepcionUrgencias(
               "El paciente no ha sido dado de alta");
       }
       double costoT = paciente.getCostoConsulta() + 
                       paciente.getMedicamentos();
       return costoT;
    } 
    
 
    // METODOS AUXILIARES 
    
    // Metodo auxiliar para encontrar el siguiente cuarto libre
    private Cuarto siguienteCuartoLibre() {
       for (Cuarto siguienteCuarto : cuarto) {
          // metodo verEstado para revisar si esta ocupado
          if (!siguienteCuarto.verEstado()) {
             return siguientePista;
          }
       }
       return null;
    }




