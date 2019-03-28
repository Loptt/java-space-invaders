/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 *
 * @author charles
 */
public class NetworkData {
    private int x;
    private int y;
    
    private boolean ready;
    private boolean shoot;
    private boolean alienShot;
    
    private int alienIndex;
    
    public NetworkData() {
        this.x = 0;
        this.y = 0;
        this.ready = false;
        this.shoot = false;
        alienShot = false;
        alienIndex = -1;
    }

    public NetworkData(int x, int y, boolean ready, boolean shoot) {
        this.x = x;
        this.y = y;
        this.ready = ready;
        this.shoot = shoot;
        alienShot = false;
        alienIndex = -1;
    }
    
    public NetworkData(int x, int y, boolean ready, boolean shoot, boolean alienShot,
            int alienIndex) {
        this.x = x;
        this.y = y;
        this.ready = ready;
        this.shoot = shoot;
        this.alienShot = alienShot;
        this.alienIndex = alienIndex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isReady() {
        return ready;
    }

    public boolean isShoot() {
        return shoot;
    }

    public boolean isAlienShot() {
        return alienShot;
    }

    public int getAlienIndex() {
        return alienIndex;
    }

    public void setAlienIndex(int alienIndex) {
        this.alienIndex = alienIndex;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setAlienShot(boolean alienShot) {
        this.alienShot = alienShot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
