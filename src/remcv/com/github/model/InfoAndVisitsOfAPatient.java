/* example query
SELECT pi.id, pi.codename, pi.age, pi.gender, v.visit, v.apri, v.fib4, v.fibroTest_categ, v.fibroTest_score
FROM patients_info pi
INNER JOIN visits v
ON pi.id = v.patient_id
WHERE pi.id = 1
 */

package remcv.com.github.model;

public class InfoAndVisitsOfAPatient
{
    // fields
    private int patient_id;
    private String codename;
    private int age;
    private String gender;
    private String visit;
    private double apri;
    private double fib4;
    private String fibroTest_categ;
    private double fibroTest_score;

    // constructor
    public InfoAndVisitsOfAPatient(int patient_id, String codename, int age, String gender, String visit,
                                   double apri, double fib4, String fibroTest_categ, double fibroTest_score)
    {
        this.patient_id = patient_id;
        this.codename = codename;
        this.age = age;
        this.gender = gender;
        this.visit = visit;
        this.apri = apri;
        this.fib4 = fib4;
        this.fibroTest_categ = fibroTest_categ;
        this.fibroTest_score = fibroTest_score;
    }

    // methods
    @Override
    public String toString()
    {
        return patient_id + " | " + codename + " | " + age + " | " + gender + " | " + visit + " | " +
                apri + " | " + fib4 + " | " + fibroTest_categ + " | " + fibroTest_score;
    }
}
