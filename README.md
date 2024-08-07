Java's most advanced Star Wars
=========================

FOLLOWING Files are taken from the Internet:
- /resources/uiFiles/empirePixelart.jpeg [Link](https://wallpaper.mob.org/pc/image/sci_fi-star_wars-pixel_art-461022.html)
- /resources/uiFiles/rebelPixelart.png [Link](https://64.media.tumblr.com/81d7ecde8e5a31ae09dfa38adace5117/2d8352260a227498-96/s540x810/aebee7b8a67893f5b1d16f1f691ed2c7d3bb4af7.png)

HOW TO PLAY:
------------------------

	- Select a Map in the dropdown Menu

<img src="resources/tutorial/tut_mapselection.png" width="250" height="150">

	- Resize the Map with the slider and click on Resize Map. If size is ok click on Start Game

<img src="resources/tutorial/resize.png" width="900" height="50">

	- The Empire begins
	- Select a Unit
	- You can move the Unit to the white highlighted Tiles

<img src="resources/tutorial/move_unit.png" width="600" height="300">

	- If an enemy Unit is in attack range it is highlighted red
	- Every Unit can move once and attack once (both is possible) per turn
	
<img src="resources/tutorial/attack.gif" width="300" height="300">

	- Finish your Round pressing the Next Round Button
	- Next turn for the Rebellion
	- If no Enemy Unit is remaining on the field press the Next Round Button to win the game

HOW TO MODIFY THE GAME:
------------------------

General Game Values (TAGS and MAP BUILDING) found in /modification/default_values.txt

**How to add a new Unit:**

To add a new Unit:

	1. CHOOSE a TAG Name for the .unit file {TAG}.unit
	2. ADD .unit file in /unit_stats following the orders in the /modification/unit_file_template.txt file
	3. ADD .png file in /units in format: {TAG}{FACTION (E/R)}.png for both Factions
	4. ADD every change to /modification/default_values.txt for Documentation
	
To add the new Unit to the Map Creator:

	1. ADD both (for every Faction) TAGs to [UNIT_TAGS:] in the /modification/Map_Creator.txt file 
	
**How to add a new Tile:**

To add a new Tile:

	1. CHOOSE a TAG for the Tile
	2. ADD the TAG to the corresponding ClassType (see /modification/default_values.txt) in /modification/Class_Types.txt
	3. ADD a .png file to /groundTiles named {TAG}.png
	4. ADD every change to /modification/default_values.txt for Documentation

To add the new Tile to the Map Creator:

	1. ADD the TAG to [TILE_TAGS:] in the /modification/Map_Creator.txt file
	


-------------------------
The only place in java where you can fight Advanced Wars in a galaxy far far away!! \
![Beautiful picture](resources/units/FE.png "Tie Fighter") ![Beautiful picture](resources/units/FR.png "X-Wing")
