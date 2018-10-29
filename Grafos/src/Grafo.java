import java.util.ArrayList;
import java.util.PriorityQueue;

public class Grafo{
    private ArrayList<Vertice> vertices = new ArrayList<>();
    private ArrayList<Aresta> arestas = new ArrayList<>();

    private Grafo(){}

    private Vertice addVertice(Grafo g,String nome){
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    private Aresta addAresta(Vertice origem, Vertice destino, double peso){
        Aresta a = new Aresta(origem,destino,peso);
        origem.addAdj(a);
        arestas.add(a);
        return a;
    }

    private static void initializeSingleSource(Grafo g, Vertice s){
        for (Vertice u: g.vertices) {
            u.peso = Double.POSITIVE_INFINITY;
            u.pred = null;
        }
        s.peso = 0;
    }

    private static void Relax(Vertice u, Vertice v, Aresta w){
        if (v.peso > (u.peso + w.peso)){
            v.peso = u.peso + w.peso;
            v.pred = u;
        }
    }

    private static boolean bellmanFord(Grafo g, Vertice w, Vertice s){
        initializeSingleSource(g,s);
        for (int i = 0; i < g.vertices.size();i++){
            for (Aresta uv: g.arestas) {
                Relax(uv.origem,uv.destino,uv);
            }
        }
        for (Aresta uv: g.arestas) {
            if(uv.destino.peso > (uv.origem.peso + uv.peso)){
                return false;
            }
        }
        return true;
    }
    public static boolean djikstra(Grafo g, Vertice w, Vertice s){
        PriorityQueue<Par<Double,Vertice>> q = new PriorityQueue<Par<Double, Vertice>>();
        ArrayList<Vertice> buffer = new ArrayList<>();
        initializeSingleSource(g, s);
        Vertice u = new Vertice();
        for(Vertice v: g.vertices){
            q.add(new Par <Double, Vertice>(v.peso, v));
        }
        while(!q.isEmpty()) {
            u = q.poll().getU();
            buffer.add(u);
            for (Vertice a : g.vertices) {
                for (Aresta x : a.adj) {
                    Relax(x.origem, x.destino, x);
                }
            }
        }
        return true;
    }

    private static void printarCaminho(Vertice v){
        if(v.pred == null){
            System.out.print(v.id + ", ");
            return;
        }
        printarCaminho(v.pred);
        System.out.print(v.id + ", ");
    }

    public static void main(String[] args){
        Grafo g = new Grafo();

        Vertice s = g.addVertice(g,"s");
        Vertice u = g.addVertice(g,"u");
        Vertice v = g.addVertice(g,"v");
        Vertice w = g.addVertice(g,"w");
        Vertice x = g.addVertice(g,"x");
        Vertice y = g.addVertice(g,"y");

        Aresta su = g.addAresta(s,u,4);
        Aresta sx = g.addAresta(s,x,2);
        Aresta ux = g.addAresta(u,x,1);
        Aresta uv = g.addAresta(u,v,5);
        Aresta xy = g.addAresta(x,y,10);
        Aresta xv = g.addAresta(x,v,8);
        Aresta vy = g.addAresta(v,y,2);
        Aresta vw = g.addAresta(v,w,6);
        Aresta yw = g.addAresta(y,w,2);

        if(bellmanFord(g,w,s)){
            for (Vertice i:g.vertices) {
                System.out.print(i.id + ": ");
                System.out.print(i.peso + ", ");
            }
            System.out.println();
            printarCaminho(w);
            System.out.println();
        }else{
            System.out.println("Existe ciclo negativo");
        }

        if (djikstra(g,w,s)){
            for (Vertice i:g.vertices) {
                System.out.print(i.id + ": ");
                System.out.print(i.peso + ", ");
            }
            System.out.println();
            printarCaminho(w);
            System.out.println();
        }

    }
}
