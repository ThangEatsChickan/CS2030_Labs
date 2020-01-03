Internship ubsIntern = new Internship("UBS Intern", 2.8)
Internship fbIntern = new Internship("Facebook Intern", 3.0)
Internship googleIntern = new Internship("Google Intern" , 4.5)
Student jack = new Student("jack", new String[] {"A","A+","A-"}, 
             new Internship[] {ubsIntern, googleIntern})
Student jane = new Student("jane", new String[] {"A","A+","A"},
                 new Internship[] {ubsIntern})
Student tom = new Student("tom", new String[] {"A","C+","A","B+"},
                 new Internship[] {googleIntern})
Job google = new Job("Google Software Engineer", 9.0, 2)
Job dbs = new Job("DBS Analyst", 7.0, 2)
google.apply(new Student[] {jack, jane, tom})
dbs.apply(new Student[] {jack, jane, tom})
google.hire()
dbs.hire()
/exit
