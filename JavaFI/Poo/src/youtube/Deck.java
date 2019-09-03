package youtube;

public class Deck
{

    private final Card[] cards;
    private int size;

    public Deck()
    {
        cards = new Card[52];
        size = 0;

        for (int suit = Card.SPADES; suit <= Card.CLUBS; suit++)
            for (int rank = Card.ACE; rank <= Card.KING; rank++)
                cards[size++] = new Card(rank, suit);
    }

    public Card deal()
    {
        size--;
        return cards[size];
    }

    public void shuffle()
    {
        for (int i = size - 1; i > 0; i--)
            swap(i, (int) (Math.random() * (i + 1)));
    }

    protected void swap(int i, int j)
    {
        Card temp = cards[i];
        cards[i] = cards[j];
        cards[j] = temp;
    }

    public boolean isEmpty()
    {
        return cards.length == 0;
    }

}
