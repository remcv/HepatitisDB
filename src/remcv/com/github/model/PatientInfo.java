package remcv.com.github.model;

public class PatientInfo
{
    // fields
    private int id;
    private String codename;
    private int age;
    private char gender;


    // constructor
    public PatientInfo(int id, String codename, int age, char gender)
    {
        this.id = id;
        this.codename = codename;
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

    public String getCodename()
    {
        return codename;
    }

    public void setCodename(String codename)
    {
        this.codename = codename;
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

    // methods - toString()
    @Override
    public String toString()
    {
        return id + " | codename: " + codename + " | age: " + age + " | gender: " + gender;
    }
}
