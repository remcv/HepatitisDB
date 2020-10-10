# HepatitisDB

## App logic
Simple database CRUD in the console to simulate data from patients with hepatitis C, treated with DAA - the evolution of liver fibrosis at three stages: baseline, 6 months and 1 year after treatment.

## Database info
The underlying database consists of an SQLite with two tables (one-to-many relationship):   

```sql
CREATE TABLE patients_info (
  id INTEGER PRIMARY KEY, 
  nameCode TEXT, 
  age INTEGER, 
  lot_CnC TEXT, 
  lot_Cirrhosis TEXT
);

CREATE TABLE markers (
  id INTEGER PRIMARY KEY, 
  patient_id INTEGER, 
  visit TEXT, 
  apri REAL, 
  fib4 REAL, 
  fibrotest_catg TEXT, 
  fibrotest_score REAL
);
```

## Programming / Java concepts used
SQLite, JDBC, singleton pattern, exceptions, MVC, CRUD

## Future plans
Add a UI in JavaFX or Android, or change to a web application.
