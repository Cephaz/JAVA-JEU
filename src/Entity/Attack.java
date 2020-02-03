package Entity;

public class Attack extends Thread{
    private Ennemi ennemi;
    private Player play;
    public Attack (){
    }
    public void run() {
        try {
            while (true) {
                if(ennemi != null && play != null){
                    if(ennemi.getEnnemiY() != 0 && play.getPersoY() != 0){
                    if (ennemi.getEnnemiX() == play.getPersoX() && ennemi.getEnnemiY() == play.getPersoY()) {
                        play.setHp(play.getHp() - ennemi.getDamage());
                        sleep(2000);
                    }}
                }
                sleep(300);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void verifie(Ennemi ennemi, Player play){
        this.ennemi = ennemi;
        this.play = play;
    }
}
