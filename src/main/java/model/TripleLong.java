package model;

/**
 * Created by Tom on 18.03.2017.
 */
public class TripleLong {

    private Long a;
    private Long b;
    private Long c;

    public TripleLong(Long a, Long b, Long c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Long getA() {
        return a;
    }

    public Long getB() {
        return b;
    }

    public Long getC() {
        return c;
    }

    @Override
    public int hashCode() {
        return (a.toString()+'a'+b.toString()+'b'+c.toString()+'c').hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj)
                return true;
        TripleLong tripleLong = (TripleLong) obj;
        if (a == tripleLong.getA() && b == tripleLong.getB() && c == tripleLong.getC())
            return true;
        return false;
    }
}
