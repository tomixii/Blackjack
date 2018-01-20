import processing.core.PApplet;

public class Mouse {
    PApplet p;
    public Mouse(PApplet p){
        this.p = p;
    }

    boolean hoverRect(Instance i) { //return true if mouse hovers inside given bounds
        return p.mouseX > i.x && p.mouseX < i.x + i.width && p.mouseY > i.y && p.mouseY < i.y + i.height;
    }

    boolean hoverCircle(Button btn) {
        return Math.sqrt(Math.pow(p.mouseX - btn.x, 2) + Math.pow(p.mouseY - btn.y, 2)) < btn.radius;
    }

}
