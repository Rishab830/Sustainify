package hashTable;
import java.io.Serial;
import java.io.Serializable;

public class Node implements Serializable{

    public Node(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Serial
    private static final long serialVersionUID = -6151325394834770705L;
    public String username;
    public String password;
    public int budget;
    public double Emin,Smin,SEmin,Eoptmin,Soptmin,SEoptmin,Eoptmax,Soptmax,SEoptmax,Zopt,rfopt;
    public int[][]Xopt;
    Node next;

    public void insert(Object[] result,double Emin,double Smin,double SEmin){
        this.Zopt = (double)result[0];
        this.Xopt = (int[][])result[1];
        this.Eoptmin = (double)result[2];
        this.Soptmin = (double)result[3];
        this.SEoptmin = (double)result[4];
        this.Eoptmax = (double)result[5];
        this.Soptmax = (double)result[6];
        this.SEoptmax = (double)result[7];
        this.budget = (int)result[8];
        this.rfopt = (double)result[9];
        this.Emin = Emin;
        this.Smin = Smin;
        this.SEmin = SEmin;
    }
}
