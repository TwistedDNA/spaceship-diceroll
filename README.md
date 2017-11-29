#####TL;DR
Eclipse spaceship battle simulator and statistics. 
Run MultiShipBattleRunner for example.

#####Background
This project emerged after intense weekend of board game action.
Among others, [Eclipse](https://boardgamegeek.com/boardgame/72125/eclipse) brought lots of fun.
In this game spaceship battle results are based on dice rolls with modifications from ship upgrades. 
So I decided to make a simulation to help foresee my chances before going into hopeless fight.

#####Features
Multi ship battles are shown in MultiShipBattleRunner class. Primitive damage mealing 
strategy makes this simulation not exactly accurate. 

Single ship vs ship battles with statistics display. See OneOnOne class.

My humble tries to predict outcome of battle based on ship statistics is in ChancesVs class.
This should be the most accurate and efficient way of understanding balance of power, 
but I lack probability theory knowledge to do this right. Will come back to this later.

#####Planned improvements
Find formula to predict battle results more precisely.
Implement more efficient damage dealing strategy. 