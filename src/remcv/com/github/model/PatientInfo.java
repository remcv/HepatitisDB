package remcv.com.github.model;

public class PatientInfo
{
    // fields
    private int id;
    private String nameCode;
    private int age;
    private char gender;
    private String lot_CnC;
    private String lot_CirrhosisDivisions;

    // constructor
    public PatientInfo(int id, String nameCode, int age, char gender)
    {
        this.id = id;
        this.nameCode = nameCode;
        this.age = age;
        this.gender = gender;
    }

    // methods - getters & setters
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNameCode()
    {
        return nameCode;
    }

    public void setNameCode(String nameCode)
    {
        this.nameCode = nameCode;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public char getGender()
    {
        return gender;
    }

    public void setGender(char gender)
    {
        this.gender = gender;
    }

    public String getLot_CnC()
    {
        return lot_CnC;
    }

    public void setLot_CnC(String lot_CnC)
    {
        this.lot_CnC = lot_CnC;
    }

    public String getLot_CirrhosisDivisions()
    {
        return lot_CirrhosisDivisions;
    }

    public void setLot_CirrhosisDivisions(String lot_CirrhosisDivisions)
    {
        this.lot_CirrhosisDivisions = lot_CirrhosisDivisions;
    }

    // methods - toString()
    @Override
    public String toString()
    {
        return id + " " + nameCode + " | age: " + age + " | gender: " + gender;
    }
}
