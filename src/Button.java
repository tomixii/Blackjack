import processing.core.PApplet;

public class Button extends Instance {
    int radius;
    String text;
    int textSize, textX, textY;
    int sWeight, sRadius;
    boolean active = true;
    int alpha;

    public Button(int x, int y, int radius, String text, int textSize, PApplet p){
        super(x,y,p);
        this.text = text;
        this.radius = radius;
        this.textSize = textSize;
        textX = x;
        textY = y + textSize/3;
        sWeight = radius/15;
        sRadius = 10*radius/6;
    }

    public void draw(){
        if(active)
            alpha = 200;
        else
            alpha = 70;
        p.fill(150, alpha);
        p.strokeWeight(sWeight);
        p.stroke(0,120,0);
        p.ellipse(x, y, 2*radius, 2*radius);
        p.ellipse(x, y, sRadius, sRadius);
        p.textSize(textSize);
        p.fill(0, alpha);
        p.textAlign(p.CENTER);
        p.text(text, textX, textY);
    }

}
