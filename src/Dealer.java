import processing.core.PApplet;

import java.util.ArrayList;

public class Dealer extends Instance {
    Deck deck;
    ArrayList<Card> hand;
    int handWidth;

    public Dealer(int x, int y, ArrayList<Card> hand, Deck deck, PApplet p){
        super(x, y, p);
        this.deck = deck;
        this.hand = hand;
    }

    void initDeal(Player player){
        player.hand.add(deck.deck.pop());
        player.hand.add(deck.deck.pop());
    }

    void reset(){
        hand.clear();
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
        p.stroke(255);
        p.strokeWeight(3);
        p.rect(p.width/2 - 50, this.y + 150, 100, 60);
        p.fill(0);
        p.text(handValue(), p.width/2, this.y + 195);
    }

    int handValue() {
        int sum = 0;
        for(Card card: hand){
            if(card.realValue() == 1 && sum < 11){
                sum += 11;
            }else{
                sum += card.realValue();
            }
        }
        return sum;
    }
}
