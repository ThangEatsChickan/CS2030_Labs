/open Loader.java
/open Cruise.java
/open BigCruise.java
Cruise b = new BigCruise("B0001", 0, 2, 60);
b.getArrivalTime()
b.getServiceCompletionTime()
b.getNumLoadersRequired()
new Loader(1).serve(b).serve(b)
new Loader(2).serve(b)
new Loader(3).serve(b)
new Loader(4).serve(new BigCruise("B2345", 0, 1, 29)).serve(new Cruise("A0000", 29))
new Loader(5).serve(new BigCruise("B3456", 0, 2, 31)).serve(new Cruise("A2345", 30))
/exit
