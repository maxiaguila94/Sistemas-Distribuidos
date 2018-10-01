import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;

public class Cliente extends Thread {
    private Clock objetoRemoto;
    private Controller controlador;
    private static int diferencia = 300; // diferencia de 5 minutos
    private static int granularidad = 500;
    private int delta;
    private boolean ajuste;
    private Calendar calendario; //Para el manejo de tiempo
    private int veces;

    // Constructor
    public Cliente(String host) {
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            this.objetoRemoto = (Clock) registry.lookup("Clock");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
        this.ajuste = false;
        this.veces = 0;
        this.delta = 0;
        this.calendario = Calendar.getInstance();
        this.calendario.add(Calendar.SECOND, this.diferencia);
        this.start();
        

    }

    // Setters y Getters
    public int getHora() {
        return this.calendario.get(Calendar.HOUR);
    }

    public int getMinutos() {
        return this.calendario.get(Calendar.MINUTE);
    }

    public int getSegundos() {
        return this.calendario.get(Calendar.SECOND);
    }

    public void setDelta(int value) {
        this.delta = value;
    }

    public int getDelta() {
        return this.delta;
    }

    public int getDiferencia() {
        return this.diferencia;
    }

    public int getGranularidad() {
        return this.granularidad;
    }

    public void setAjuste(boolean ajuste) {
        this.ajuste = ajuste;
    }

    public boolean getAjuste() {
        return this.ajuste;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

    public int getVeces() {
        return veces;
    }

    public Clock getObjetoRemoto() {
        return this.objetoRemoto;
    }

    public void setControlador(Controller controlador) {
        this.controlador = controlador;
    }
    
    public Controller getControlador() {
        return this.controlador;
    }

    @Override
    public void run() {
        try {
            while(true){
                //Si hubo un cambio en el delta, va a contar cuantas veces tiene que aplicarse ese delta sobre el segundo antes de
                //volver a la normalidad
                if(this.getAjuste()){
                    if(this.getVeces() < 0){
                        this.setAjuste(false);
                        this.setDelta(0);
                    }else{
                        this.setVeces(this.getVeces()-1);
                    }
                }
                //Funcionamiento de reloj
                Thread.sleep(1000+this.getDelta()); //cada 1 segundo + delta, el reloj aumenta 1 segundo.
                this.calendario.add(Calendar.SECOND, 1);
                this.controlador.mostrarHora(this.getHora(), this.getMinutos(), this.getSegundos());
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

    }

    public long algoritmoCristian(Clock clock) {
        try{
            long primeraObtenida = clock.getHora();
            long segundaObtenida = 0;
            long sumatoriaRTT = 0;
            for(int i=0; i<5;i++){
                segundaObtenida = clock.getHora();
                sumatoriaRTT = sumatoriaRTT + (segundaObtenida - primeraObtenida);
                primeraObtenida = segundaObtenida;
            }
            return primeraObtenida + ((sumatoriaRTT / 5) / 2); //Ultima hora obtenida + (promedio RTT / 2)
        }catch(Exception e){
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    public void ajustaClock(Clock clock) {
        long horaCalculada = algoritmoCristian(clock); //Realiza el algoritmo de Cristian y obtiene la hora calculada del server.
                long horaCliente = this.calendario.getTimeInMillis(); //Obtiene hora del cliente para comparar con la calculada
                long diferencia = horaCliente - horaCalculada; //Si es positivo, el reloj esta adelantado. Si es negativo, esta atrasado.
                System.out.println("La diferencia en milisegundos es: " + diferencia);
                System.out.println("La diferencia en segundos es: " + diferencia/1000);
                System.out.println("La diferencia en minutos es: " + (diferencia/1000)/60);

                if(diferencia!=0){ //si el reloj no esta sicronizado a nivel milisegundos
                    if (diferencia > 0){ //reloj adelantado
                        if((diferencia/1000) > 200){ 
                            this.setDelta(1500); //aumenta el tiempo entre cada segundo del reloj
                        }else {
                            if((diferencia/1000) > 50){
                                this.setDelta(1000);
                            }else{
                                this.setDelta(granularidad);
                            }

                        }
                                                    
                    }else{ //reloj atrasado
                        if(((Math.abs(diferencia))/1000) > 3){
                            this.setDelta(-1500);//reduce el tiempo entre cada segundo del reloj
                        }else{
                            if(((Math.abs(diferencia))/1000) > 50){
                                this.setDelta(-1000);
                        }else {
                            this.setDelta(-granularidad);
                        }
                        
                    }

                    this.setVeces((int)(Math.abs(diferencia) / granularidad)); 
                    this.setAjuste(true);
                }
                int a = this.getDelta();
                System.out.println("Trabajando con Delta: " + a);

            }
    }
}