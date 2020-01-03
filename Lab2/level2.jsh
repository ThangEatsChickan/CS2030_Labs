/open Cruise.java
/open Loader.java
new Loader(1);
new Loader(1).serve(new Cruise("A1234", 0));
new Loader(1).serve(new Cruise("A1234", 0)).serve(new Cruise("A2345", 30));
new Loader(2).serve(new Cruise("A1245", 2330));
new Loader(2).serve(new Cruise("A1245", 2330)).serve(new Cruise("A2345", 2359));
new Loader(2).serve(new Cruise("A1245", 2330)).serve(new Cruise("A2345", 2359));
Cruise c = new Cruise("CS2030", 0);
new Loader(3).serve(c);
new Loader(3).serve(c);
/exit
