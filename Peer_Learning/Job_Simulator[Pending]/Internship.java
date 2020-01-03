class Internship {
    public final String jobRole;
    public final double pts;
    public Internship(String jobRole, double pts) {
        this.jobRole = jobRole;
        this.pts = pts;
    }
    
    public String toString() {
        String s1 = new String();
        s1 = "\nInternship: " + this.jobRole + 
             String.format("\nPoints provided: %.1f", this.pts); 
        return s1;
    }
}
