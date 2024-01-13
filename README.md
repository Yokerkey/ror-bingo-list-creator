# ror-bingo-list-creator
An application that creates a Bingo list for Risk of Resources Races


#Bingos.json:

Bingos that are not included in the general "Bingos" list, are: "Red item on Stage _?_", "_?_ printer" or "_item_ picked up" as I create them randomly based on the list of all possibilities.
Also not included are bingos for: "_Racer_ wins loadout" because this is calculated by an input of all participating racers.

I added a number (0 and 1) to every item to indicate whether these items are from the base game (0) or from the first dlc (1) and possible later dlcs with an ascending number.
This way it is configurable to exclude specific dlcs, in case someday there will be a race without specific dlcs.

The monsters I added in the "Monsters" list, are some of the harder monsters that can be used as a bingo if you encounter them as one of the "Elite" versions.

I also added conditions to some of the bingos:

###Condition "stages"
Can only appear if specific stages are played during the race

###Condition "boss item amount"
Ensures that we don't have multiple bingos at once that need specific amount of boss items so that we can have different versions of this bingo

###Condition "lockboxes"
Ensures that we don't have multiple bingos at once that need interactions with lockboxes so that we can have different versions of this bingo

###Condition "void seeds"
Ensures that we don't have multiple bingos at once that are tied to the occurance of void seeds so that we can have different versions of this bingo

###Condition "damage"
Ensures that we don't have multiple bingos at once that are tied to collecting multiple damage% items so that we can have different versions of this bingo

Bingos also have the modifiers "Happened" and "Appeared" to count the number of times the bingo occured on a race as well as the amount of times that bingo actually happened.
This could be used in the future (with more data) to more accurately measure how rare a specific bingo is.
For example: The bingo "Blood shrine hit" might not seem uncommon, but if we see the tendency of racers to avoid blood shrines because of a lack of healing or not needing the money, this bingo could be more rare than initially thought.

The modifier "Rarity" stands for how rare bingos are to happen during a race so that we can create a bingo list of a healthy mix of "common", "uncommon" and "rare" bingos.
The modifier "Complexity" stands for how complex the task is for racers to complete to potentially add a certain amount of bingos that are not based on luck, but on the skill of racers (experimental).