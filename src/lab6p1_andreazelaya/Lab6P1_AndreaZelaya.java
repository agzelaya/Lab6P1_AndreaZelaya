package lab6p1_andreazelaya;

import java.util.Scanner;
import java.util.Random;

public class Lab6P1_AndreaZelaya {

    static Scanner in = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        int opt;
        do {
            System.out.println("\nMENU");
            System.out.println("1. Turing");
            System.out.println("2. Constante de Kaprekar");
            System.out.println("3. Salida");
            System.out.print("Ingrese una opcion: ");
            opt = in.nextInt();
            System.out.println();

            switch (opt) {
                case 1: {
                    System.out.println("Turing");

                    System.out.print("Ingrese el tamaño del arreglo: ");
                    int tam = in.nextInt();
                    int[] arregloRandom = randomNum(tam);
                    System.out.print("Arreglo generado: ");
                    print(arregloRandom);
                    System.out.println();

                    System.out.println("Ingrese la cadena de instrucciones: ");
                    String inst = in.next();

                    System.out.println("Cadena generada: " + interpret(inst, arregloRandom));
                    break;
                }

                case 2: {
                    System.out.println("Constante de Kaprekar");

                    System.out.print("Ingrese un numero de 4 cifras: ");
                    int num = in.nextInt();

                    if (num >= 1000 && num <= 9999) {
                        int[] arreglo = intToArreglo(num);

                       
                        if (digitosIguales(arreglo) == true) {
                            System.out.println("Error. Los 4 digitos no pueden ser iguales.");
                            break;
                        }

                        kaprekar(num);

                    } else {
                        System.out.println("Error. Ingrese un numero de cuatro digitos.");
                    }

                    break;

                }
            }

        } while (opt != 3);
    }

    public static int[] randomNum(int length) {
        int[] randomArray = new int[length];

        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = random.nextInt(10);
        }

        return randomArray;
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + array[i] + "]");
        }
    }

    public static String interpret(String inst, int[] array) {
        String newString = "";
        int apuntador = 0;

        for (int i = 0; i < inst.length(); i++) {
            char chara = inst.charAt(i);

            if (chara == 'R') {
                apuntador++;
            }

            if (chara == 'L') {
                apuntador--;
            }
            
            

            /*if (chara == 'X') {
                if (apuntador > array.length || apuntador < 0) {
                    System.out.println("Error. Fuera del límite del arreglo.");
                } else {
                    newString += array[apuntador];
                }
            }*/
            
            if(chara == 'X'){
                if (apuntador > array.length-1 || apuntador < 0){
                    System.out.println("Error. Fuera del limite del arreglo.");
                } else{
                    newString += array[apuntador];
                }
            }

        }

        return newString;
    }

    public static int[] intToArreglo(int num) {
        int[] arregloNum = new int[4];
        int resi = 0;
        int cont = 3;

        for (int i = 0; i < 4; i++) {
            resi = num % 10;
            num = num / 10;
            arregloNum[cont] = resi;
            cont--;
        }

        return arregloNum;
    }

    public static boolean digitosIguales(int[] arreglo) {
        boolean sonIguales = false;

        int d1 = arreglo[0];
        int d2 = arreglo[1];
        int d3 = arreglo[2];
        int d4 = arreglo[3];

        if (d1 == d2 && d2 == d3 && d3 == d4) {
            sonIguales = true;
        }

        return sonIguales;
    }

    public static int arregloToInt(int[] arreglo) {
        int newInt = 0;
        int cont = 3;

        for (int i = 0; i < 4; i++) {
            int digit = arreglo[i];

            newInt += digit * (int) Math.pow(10, cont);
            cont--;
        }
        //System.out.println(newInt);
        return newInt;
    }

    public static int[] ascendente(int[] arreglo) {

        /*la variable i es un valor de array, el valor j es un valor que empieza
            desde un valor mayor que i. j recorre el resto del array y compara los
            valores de i y j. si i es menor que j, no pasa nada. si j es menor que i,
            el valor de i es almacenado en una variable temporal. el valor de i cambia
            al valor de j y el valor de j es asignado al valor de temporal. en otras 
            palabras, los valores cambian su posicion entre si hasta el limite, el cual
            es el length del array, que en este caso siempre es 4.
         */

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (arreglo[j] < arreglo[i]) {
                    int temp = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = temp;
                }
            }
        }

        return arreglo;
    }

    public static int[] descendente(int[] arreglo) {
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (arreglo[j] > arreglo[i]) {
                    int temp = arreglo[i];
                    arreglo[i] = arreglo[j];
                    arreglo[j] = temp;
                }
            }
        }
        //print(arreglo);
        return arreglo;

    }

    public static void kaprekar(int numero) {
        int intAsc, intDesc;
        int num = numero;
        int iter = 1;

        while (num != 6174 && iter <= 7) {
            int[] array = intToArreglo(num);
            int[] ascen = ascendente(array);
            intAsc = arregloToInt(ascen);

            int[] descen = descendente(array);
            intDesc = arregloToInt(descen);

            num = intDesc - intAsc;
            iter++;
            System.out.println(intDesc + " - " + intAsc + " = " + num);
        }

    }

}
