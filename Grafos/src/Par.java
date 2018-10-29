public class Par < T extends Comparable < T > , U >  implements Comparable < Par < T,U > >  {
    private T t;
    private U u;
    public Par (T t, U u) { this.t = t; this.u = u; }
    public T getT() { return t; }
    public U getU() { return u; }
    public int compareTo (Par < T,U >  obj) {
        return t.compareTo (obj.getT());
    }
    public String toString() { return "{" + t + "," + u + "}"; }
}