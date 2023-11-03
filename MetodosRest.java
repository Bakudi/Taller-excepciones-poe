import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



public class MetodosRest {

    static Platos entrada1 = new Platos("Aros de cebolla", "Aros hechos con cebolla .__.", Tipoplato.ENTRADA, 15000, 10);
    static Platos entrada2 = new Platos("Tostadas", "Tostadas de platano con ají", Tipoplato.ENTRADA, 20000, 15);
    static Platos bebida1 = new Platos("Jugo de mango", "bebida de mango ._.", Tipoplato.BEBIDA, 20000, 10);
    static Platos bebida2 = new Platos("Limonada cerezada", "Limonada con sabor a cereza", Tipoplato.BEBIDA, 25000, 10);
    static Platos fuerte1 = new Platos("Parrillada", "Picada de varias carnes (pollo, res y cerdo) acompañado de papa y ensalada", Tipoplato.PLATO_FURTE, 60000, 25);
    static Platos fuerte2 = new Platos("Bandeja paisa", "plato de arroz, frijoles, chorizo, huevo, aguacate y arepa", Tipoplato.PLATO_FURTE, 90000, 35);

    static boolean condi = true;
    public static void ordenarMenu(ArrayList<Platos> lista){

        while (condi==true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Qué desea ordenar?\n");
            System.out.println("1. Entrada\n 2. Bebidas\n 3. Plato fuerte\n");
            int opc = scanner.nextInt();
            switch (opc) {
                case 1:
                    ordenarEntradas(lista);
                    break;
                case 2:
                    ordenarBebidas(lista);
                    break;
                case 3:
                    ordenarFuertes(lista);
                default:
                    break;
            }
            System.out.println("\nDigite el numero (1) si desea volver a ordenar algo del menú.\nDigite el numero (0) si desea terminar.");
            int opc2 = scanner.nextInt();
            if(opc2 == 1){
                condi = true;
            }
            if(opc2 == 0){
                condi = false;
            }
        }
    }

    public static void ordenarEntradas(ArrayList<Platos> lista) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escoja la entrada que desea ordenar");
        System.out.println("1. Aros de pollo\n2. Tostadas\n");
        int opc = scanner.nextInt();
        System.out.println("Cantidad: ");
        int opc2 = scanner.nextInt();
        switch (opc) {
            case 1:
                for(int i=0; i < opc2; i++){
                    lista.add(entrada1);
                }
                break;
            case 2:
                for(int i=0; i < opc2; i++){
                    lista.add(entrada2);
                }
                break;
            default:
                break;
        }
    }

    public static void ordenarBebidas(ArrayList<Platos> lista) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escoja la bebida que desea ordenar");
        System.out.println("1. Jugo de mango\n2. Limonada cerezada\n");
        int opc = scanner.nextInt();
        System.out.println("Cantidad: ");
        int opc2 = scanner.nextInt();
        switch (opc) {
            case 1:
                for(int i=0; i < opc2; i++){
                    lista.add(bebida1);
                }
                break;
            case 2:
                for(int i=0; i < opc2; i++){
                    lista.add(bebida2);
                }
                break;
            default:
                break;
        }
    }

    public static void ordenarFuertes(ArrayList<Platos> lista) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escoja el plato fuerte que desea ordenar");
        System.out.println("1. Parrillada\n2. Bandeja paisa\n");
        int opc = scanner.nextInt();
        System.out.println("Cantidad: ");
        int opc2 = scanner.nextInt();
        switch (opc) {
            case 1:
                for(int i=0; i < opc2; i++){
                    lista.add(fuerte1);
                }
                break;
            case 2:
                for(int i=0; i < opc2; i++){
                    lista.add(fuerte2);
                }
                break;
            default:
                break;
        }
    }
    
}
