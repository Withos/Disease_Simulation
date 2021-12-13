# Disease_Simulation
 A simple simulation of an airborne disease in a small area
 
 We're simulating an area crowded with people that are going in random directions.
 When they reach the border of said area they can either go out of it or turn back.
 People can be in set number of states, represented by different colors in the simulation. 
 Firstly, they can be either immune (cyan) to the disease or not.
 If they're not immune they can be healthy (green) or sick.
 Sick ones can either have symptoms (red) or not (orange).
 Healthy people can be infected by sick ones when they're too close to each other (approximately 3m) for longer than 3 seconds.
 Person without symptoms has 50% chance of infecting someone.
 Simulation can start either from scratch or from previous state saved in xml file.
 If you chose clean start you can chose between starting population with or without some innate immunity.
