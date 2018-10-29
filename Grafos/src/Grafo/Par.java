public class Par < T extends Comparable < T > , U >  implements Comparable < Par < T,U > >  {
    private T t;
    private U u;
    public Par (T t, U u) { this.t = t; this.u = u; }
    public T getT() { return t; }
    public U getU() { return u; }

    public void setT(T t) {
        this.t = t;
    }

    public void setU(U u) {
        this.u = u;
    }

    public int compareTo (Par < T,U >  obj) {
        return t.compareTo (obj.getT());
    }
    public String toString() { return "{" + t + "," + u + "}"; }
}