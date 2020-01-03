class Trade {
    public final int time;
    public final int cmd;
    public final int units;
    public final int price;
    public int id;
    public Trade(int time, int cmd, int units, int price) {
        this.time = time;
        this.cmd = cmd;
        this.units = units;
        this.price = price;
        this.id = 0;
    }
    public void setID(int id) {
        this.id = id;
    }
    public int getcommand() {
        return this.cmd;
    }
    public int gettime() {
        return this.time;
    }
    public int getvol() {
        return this.units;
    }
    public int getprice() {
        return this.price;
    }
    public int getID() {
        return this.id;
    }
}
