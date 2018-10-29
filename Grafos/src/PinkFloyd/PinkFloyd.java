import java.util.Scanner;
import java.util.Arrays;

public class PinkFloyd {
    
    public static void printCaminho(int x, int y, int[][]pred){
        if(pred[x][y] == y){
            System.out.print("vertices: "+y + " ");
            return;
        }
        printCaminho(x,pred[x][y], pred);
        System.out.print(" "+y+" ");
    }
    
     public static void floydWarshall(int[][]mat, int n, int[][] pred){
        String buffer;
        String [] beffer;
        Scanner teclado = new Scanner(System.in);
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                   if(mat[i][j]> mat[i][k]+mat[k][j]){
                       pred[i][j] = k;
                   }
                   mat[i][j] = Math.min(mat[i][j], mat[i][k]+mat[k][j]);
                }
            }
        }
        System.out.println();
        System.out.println("Matriz de distâncias: ");
        for(int i=0;i<n;i++){
            System.out.println(" ");
            for(int j=0;j<n;j++){
                System.out.print(mat[i][j]+"    ");
            }
        }
        System.out.println();
        System.out.println("Matriz de predecessores: ");
        for(int i=0;i<n;i++){
            System.out.println(" ");
            for(int j=0;j<n;j++){
                System.out.print(pred[i][j]+"    ");
            }
        }
        System.out.println();
        do{

          buffer = teclado.nextLine();
          beffer = buffer.split(" "); 
          
          if(beffer[0].equals("0") && beffer[1].equals("0")){
              break;
          }
          if(mat[Integer.parseInt(beffer[0])-1][Integer.parseInt(beffer[1])-1] != 99999){
            System.out.println("Resultado: "+mat[Integer.parseInt(beffer[0])-1][Integer.parseInt(beffer[1])-1]);
            printCaminho(Integer.parseInt(beffer[0])-1,Integer.parseInt(beffer[1])-1,pred);
          }else{
            System.out.print("Não existe caminho");
          }
          System.out.println("");
        }while(!beffer[0].equals("0") && !beffer[1].equals("0"));
    }
    
    public static void main(String[] args) {
        int m, n, peso;
        String buffer;
        String [] beffer;
        Scanner teclado = new Scanner(System.in);
        buffer=teclado.nextLine();
        beffer = buffer.split(" ");
        m = Integer.parseInt(beffer[0]);
        n = Integer.parseInt(beffer[1]);
        int[][] pred = new int[m][m];
        int[][] matriz = new int[m][m];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                pred[i][j] = i;
                if(j==i){
                    matriz[i][j] = 0;
                }else{
                    matriz[i][j] = 99999;
                }
               
            }
        }
        for(int k=0;k<n;k++){
            buffer = teclado.nextLine();
            beffer = buffer.split(" ");
            matriz[Integer.parseInt(beffer[0])-1][Integer.parseInt(beffer[1])-1] = Integer.parseInt(beffer[2]); 
        }
        floydWarshall(matriz,m,pred);        
    }
}
