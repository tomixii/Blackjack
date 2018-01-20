import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player extends Instance{
    ArrayList<Card> hand;
    int handWidth;
    int bet = 0;
    Deck deck;
    Button handV;
    boolean turn = true;
    int bank = 20;
    boolean win;

    public Player(int x, int y, ArrayList<Card> hand, Deck deck, PApplet p){
        super(x, y, p);
        this.hand = hand;
        this.deck = deck;
        //handV = new Button(p.width/2, 500, 100, Integer.toString(handValue()), p);
    }

    public void hitMe(){
        hand.add(deck.deck.pop());
    }

    public void stand(){
        this.turn = false;
    }

    public void doubleDown(){
        bet *= 2;
        hitMe();
        stand();
    }

    public void split(){

    }

    void reset(){
        hand.clear();
        this.turn = true;
    }

    void win(){
        bet *= 2;
        win = true;
    }

    void update() {
        handWidth = 105 + (hand.size() - 1) * 20;
        this.x = p.width/2 - handWidth/2;
        for(int i = 0; i < hand.size(); i++){
            Card card = hand.get(i);
            card.x = this.x + i*20;
            card.y = this.y;
        }
       // handV.text = Integer.toString(handValue());
    }

    void draw() {

        for(Card card: hand){
            card.draw();
        }
        p.fill(100, 120, 150, 150);
        p.textSize(40);
        p.rect(p.width/2 - 50, p.height*7/8, 100, 60);
        p.rect(p.width/2 - 50, p.height*9/16, 100, 60);
        p.fill(0);
        p.text(handValue(), p.width/2, p.height*7/8 + 45);
        p.text(bet, p.width/2, p.height*9/16 + 45);
        p.text(bank, p.width*5/6, p.height - 10);
    }

    int handValue() {
        int sum = 0;
        ArrayList<Card> sorted =new ArrayList<>(hand);
        sorted.sort( new Comparator<Card>(){
            public int compare(Card o1, Card o2){
                if(o1.value == o2.value)
                    return 0;
                return o1.value > o2.value ? -1 : 1;
            }
        });
        for(Card card: sorted){
            if(card.realValue() == 1 && sum + 11 < 21){
                sum += 11;
            }else{
                sum += card.realValue();
            }
        }
        return sum;
    }
}
