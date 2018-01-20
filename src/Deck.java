import java.util.*;
import processing.core.*;

public class Deck {

    public int count;
    public Stack<Card> deck = new Stack<>();
    PApplet p;

    public Deck(int count, PApplet p){
        this.count = count;
        this.p = p;
    }

    public void shuffle() {
        /*
        this.deck.push(new Card(13,0,1, -200, 0, 70, 105, p));
        this.deck.push(new Card(1,1,1, -200, 0, 70, 105, p));
        this.deck.push(new Card(6,2,1, -200, 0, 70, 105, p));
        this.deck.push(new Card(1,3,1, -200, 0, 70, 105, p));
        this.deck.push(new Card(2,1,1, -200, 0, 70, 105, p));
        */

        for(int i = 0; i < this.count; i++){
            for(int j = 0; j < 52; j++){
                this.deck.push(new Card(j / 4 + 1, j % 4, j, -200, 0, 70, 105, p));
            }
        }
        Collections.shuffle(deck);
    }

}
