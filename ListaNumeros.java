import java.util.Random;
import java.util.Arrays;

/**
 * Un objeto de esta clase
 * guarda una lista de n�meros enteros
 * 
 * La clase incluye una serie de m�todos de instancia
 * para hacer operaciones sobre la lista
 * y dos  m�todos est�ticos para trabajar con
 * arrays de dos dimensiones
 *
 * @author - Iratxe Rem�n
 */

public class ListaNumeros {
    public static final int DIMENSION = 10;
    public static final int ANCHO_FORMATO = 6;
    public static final char CAR_CABECERA = '-';

    private static final Random generador = new Random();
    private int[] lista;
    private int pos;

    /**
     * Constructor de la clase ListaNumeros
     * Crea e inicializa adecuadamente los
     * atributos
     *
     * @param n el tama�o m�ximo de la lista
     */
    public ListaNumeros(int n) {
        lista = new int[n];
        pos = 0;
    }

    /**
     * A�ade un valor al final de la lista 
     * siempre que no est� completa
     *
     * @param numero el valor que se a�ade.  
     * @return true si se ha podido a�adir, false en otro caso
     */
    public boolean addElemento(int numero) {
        if (pos < lista.length) {
            lista[pos] = numero;
            pos++;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return true si la lista est� completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
        return pos == lista.length;
    }

    /**
     * @return true si la lista est� vac�a, false en otro caso.
     * Hacer sin if
     */
    public boolean estaVacia() {
        return pos == 0;
    }

    /**
     * @return el n� de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        return pos;
    }

    /**
     * Vac�a la lista
     */
    public void vaciarLista() {
        pos = 0;
    }

    /**
     * @return una cadena con representaci�n textual de la lista 
     * (leer enunciado)
     * 
     * Si la lista est� vac�a devuelve ""
     */
    public String toString() {
        String str = "";
        str += escribirLineas();
        str += "\n";
        for(int j = 0; j < pos; j++) {
            str += Utilidades.centrarNumero(lista[j], ANCHO_FORMATO);  
        }
        str += "\n";
        str += escribirLineas();

        if (pos == 0) {
            str = "";
        }

        return str;
    }

    /**
     * 
     */
    private String escribirLineas() {
        String str = "";
        for (int i = 0; i < pos * ANCHO_FORMATO; i++) {
            str += CAR_CABECERA;    
        }
        return str;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     *  
     * @return el segundo valor m�ximo en la lista
     * Cuando no haya un segundo m�ximo el m�todo 
     * devolver� el valor Integer.MIN_VALUE
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} se devuelve 28
     * Si lista = {21, -5, 28, -7, 77} se devuelve 28
     * Si lista = {77, 21} se devuelve 21
     * Si lista = {21} se devuelve Integer.MIN_VALUE
     * Si lista = {21, 21, 21, 21} se devuelve Integer.MIN_VALUE
     * 
     * No se puede usar ning�n otro array auxiliar ni hay que ordenar previamente
     * la lista
     */
    public int segundoMaximo() {       
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < pos; i++) {
            if (lista[i] > max) {
                max = lista[i];
            }
            if (lista[i] < min) {
                min = lista[i];
            }
        }
        for (int i = 0; i < pos; i++) {
            if (lista[i] < max && lista[i] > min) {
                min = lista[i];
            }
        }

        if (max == min) {
            min = Integer.MIN_VALUE;
        }

        max = min;
        return max;
    }

    /**
     * El m�todo coloca los valores que son segundos m�ximos al principio de
     * la lista respetando el orden de aparici�n del resto de elementos
     * 
     * No se puede usar ning�n otro array auxiliar ni hay que ordenar previamente
     * la lista
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} 
     * lista queda  {28, 28, 28, 28, 21, -5, -7, 77, 77, -17, 21, 15, 77} y se devuelve true
     * 
     * Si lista = {77, 21} lista queda {21, 77} y se devuelve true
     * Si lista = {21} lista queda igual y se devuelve false
     * Si lista = {21, 21, 21, 21} lista queda igual y se devuelve false
     * 
     * @return true si se han colocado los segundos m�ximos
     *          false si no se han colocado los segundos m�ximos porque no hab�a ninguno
     */
    public boolean segundosMaximosAlPrincipio() {
        boolean distinta = false;
        for (int i = 0; i < pos; i++) {
            if (lista[i] == segundoMaximo()) {
                int aux = lista[i];
                for (int j = i; j > 0; j--) {
                    lista[j] = lista[j-1];
                }
                lista[0] = aux;
                distinta = true;
            }
        }
        return distinta;
    }

    /**
     * @param numero b�squeda binaria de  numero en lista
     * @return devuelve -1 si no se encuentra o la posici�n en la que aparece
     *  
     * El array original lista no se modifica
     * Para ello crea  previamente una copia
     * de lista y trabaja  con la copia
     *  
     * Usa exclusivamente m�todos de la clase Arrays
     */
    public int buscarBinario(int numero) {
        int[] listaCopia = Arrays.copyOf(lista, pos);
        Arrays.sort(listaCopia);
        int pos = Arrays.binarySearch(listaCopia, numero);
        if (pos < 0) {
            return -1;
        }
        else {
            return pos -1;  
        }
    }

    /**
     * @return devuelve un array bidimensional de enteros de tama�o DIMENSION
     * inicializado con valores aleatorios entre 0 y 10 inclusive
     * 
     * Estos valores van a representar el brillo de una zona del espacio
     */
    public static int[][] crearBrillos() {
        int[][] brillos = new int[DIMENSION][DIMENSION];
        for (int fila = 0; fila < brillos.length; fila++) {
            for (int col = 0; col < brillos[fila].length; col++) {
                brillos[fila][col] = generador.nextInt(11);
            }
        }
        return brillos;
    }

    /**
     * @param  un array bidimensional brillos 
     * @return un nuevo array bidimensional de valores booleanos
     *          de las mismas dimensiones que el array brillos con
     *          valores true en las posiciones donde hay estrellas
     * 
     * Una posici�n f,c del array brillos es una estrella 
     * si la suma del valor de los brillos de sus cuatro vecinos 
     * (arriba, abajo, derecha e izquierda) es mayor que 30
     * 
     * Nota -  No hay estrellas en los bordes del array brillos
     */
    public static boolean[][] detectarEstrellas(int[][] brillos) {
        boolean[][] estrellas = new boolean[DIMENSION][DIMENSION];
        for (int i = 1; i < DIMENSION - 1; i++) {
            for (int j = 1; j < DIMENSION - 1; j++) {
                int suma = brillos[i+1][j] + brillos[i-1][j] 
                    + brillos[i][j-1] + brillos[i][j+1];
                if (suma > 30) {
                    estrellas[i][j] = true;
                }
            }
        }
        return estrellas;
    }
}
