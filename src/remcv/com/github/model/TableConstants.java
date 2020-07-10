package remcv.com.github.model;

public interface TableConstants
{
    public static final String ID = "id";

    // fields - first table
    public static final String TABLE_PATIENTS_INFO = "patients_info";
    public static final String PATIENTS_COLUMN_CODENAME = "codename";
    public static final String PATIENTS_COLUMN_AGE = "age";
    public static final String PATIENTS_COLUMN_GENDER = "gender";

    // fields - second table
    public static final String TABLE_VISITS = "visits";
    public static final String VISITS_COLUMN_PATIENT_ID = "patient_id";
    public static final String VISITS_COLUMN_VISIT = "visit";
    public static final String VISITS_COLUMN_APRI = "apri";
    public static final String VISITS_COLUMN_FIB4 = "fib4";
    public static final String VISITS_COLUMN_FIBROTEST_CATEG = "fibroTest_categ";
    public static final String VISITS_COLUMN_FIBROTEST_SCORE = "fibroTest_score";
}
