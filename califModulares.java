import java.util.Scanner;

public class califModulares{
    static Scanner scan= new Scanner(System.in);
    static String[] leerNombres(int cantA){
        String[] nombres= new String[10];
        for(int i=0;i<cantA;i++){
            System.out.println("Ingrese el nombre del alumno "+(i+1));
            nombres[i]=scan.nextLine();
        }
        return nombres;
    }
    static float[][] leerCalif(int cantA,int cantC, String[] nombres){
        float[][] calif = new float[10][10];
        for(int i=0;i<cantA;i++){
            for(int j=0;j<cantC;j++){
                System.out.println("Ingrese la calificación No. "+(j+1)+" del alumno "+nombres[i]);
                calif[i][j]= scan.nextFloat();
                scan.nextLine();
            }
            
        }
        return calif;
    }

    static float calcularProm(float[][] calif, int cantA, int cantC){
        float prom=0,sumProm=0;
        int cantCal=cantA*cantC;
        for(int i=0;i<cantA;i++){
            for(int j=0;j<cantC;j++){
                sumProm+=calif[i][j];
            }
        }
        prom=sumProm/cantCal;
        return prom;
    }
    static int[] masAlta(int cantA, int cantC, float[][] calif){
        float hst=0;
        int[] indices = new int[2];
        for(int i=0;i<cantA;i++){
            for(int j=0;j<cantC;j++){
                if(calif[i][j]>hst){
                    hst=calif[i][j];
                    indices[0]=i;
                    indices[1]=j;
                }
            }
        }
        return indices;
    }
    static int[] masBaja(int cantA, int cantC, float[][] calif){
        float lwst=100;
        int[] indices = new int[2];
        for(int i=0;i<cantA;i++){
            for(int j=0;j<cantC;j++){
                if(calif[i][j]<lwst){
                    lwst=calif[i][j];
                    indices[0]=i;
                    indices[1]=j;
                }
            }
        }
        return indices;
    }
    static void imprimirResultados(int cantA,int cantC, int[] indexAlta, int[] indexBaja, String[] nombres, float[][] calif, float promedio){
        String impresion="";
            for(int i=0;i<cantA;i++){
                System.out.println(nombres[i]+":");
                for(int j=0;j<cantC;j++){
                    System.out.print(calif[i][j]+"\t");
                }
                System.out.print("\n");
            }
            System.out.println("El promedio general de las calidifaciones ingresadas fue: "+promedio);
            System.out.println("La calificacion más alta obtenida fue por el alumno "+nombres[indexAlta[0]]+" con:"+calif[indexAlta[0]][indexAlta[1]]);
            System.out.println("La calificacion más baja obtenida fue por el alumno "+nombres[indexBaja[0]]+" con:"+calif[indexBaja[0]][indexBaja[1]]);
    }
    public static void main(String[] args){
        int[] indexAlta = {1,2};
        int[] indexBaja = {2,1};
        String[] nombres = {"Marco Torres","Daniela Valdivia","Juan Ornelas"};
        float[][] calif = {
            {98.9f,75f,91f},
            {100f,95f,97f},
            {80f,85f,65f}
        };
        int cantA=3, cantC=3;
        float promedio;
                //nombres=leerNombres(cantA);
                //calif=leerCalif(cantA, cantC, nombres);
                promedio=calcularProm(calif, cantA, cantC);
                indexAlta=masAlta(cantA, cantC, calif);
                indexBaja=masBaja(cantA, cantC, calif);
                imprimirResultados(cantA, cantC, indexAlta, indexBaja, nombres, calif, promedio);
    }
}