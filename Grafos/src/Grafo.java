import java.util.List;

public class Grafo{
    private List<Vertice> vertices;
    private List<Aresta> arestas;

    public Grafo(List<Vertice> vertices, List<Aresta> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
    }
    private Grafo(){}

    public void addVertice(String nome){
        Vertice v = new Vertice(nome);
        vertices.add(v);
    }

    public void addAresta(Vertice origem, Vertice destino, int peso){
        Aresta a = new Aresta(origem,destino,peso);
        origem.addAdj(a);
        arestas.add(a);
    }
    public static void main(String[] args){
        Grafo g = new Grafo();
    }
}
