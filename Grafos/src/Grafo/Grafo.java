
import java.util.*;

public class Grafo{
    private ArrayList<Vertice> vertices = new ArrayList<>();
    private ArrayList<Aresta> arestas = new ArrayList<>();

    private Grafo(){}
    static final double maxNumber = 10000;

    private void addVertice(String nome){
        Vertice v = new Vertice(nome);
        vertices.add(v);
    }

    private static Vertice getVertice(Grafo g, String id){
        for (Vertice aux: g.vertices) {
            if (aux.id.equals(id)){
                return aux;
            }
        }
        return null;
    }

    private void addAresta(Vertice origem, Vertice destino, double peso){
        Aresta a = new Aresta(origem,destino,peso);
        origem.addAdj(a);
        arestas.add(a);
    }

    private static void initializeSingleSource(Grafo g, Vertice s){
        for (Vertice u: g.vertices) {
            u.peso = maxNumber;
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

    private static void Relax2(Vertice u, Vertice v, Aresta w,PriorityQueue<Par<Double,Vertice>> q){
        if (v.peso > (u.peso + w.peso)){
            v.peso = u.peso + w.peso;
            v.pred = u;
            for(Par<Double, Vertice> p: q){
                if(p.getU() == v){
                    p.setT(v.peso);
                }
            }
        }
    }
    private static void Relax3(Vertice u, Vertice v, Aresta w,PriorityQueue<Par<Double,Vertice>> q ){
        if (v.peso > (u.peso + w.peso)){
            v.peso = u.peso + w.peso;
            v.pred = u;
            Par par = new Par(v.peso, v);
            q.add(par);
        }
    }
    
     public static boolean  djikstra2(Grafo g, Vertice w, Vertice s){
        PriorityQueue<Par<Double, Vertice>> q = new  PriorityQueue<Par<Double, Vertice>>();
        HashMap<String, Vertice> S = new HashMap<String, Vertice>();
        initializeSingleSource(g, s);
        Vertice u = new Vertice();
        for(Vertice v: g.vertices){
            Par par = new Par(v.peso, v);
            q.add(par);
        }
        while(!q.isEmpty()){
            u = q.poll().getU();
            if(!S.containsKey(u.id)){
                for(Aresta a: u.adj){
                    Relax3(u,a.destino, a, q);
                }
            }    
                S.put(u.id, u);
        }
        return true;
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
        while(!q.isEmpty()){
            u = q.peek().getU();
            //u = q.poll().getU();
            buffer.add(u);
            for(Aresta a: u.adj){
               Relax2(u,a.destino,a,q);
               //Relax(u,a.destino,a);
            }
           q.remove();
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
        Scanner input = new Scanner(System.in);
        String buffer;
        String[] separate;
        Vertice u,v,aux;

        buffer = input.nextLine();
        separate = buffer.split(" ");
        int qtdVertices = Integer.parseInt(separate[0]);
        int qtdArestas = Integer.parseInt(separate[1]);

        for (int i = 0; i<qtdArestas;i++){
            buffer = input.nextLine();
            separate = buffer.split(" ");

            if(getVertice(g,separate[0]) == null){
                g.addVertice(separate[0]);
            }

            if(getVertice(g,separate[1]) == null){
                g.addVertice(separate[1]);
            }
            g.addAresta(getVertice(g,separate[0]),getVertice(g,separate[1]),Double.parseDouble(separate[2]));
        }
        do {
            buffer = input.nextLine();
            separate = buffer.split(" ");
            if (separate[0].equals("0") && separate[1].equals("0")) {
                break;
            }  
            if(bellmanFord(g,getVertice(g,separate[1]),getVertice(g,separate[0]))){
                //printarCaminho(Objects.requireNonNull(getVertice(g, separate[1])));
                System.out.println(getVertice(g, separate[1]).peso);
            }else{
                System.out.println("Existe ciclo negativo");
            }
            
            if (djikstra2(g,getVertice(g,separate[1]),getVertice(g,separate[0]))){
                //printarCaminho(Objects.requireNonNull(getVertice(g, separate[1])));
                aux = getVertice(g, separate[1]);
                if(aux != null){
                    if (aux.peso != 10000) {
                        System.out.println(getVertice(g, separate[1]).peso);
                    }else{
                        System.out.println("Não existe caminho.");
                    }
                }else{
                    System.out.println("Não existe caminho.");
                }
            }
        }while (!separate[0].equals("0") && !separate[1].equals("0"));
    }
}
