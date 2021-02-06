import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ComunHilos {
    int conexiones; //Conexiones ocupadas en el array
    int actuales;   //Conexiones actuales
    int maximo;     //Máximo de conexiones permitidas
    Socket tabla[] = new Socket[maximo];// Sockets de clientes conectados
    ArrayList<Pregunta> listaPreguntas=new ArrayList<Pregunta>();
    Pregunta preguntaActual;

    public ComunHilos(int maximo, int actuales, int conexiones, Socket[] tabla) {
        this.maximo = maximo;
        this.actuales = actuales;
        this.conexiones = conexiones;
        this.tabla = tabla;

        listaPreguntas.add(new Pregunta("¿2+2?", "5", "3", "4", 3));
        listaPreguntas.add(new Pregunta("¿5+2?", "7", "3", "4", 1));
        listaPreguntas.add(new Pregunta("¿4+2?", "5", "6", "2", 2));

        //Fijo las letras para jugar
        setPreguntaActual();
    }

    public void setPreguntaActual(){
        int n= new Random().nextInt(listaPreguntas.size());
        preguntaActual=listaPreguntas.get(n);
    }

    public Pregunta getPreguntaActual(){
        return preguntaActual;
    }

    public Pregunta getPreguntaAleatoria(){
        int n= new Random().nextInt(listaPreguntas.size());
        return listaPreguntas.get(n);
    }

    public int getMaximo() { return maximo;	}
    public void setMaximo(int maximo) { this.maximo = maximo;}

    public int getConexiones() { return conexiones;	}
    public synchronized void setConexiones(int conexiones) {
        this.conexiones = conexiones;
    }

    public int getActuales() { return actuales; }
    public synchronized void setActuales(int actuales) {
        this.actuales = actuales;
    }

    public synchronized void addTabla(Socket s, int i) {
        tabla[i] = s;
    }
    public Socket getElementoTabla(int i) { return tabla[i]; }
}
