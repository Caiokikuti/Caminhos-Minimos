import java.util.ArrayList;
import java.util.List;

public class Vertice {
    String id;
    List<Aresta> adj;
    Vertice pred;
    double peso;

    public Vertice(String id, ArrayList<Aresta> adj, Vertice pred) {
        this.id = id;
        this.adj = adj;
        this.pred = pred;
    }

    public Vertice(String id, ArrayList<Aresta> adj) {
        this.id = id;
        this.adj = adj;
    }

    public Vertice(String id) {
        this.id = id;
        this.adj = new ArrayList<Aresta>();
    }
    public Vertice(){}

    void addAdj(Aresta a){
        adj.add(a);
    }
}
