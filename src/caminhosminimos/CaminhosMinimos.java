
package caminhosminimos;
import static caminhosminimos.CaminhosMinimos.floydWarshall;
import java.util.Scanner;





public class CaminhosMinimos {
     public static void floydWarshall(int[][]mat, int n){
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                   mat[i][j] = Math.min(mat[i][j], mat[i][k]+mat[k][j]);
                }
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(mat[i][j]);
            }
        }
        
    }
    
    public static void main(String[] args) {
        int m,n;
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Vertices: ");
        m=teclado.nextInt();
        System.out.println("Arestas: ");
        n=teclado.nextInt();
        
        int[][] matriz = new int[m][m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(j==i){
                    matriz[i][j] = 0;
                }
                System.out.printf("Digite a posição matriz[%d][%d]",i,j);
                matriz[i][j] = teclado.nextInt();
            }
        }
       floydWarshall(matriz,n);
    }
    
}
