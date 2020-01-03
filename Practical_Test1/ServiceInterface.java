interface ServiceInterface {
    public double getPrice();

    public String getServiceType();

    public ServiceInterface computeFare(Request req);
}
