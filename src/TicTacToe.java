
import javax.swing.JOptionPane;

/**
 * 
 * @author Sam Te
 */
public class TicTacToe
{
    public static void main(String[] args)
    {
        String[][] game = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}};
        boolean victoryPlayer = false;
        boolean victoryComputer = false;
        boolean legitMove = false;
        boolean computerMove = false;  // flag for computer win condition
        String gameBox;
        int input;
        int turn = 0; // exit trigger
        
        //System.out.println(game[0][0].);
        
        //starting msg
        JOptionPane.showMessageDialog(null, "Welcome to Tic Tac Toe."
                            + "\nYou will have X and the computer will have O");
        // The game
        while (!victoryPlayer && !victoryComputer)
        {
            // p1 action
            input = 0;
            legitMove = false;
            gameBox = updateBox(game);
            do
            {
                input = Integer.parseInt(
                        JOptionPane.showInputDialog("Enter the Position where "
                                                             + "you want to play\n"
                                                             + gameBox));

                //Check for victory and update game box
                legitMove = inputMove(game, input, "X");
            } while (!legitMove);
            
            gameBox = updateBox(game);
            victoryPlayer = checkVictory(game);
            ++turn;
            if (turn == 9)
                break;
            if (victoryPlayer)
                break;
            
            //display updated message
            JOptionPane.showMessageDialog(null, "Your move is recored;"
                                        + "\nNow the computer will play\n\n"
                                        + gameBox);
            
            //Computers turn
            legitMove = false;
            input = 0;
            do
            {
                input = checkConsectives(game, "O");
                if (input == 0)
                {
                    input = (1 + (int)(Math.random() * 9));
                    legitMove = inputMove(game, input, "O");
                }   
                else
                {
                    legitMove = inputMove(game, input, "O");
                }
            } while (!legitMove);
            gameBox = updateBox(game);
            victoryComputer = checkVictory(game);
            ++turn;
            
            // mesage about computer turn
            JOptionPane.showMessageDialog(null, "The computer has played\n\n"
                                        + gameBox); 
        }
        
        if (victoryPlayer)
            JOptionPane.showMessageDialog(null,"Congratulations You Have WON!!");
        else if (turn == 9)
            JOptionPane.showMessageDialog(null,"It is a tie");
        else
            JOptionPane.showMessageDialog(null,"You have LOST!! U SUCK");
    }
    
    public static String updateBox(String[][] game)
    {
        String gameBox = "";
        for (int i = 0; i < game.length; ++i )
        {
            for (int j = 0; j < game[i].length; ++j)
                gameBox += game[i][j] + " ";
            gameBox += "\n";
        }
        return gameBox;
    }
    
    public static boolean inputMove (String[][] game, int input, String move)
    {
        switch (input)
        {
            case 1:
                if (Character.isDigit(game[0][0].charAt(0)))
                {
                    game[0][0] = move;
                    return true;
                }
                else
                    return false;
            case 2:
                if (Character.isDigit(game[0][1].charAt(0)))
                {
                    game[0][1] = move;
                    return true;
                }
                else
                    return false;
            case 3:
                if (Character.isDigit(game[0][2].charAt(0)))
                {
                    game[0][2] = move;
                    return true;
                }
                else
                    return false;
            case 4:
                if (Character.isDigit(game[1][0].charAt(0)))
                {
                    game[1][0] = move;
                    return true;
                }
                else
                    return false;
            case 5:
                if (Character.isDigit(game[1][1].charAt(0)))
                {
                    game[1][1] = move;
                    return true;
                }
                else
                    return false;
            case 6:
                if (Character.isDigit(game[1][2].charAt(0)))
                {
                    game[1][2] = move;
                    return true;
                }
                else
                    return false;
            case 7:
                if (Character.isDigit(game[2][0].charAt(0)))
                {
                    game[2][0] = move;
                    return true;
                }
                else
                    return false;
            case 8:
                if (Character.isDigit(game[2][1].charAt(0)))
                {
                    game[2][1] = move;
                    return true;
                }
                else
                    return false;
            case 9:
                if (Character.isDigit(game[2][2].charAt(0)))
                {
                    game[2][2] = move;
                    return true;
                }
                else
                    return false;
            default:
                return false;
        }
    }
    
    public static boolean checkVictory(String[][] game)
    {
        // game[x][0 1 2] == same = win
        // game[0 1 2][x] == same = win
        // game[0][0] == game[1][1] == game[2][2] = win
        // game[0][2] == game[1][1] == game[2][0] = win
        boolean found = false;
        for (int i = 0; i < game.length; ++i)
        {
            if (game[i][0].equals(game[i][1]) && game[i][1].equals(game[i][2]))
            {
                found = true;
                break;
            }
            if (game[0][i].equals(game[1][i]) && game[1][i].equals(game[2][i]))
            {
                found = true;
                break;
            }
        }
        if (game[0][0].equals(game[1][1]) && game[1][1].equals(game[2][2]))
            found = true;
        if (game[0][2].equals(game[1][1]) && game[1][1].equals(game[2][0]))
            found = true;
        return found;
    }
    
    public static int checkConsectives(String[][] game, String mark)
    {
        // 1 2 3
        // 4 5 6
        // 7 8 9
        // if game[i][2 3] then place 1 if [1 3] then palce 2 if 1 2 place 3??
        int input = 0;
        for (int i = 0; i < game.length; ++i)
        {
            if (game[i][1].equals(game[i][2]))
            {
                if(Character.isDigit(game[i][0].charAt(0)))
                    input = (i * 3) + 1;
            }
            if (game[i][0].equals(game[i][2]))
            {
                if(Character.isDigit(game[i][1].charAt(0)))
                    input = (i * 3) + 2;
            }
            if (game[i][0].equals(game[i][1]))
            {
                if(Character.isDigit(game[i][2].charAt(0)))
                    input = (i * 3) + 3;
            }
            
            if (game[1][i].equals(game[2][i]))
            {
                if(Character.isDigit(game[0][i].charAt(0)))
                    input = 1 + i;
            }
            
            if (game[0][i].equals(game[2][i]))
            {
                if(Character.isDigit(game[1][i].charAt(0)))
                    input = 4 + i;
            }
            
            if (game[0][i].equals(game[1][i]))
            {
                if(Character.isDigit(game[2][i].charAt(0)))
                    input = 7 + i;
            }
        }
        
        if (game[1][1].equals(game[2][2]))
        {
            if(Character.isDigit(game[0][0].charAt(0)))
                input = 1;
        }
        
        if (game[0][0].equals(game[2][2]))
        {
            if(Character.isDigit(game[1][1].charAt(0)))
                input = 5;
        }
        
        if (game[0][0].equals(game[1][1]))
        {
            if(Character.isDigit(game[2][2].charAt(0)))
                input = 9;
        }
        
        if (game[1][1].equals(game[0][2]))
        {
            if(Character.isDigit(game[2][0].charAt(0)))
                input = 7;
        }
        
        if (game[2][0].equals(game[0][2]))
        {
            if(Character.isDigit(game[1][1].charAt(0)))
                input = 5;
        }
        
        if (game[2][0].equals(game[1][1]))
        {
            if(Character.isDigit(game[0][2].charAt(0)))
                input = 3;
        }
        if (input == 0)
            return 0;
        else
            return input;
    }
}
