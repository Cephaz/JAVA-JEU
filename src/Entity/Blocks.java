package Entity;

import GameState.GameState;
import TileMap.ImageLoader;
import TileMap.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Blocks extends GameState {
    private BufferedImage blocks;
    private SpriteSheet sheet;

    private static ArrayList<ArrayList<Blocks>> blockss = new ArrayList<>();
    private static ArrayList<Blocks> bloc;

    // Particularite de la map
    private boolean mur = false;
    private boolean piege = false;
    private boolean portail = false;

    //Position choisix du sprit asset
    private int posX;
    private int posY;

    //variable inchangeable du sprit asset
    private int fixewidth = 32;
    private int fixeheight = 32;
    private int i;
    private int i1;
    private int debutx = 74;

    //definition des cases de la maps
    public Blocks(int pos, int i, int i1) {
        if (pos == 50){
            piege = true;
        }
        if (pos == 613){
            portail = true;
        }
        if (pos > 99){
            this.posX = pos % 100;
            this.posY = pos / 100;
            if (this.posX == 5)
                mur = true;
        }
        if (pos < 99){
            this.posX = pos % 10;
            this.posY = pos / 10;
            if (this.posX == 3 ||
                this.posX == 4 && this.posY > 1)
                mur = true;
        }
        this.i = i;
        this.i1 = i1;
        init();
    }
    public void init() {
        blocks = ImageLoader.loadImage("map.png");
        sheet = new SpriteSheet(blocks);
    }
    public static void readLines(File f) throws IOException{
        int ll = 0;
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null) {
            List<Integer> ints = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            receiveBlocks(ints, ll);
            ll++;
        }
        br.close();
        fr.close();
    }
    public static void receiveBlocks(List<Integer> nombre, int ligne){
        bloc = new ArrayList<>();
        int i = 0;
        for(int nbr:nombre){
            bloc.add(new Blocks(nbr,i,ligne));
            i++;
        }
        blockss.add(ligne,bloc);
    }
    public void update() {

    }
    public void draw(Graphics2D g) {
        g.drawImage(sheet.crop(debutx + fixewidth*posX + posX,fixeheight*posY + posY,fixewidth,fixeheight), i*32, i1*32, null);
    }
    public void keyPressed(int k) throws IOException {

    }
    public void keyReleased(int k) {

    }
    public  boolean getPiege(){ return piege; }
    public boolean getPortail(){
        return portail;
    }
    public int getPosX(){ return i; }
    public int getPosY(){ return i1; }
    public static ArrayList<ArrayList<Blocks>> getBlockss() {
        return blockss;
    }
    public boolean getMur(){
        return mur;
    }
}
