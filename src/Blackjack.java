import processing.core.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Blackjack extends PApplet {
    State state = State.STARTMENU;
    Mouse mouse = new Mouse(this);
    Player player;
    Deck deck;
    private Button play, stand, hitme, doubleDown, split, deal, plus, minus, nextround;
    Dealer dealer;
    ArrayList<Button> inGameButtons = new ArrayList<>();
    ArrayList<Button> preGameButtons = new ArrayList<>();
    int counter;

    public enum State {
        PREGAME, GAME, STARTMENU, PAUSE, POSTGAME
    }

    public void settings() {
        size(600, 800);
    }

    public void setup() {
        deck = new Deck(8, this);
        deck.shuffle();
        player = new Player(width / 2, height * 11 / 16, new ArrayList<>(), deck, this);
        dealer = new Dealer(width / 2, height / 16, new ArrayList<>(), deck, this);
        play = new Button(width / 2, height / 2, width / 4,  "PLAY", 60, this);
        deal = new Button(width / 2, height * 3 / 4, width / 8, "DEAL", 30, this);
        plus = new Button(width * 2 / 3, height * 19 / 32, width / 16, "+", 40, this);
        minus = new Button(width / 3, height * 19 / 32, width / 16, "-", 40, this);
        stand = new Button(width * 5 / 6, height * 10 / 16, width / 8, "STAND", 30, this);
        hitme = new Button(width * 5 / 6, height * 13 / 16, width / 8, "HIT ME", 30, this);
        doubleDown = new Button(width / 6, height * 10 / 16, width / 8, "DOUBLE", 30, this);
        split = new Button(width / 6, height * 13 / 16, width / 8, "SPLIT", 30, this);
        nextround = new Button(width / 2, height *7/ 16, width / 8, "CONTINUE", 24, this);
        inGameButtons.addAll(Arrays.asList(stand, hitme, doubleDown, split));
        preGameButtons.addAll(Arrays.asList(deal, plus, minus));
    }

    public void draw() {
        background(0, 120, 0);
        if (state == State.STARTMENU) {
            play.draw();
        } else if (state == State.PREGAME) {
            player.draw();
            for (Button btn : preGameButtons) {
                btn.draw();
            }
        } else if (state == State.GAME) {
            player.update();
            player.draw();
            for (Button btn : inGameButtons) {
                textSize(30);
                btn.draw();
            }
            if (player.turn) {
                if(player.handValue() == 21){
                    player.stand();
                    changeButtonStatus();
                }

                if (player.handValue() > 21) {
                    state = State.POSTGAME;
                    changeButtonStatus();
                    player.win = false;
                    counter = 0;
                }
            } else {
                dealer.update();
                dealer.draw();
                if(counter == 90){
                    counter = 0;
                    if(dealer.handValue() < 17){
                        dealer.hand.add(deck.deck.pop());
                    }else{
                        endRound();
                    }
                }
                counter++;

            }
        }else if(state == State.POSTGAME){
            if(counter == 10){
                counter = 0;
                if(player.bet > 0){
                    player.bet--;
                    if(player.win) player.bank++;
                }
            }
            player.draw();
            dealer.draw();
            nextround.draw();
            counter++;
        }


        noFill();
        stroke(255);
        strokeWeight(3);
        ellipse(mouseX, mouseY, 20, 20);
    }

    void endRound(){
        System.out.println("player " + player.handValue() + "    dealer" + dealer.handValue());
        if(player.handValue() > dealer.handValue() || dealer.handValue() > 21){
            player.win();
            System.out.println(player.win);
        }else{
            player.win = false;
        }
        counter = 0;
        state = State.POSTGAME;
    }


    public void changeButtonStatus() {
        for (Button btn : inGameButtons) {
            btn.active = !btn.active;
        }
        counter = 0;
    }

    public void keyPressed() {
    }

    public void mouseClicked() {
        if (state == State.STARTMENU) {
            if (mouse.hoverCircle(play)) {
                state = State.PREGAME;
            }
        }else if(state == State.PREGAME){
            if(mouse.hoverCircle(deal) ){
                if(player.bet > 0){
                    state = State.GAME;
                    dealer.initDeal(player);
                }
            }else if (mouse.hoverCircle(plus)){
                if(player.bank > 0){
                    player.bet++;
                    player.bank--;
                }
            }else if(mouse.hoverCircle(minus)){
                if(player.bet > 0){
                    player.bet--;
                    player.bank++;
                }
            }
        }else if (state == State.GAME) {
            if (player.turn) {
                if (mouse.hoverCircle(stand)) {
                    player.stand();
                    changeButtonStatus();
                } else if (mouse.hoverCircle(hitme)) {
                    player.hitMe();
                } else if (mouse.hoverCircle(doubleDown)) {
                    player.doubleDown();
                    changeButtonStatus();
                } else if (mouse.hoverCircle(split)) {
                    player.split();
                }
            }
        }else if(state == State.POSTGAME){
            if(mouse.hoverCircle(nextround) && player.bet == 0){
                player.reset();
                dealer.reset();
                state = State.PREGAME;
                changeButtonStatus();
            }
        }

    }

    public static void main(String... args) {
        PApplet.main("Blackjack");
    }
}