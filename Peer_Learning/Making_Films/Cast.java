class Cast {
    public final String star;
    public int awards;
    public Cast(String name, int award) {
        this.star = name; 
        this.awards = award;
    } 
    public int getAwards() {
        return this.awards;
    }
    public void update(int n) {
        this.awards = this.awards + n;
    }
 
    public String toString() {
        String s1 = new String();
        s1 = this.star + " (Cast)";
        return s1;
    }
} 
