package remcv.com.github.model;

public class MarkersAtAnEvaluation
{
    // fields
    private int id;
    private int patient_id;
    private String visit;
    private double apri;
    private double fib4;
    private String fibroTest_categ;
    private double fibroTest_score;

    // constructor
    public MarkersAtAnEvaluation(int id, int patient_id, String visit, double apri, double fib4,
                                 String fibroTest_categ, double fibroTest_score)
    {
        this.id = id;
        this.patient_id = patient_id;
        this.visit = visit;
        this.apri = apri;
        this.fib4 = fib4;
        this.fibroTest_categ = fibroTest_categ;
        this.fibroTest_score = fibroTest_score;
    }

    // methods - setters & getters
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPatient_id()
    {
        return patient_id;
    }

    public void setPatient_id(int patient_id)
    {
        this.patient_id = patient_id;
    }

    public String getVisit()
    {
        return visit;
    }

    public void setVisit(String visit)
    {
        this.visit = visit;
    }

    public double getApri()
    {
        return apri;
    }

    public void setApri(double apri)
    {
        this.apri = apri;
    }

    public double getFib4()
    {
        return fib4;
    }

    public void setFib4(double fib4)
    {
        this.fib4 = fib4;
    }

    public String getFibroTest_categ()
    {
        return fibroTest_categ;
    }

    public void setFibroTest_categ(String fibroTest_categ)
    {
        this.fibroTest_categ = fibroTest_categ;
    }

    public double getFibroTest_score()
    {
        return fibroTest_score;
    }

    public void setFibroTest_score(double fibroTest_score)
    {
        this.fibroTest_score = fibroTest_score;
    }

    // methods - toString()
    @Override
    public String toString()
    {
        return id + " | patient_id: " + patient_id + " | visit: " + visit +
                " | apri: " + apri + " | fib4: " + fib4 + " | fibroTest_categ: " +
                fibroTest_categ + " | fibroTest_score: " + fibroTest_score;
    }
}
