# SMB-Bingo-Generator
Generates a JSON string to be used for a Monkey Ball Bingo on Bingosync

How to use this program:

1. Adding goals: To add a goal, run the BingoGen program and type 2 on the Main Menu. It will prompt you to add a goal, 
which is the goal you want to show up on the bingo card. Then it will prompt you to add a length, which is how long the
goal takes to complete. For this prompt, use short, medium or long for the length values. Additionally, you can edit the
goals.txt text file to add goals. The format for each line is "Goal name,length".
2. Generating a JSON String: To generate the string for Bingosync, run the BingoGen program and type 1 on the Main Menu.
Then choose which type of bingo you want to play, using 1 for normal bingo, 2 for short bingo and 3 for long bingo. It
will then generate a JSON string for you.
3. Playing the Bingo: Copy and paste the JSON string and go to bingosync.com. Create a room with the Game set to Custom 
(Advanced) and the Board set to the JSON string created by BingoGen program. A board with 25 goals should be ready to
play. If you want to use a new board, hit new card on the right side and put in a new JSON string.