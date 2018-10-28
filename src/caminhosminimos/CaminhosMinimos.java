
package caminhosminimos;
import static caminhosminimos.CaminhosMinimos.floydWarshall;
import java.util.Scanner;





public class CaminhosMinimos {
     
    public static void printCaminho(int x, int y, int[][]pred){
        if(pred[x][y] != y){
            System.out.println("vertice: "+y);
            return;
        }
        printCaminho(x,pred[x][y], pred);
        System.out.println(y);
    }
    
     public static void floydWarshall(int[][]mat, int n, int[][] pred){
        int x, y;
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
        System.out.println("Matriz de distâncias: ");
        for(int i=0;i<n;i++){
            System.out.println(" ");
            for(int j=0;j<n;j++){
                System.out.print(mat[i][j]+"    ");
            }
        }
        System.out.println("Matriz de predecessores: ");
        for(int i=0;i<n;i++){
            System.out.println(" ");
            for(int j=0;j<n;j++){
                System.out.print(pred[i][j]+"    ");
            }
        }
        
        System.out.println("");
        System.out.println("Digite o vertice de origem: ");
        x = teclado.nextInt();
        System.out.println("Digite o destino: ");
        y = teclado.nextInt();
        printCaminho(x,y,pred);
        
    }
    
    public static void main(String[] args) {
        int m;
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Vertices: ");
        m=teclado.nextInt();

        int[][] pred = new int[m][m];
        int[][] matriz = new int[m][m];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                pred[i][j] = i+1;
                if(j==i){
                    matriz[i][j] = 0;
                }else{
                    System.out.printf("Digite a posição matriz[%d][%d]",i,j);
                    matriz[i][j] = teclado.nextInt();
                }
               
            }
        }
       floydWarshall(matriz,m,pred);
    }
    
}
