/**
 * Created by mshz on 2017/11/13.
 */
public class TestObject {
    private int m_a;
    private int m_b;

    public TestObject(int a,int b)
    {
        m_a =a;
        m_b = b;
    }

    public int getA(){ return m_a;}
    public int getB(){ return m_b;}
    public void setA(int a){ m_a = a;}
    public void setB(int b){m_b = b;}

    public int add(){ return m_a +m_b;}
}
