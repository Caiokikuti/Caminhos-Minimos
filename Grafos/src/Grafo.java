import java.util.ArrayList;

public class Grafo{
    private ArrayList<Vertice> vertices = new ArrayList<>();
    private ArrayList<Aresta> arestas = new ArrayList<>();

    private Grafo(){}

    private Vertice addVertice(Grafo g,String nome){
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    public static void initializeSingleSource(Grafo g, Vertice s){
        for (Vertice u: g.vertices) {
            u.peso = Double.POSITIVE_INFINITY;
        }
        s.peso = 0;
    }

    private Aresta addAresta(Vertice origem, Vertice destino, double peso){
        Aresta a = new Aresta(origem,destino,peso);
        origem.addAdj(a);
        arestas.add(a);
        return a;
    }
    
    public static void main(String[] args){
        Grafo g = new Grafo();
        Vertice a = g.addVertice(g,"a");
        Vertice b = g.addVertice(g,"b");
        Aresta ab = g.addAresta(a,b,10);
        initializeSingleSource(g,a);
        for (Vertice i:g.vertices) {
            System.out.println(i.id);
            System.out.println(i.peso);
        }
    }
}
