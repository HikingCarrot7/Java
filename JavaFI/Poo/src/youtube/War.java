package youtube;

import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class War
{

    public static final Scanner INPUT = new Scanner(System.in);

    private Queue<Card> hand1;
    private Queue<Card> hand2;

    public War()
    {
        //hand1 = new ArrayQueue<Card>();
        //hand2 = new ArrayQueue<Card>();

        Deck deck = new Deck();

        deck.shuffle();

        while (!(deck.isEmpty()))
        {
            hand1.add(deck.deal());
            hand2.add(deck.deal());
        }
    }

    public static void main(String args[])
    {

        System.out.println("Welcome to War.");
        War game = new War();
        game.play();
    }

    public void play()
    {
        while (!(hand1.isEmpty() || hand2.isEmpty()))
        {
            System.out.print("\nHit return to play round: ");
            INPUT.nextLine();
            playRound();

            if (hand1.isEmpty())
                System.out.println("Player 2 wins!");

            if (hand2.isEmpty())
                System.out.println("Player 1 wins!");
        }

    }

    public void playRound()
    {
        Stack<Card> stack1 = new ArrayStack<>();
        Stack<Card> stack2 = new ArrayStack<>();

        stack1.push(hand1.remove());
        stack2.push(hand2.remove());

        do
        {
            Card card1 = stack1.peek();
            Card card2 = stack2.peek();

            System.out.println(card1 + " " + card2);

            Queue<Card> winner = null;

            if (card1.getRank() > card2.getRank())
                winner = hand1;

            if (card1.getRank() < card2.getRank())
                winner = hand2;

            if (winner != null)
            {
                give(stack1, stack2, winner);
                return;
            }

        } while (!settledByWar(stack1, stack2));

    }

    public boolean settledByWar(Stack stack1, Stack stack2)
    {
        System.out.println("War!");
        for (int i = 0; i < 4; i++)
        {
            if (hand1.isEmpty())
            {
                give(stack1, stack2, hand2);
                return true;
            }

            //stack1.push(hand1.remove());
            if (hand2.isEmpty())
            {
                give(stack1, stack2, hand1);
                return true;
            }

            stack2.push(hand2.remove());
        }

        return false;

    }

    public void give(Stack<Card> stack1, Stack<Card> stack2, Queue<Card> winner)
    {

        if (winner == hand1)
            System.out.println("Player 1 gets the cards");
        else
            System.out.println("Player 2 gets the cards");

        while (!(stack1.isEmpty()))
            winner.add(stack1.pop());

        while (!(stack2.isEmpty()))
            winner.add(stack2.pop());
    }

}
