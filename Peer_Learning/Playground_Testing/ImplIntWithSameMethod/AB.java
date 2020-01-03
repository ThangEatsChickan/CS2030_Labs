class AB implements A,B {
    public void f() {
    }  //This is needed here in order for AB to compile otherise it will complain of no method to overwrite the one in interface. The method here has to be at least public as the rest are considered as weak privileges.
}
