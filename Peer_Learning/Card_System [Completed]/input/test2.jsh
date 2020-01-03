User Thomas = new User("Thomas", 1, new Card(1,10))
Customer Elise = new Customer("Elise", 2, new Card(2, 30))
Admin Dicky = new Admin("Dicky", 3, new Card(3, 30))
Elise.checkBalance()
Elise.doTopUp(100)
Elise.doTopUp(100)
Elise = Elise.doTopUp(100)
Elise
Elise.rewardEventBonus(Elise, 300)
Dicky.rewardEventBonus(Elise, 300)
Elise
Elise = Dicky.rewardEventBonus(Elise, 300)
Elise.checkBalance()
