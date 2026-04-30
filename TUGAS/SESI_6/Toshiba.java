

public class Toshiba implements Laptop{
    private int volume;
    private boolean isPowerOn;

    public Toshiba(){
        this.volume=50;
    }

    public int getVolume(){
        return this.volume;
    }

    @Override
    public void powerOn(){
        isPowerOn=true;
        System.out.println("Toshiba is On");
    }

    @Override
    public void powerOff() {
        isPowerOn=false;
        System.out.println("Shutting Down");
    }

    @Override
    public void volumeUp() {
        if (isPowerOn) {
            if(volume==maxVol){
                System.out.println("Volume is Max");
            }else{
                this.volume+=10;
                System.out.println("Volume is: "+ this.getVolume());
            }
        }else{
            System.out.println("Power Off");
        }
    }

    @Override
    public void volumeDown() {
        if (isPowerOn) {
            if (this.volume==minVol) {
                System.out.println("Volume is 0%");
            } else{
                this.volume-=10;
                System.out.println("Volume is: "+this.getVolume());
            }
        }else{
            System.out.println("Power Off");
        }
    }
}
