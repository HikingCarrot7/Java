package youtube;

public class Domineering
{

    private final boolean[][] squares;

    public Domineering()
    {
        squares = new boolean[8][8];
    }

    public static void main(String args[])
    {
        System.out.println("Welcome to Domineering.");

        Domineering game = new Domineering();

        game.play();

    }

    @Override
    public String toString()
    {
        String result = " 0 1 2 3 4 5 6 7";

        for (int row = 0; row < 8; row++)
        {
            result += "\n" + row;

            for (int column = 0; column < 8; column++)
                if (squares[row][column])
                    result += " #";
                else
                    result += " .";
        }

        return result;
    }

    public static final java.util.Scanner INPUT = new java.util.Scanner(System.in);

    public static final boolean HORIZONTAL = false;
    public static final boolean VERTICAL = true;

    public void play()
    {
        boolean player = HORIZONTAL;

        while (true)
        {
            System.out.println("\n" + this);

            if (player == HORIZONTAL)
                System.out.println("Horizontal to play");

            else
                System.out.println("Vertical to play");

            if (!(hasLegalMoveFor(player)))
            {
                System.out.println("No legal moves -- you lose!");

                return;
            }

            System.out.print("Row: ");
            int row = INPUT.nextInt();

            System.out.print("Column: ");
            int column = INPUT.nextInt();

            playAt(row, column, player);

            player = !player;

        }

    }

    public void playAt(int row, int column, boolean player)
    {
        squares[row][column] = true;

        if (player == HORIZONTAL)
            squares[row][column + 1] = true;

        else
            squares[row + 1][column] = true;

    }

    public boolean hasLegalMoveFor(boolean player)
    {
        int rowOffset = 0;
        int columnOffset = 0;

        if (player == HORIZONTAL)
            columnOffset = 1;

        else
            rowOffset = 1;

        for (int row = 0; row < (8 - rowOffset); row++)
            for (int column = 0; column < (8 - columnOffset); column++)
                if (!(squares[row][column] || squares[row + rowOffset][column + columnOffset]))
                    return true;

        return false;

    }

    public interface Domino
    {

        public void flip();

        public int getLeft();

        public int getRight();

    }

}
