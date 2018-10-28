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

    public static void main(String[] args){
        Grafo g = new Grafo();
        /*
        Vertice s = g.addVertice(g,"s");
        Vertice u = g.addVertice(g,"u");
        Vertice v = g.addVertice(g,"v");
        Vertice w = g.addVertice(g,"w");
        Vertice x = g.addVertice(g,"x");
        Vertice y = g.addVertice(g,"y");

        Aresta su = g.addAresta(s,u,10);
        Aresta sx = g.addAresta(s,x,3);
        Aresta ux = g.addAresta(u,x,3);
        Aresta uv = g.addAresta(u,v,2);
        Aresta uy = g.addAresta(u,y,-3);
        Aresta xy = g.addAresta(x,y,3);
        Aresta vu = g.addAresta(v,u,-1);
        Aresta yv = g.addAresta(y,v,7);
        Aresta yw = g.addAresta(y,w,1);
        Aresta wv = g.addAresta(w,v,5);
        */
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
        Aresta xv = g.addAresta(x,y,8);
        Aresta vy = g.addAresta(v,y,2);
        Aresta vw = g.addAresta(v,w,6);
        Aresta yw = g.addAresta(y,w,2);

        for (Vertice i:g.vertices) {
            System.out.print(i.id + ": ");
            System.out.print(i.peso+", ");
        }
        /*
        if(bellmanFord(g,w,s)){
            for (Vertice i:g.vertices) {
                System.out.print(i.id + ": ");
                System.out.print(i.peso+", ");
                //System.out.println("pred: " + i.pred.id);
            }
        }else{
            System.out.println("Existe ciclo negativo");
        }*/
    }
}
