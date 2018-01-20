import processing.core.*;
// suits: 0 = hearts, 1 = spades, 2 = diamonds, 3 = clubs
public class Card extends Instance{
    int value, suit, number;
    PImage img;

    public Card(int value, int suit, int number, int x, int y, int width, int height, PApplet p) {
        super(x,y,p);
        this.width = width;
        this.height = height;
        this.value = value;
        this.suit = suit;
        this.number = number;
        img = p.loadImage(parseFilename());
    }

    public void draw(){
        p.image(img,x,y);
    }

    int realValue(){
        if(value > 10){
            return 10;
        }else{
            return value;
        }
    }

    public int compareTo(Card o) {
        return (this.value - o.value);
    }

    private String parseFilename(){
        String front;
        String back;
        switch (value) {
            case 1:
                front = "ace";
                break;
            case 11:
                front = "jack";
                break;
            case 12:
                front = "queen";
                break;
            case 13:
                front = "king";
                break;
            default:
                front = Integer.toString(value);
        }
        switch (suit) {
            case 0:
                back = "hearts";
                break;
            case 1:
                back = "spades";
                break;
            case 2:
                back = "diamonds";
                break;
            case 3:
                back = "clubs";
                break;
            default:
                back = "error";
        }
        return front + "_of_" + back + ".png";
    }


}
