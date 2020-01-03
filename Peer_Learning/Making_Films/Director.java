class Director extends Cast {
    public final String country;
    public Director(String name, int award, String country) {
        super(name, award);
        this.country = country;
    } 
 
    public String toString() {
        String s1 = new String();
        s1 = this.star + " (" + this.country + ")";
        return s1;
    }
} 
