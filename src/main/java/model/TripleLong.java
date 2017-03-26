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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripleLong that = (TripleLong) o;

        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        if (b != null ? !b.equals(that.b) : that.b != null) return false;
        return !(c != null ? !c.equals(that.c) : that.c != null);

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}
