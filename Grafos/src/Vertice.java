import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private String id;
    private List<Aresta> adj;
    private Vertice pred;

    public Vertice(String id, List<Aresta> adj, Vertice pred) {
        this.id = id;
        this.adj = adj;
        this.pred = pred;
    }

    public Vertice(String id, List<Aresta> adj) {
        this.id = id;
        this.adj = adj;
    }

    public Vertice(String id) {
        this.id = id;
        this.adj = new ArrayList<Aresta>();
    }

    void addAdj(Aresta a){
        adj.add(a);
    }
}
