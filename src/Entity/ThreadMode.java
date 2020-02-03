package Entity;

public class ThreadMode extends Thread{
    private Ennemi ennemi;

    public ThreadMode (int x,int y){
        ennemi = new Ennemi(x, y);
    }
    public void run() {
        try {
            while (true) {
                ennemi.update();
                sleep(500);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public Ennemi getEnnemi(){
        return  ennemi;
    }
}
