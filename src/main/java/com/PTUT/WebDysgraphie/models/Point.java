package com.PTUT.WebDysgraphie.models;


public class Point implements Comparable {
    private int x;
    private int y;
    private int num;
    private int inter;
    private int tps;

    public Point() {
    }

    public Point(int x, int y, int num, int inter, int tps){
        this.x = x;
        this.y = y;
        this.inter = inter;
        this.num = num;
        this.tps = tps;
    }

    /**
     * Calcule la distance entre 2 points
     * @param p
     * @return la distance entre 2 points
     */
    public double distanceAvec(Point p){
        return Math.sqrt(Math.pow(p.x-this.x,2)+Math.pow(p.y-this.y, 2));
    }

    /**
     * Calcule la vitesse entre this et le point p
     * @param p
     * @return vitesse
     */
    public double vitesseEntre(Point p){
        int interval;
        if(this.num>p.num){
            interval = p.inter;
        }
        else{
            interval = this.inter;
        }
        return p.distanceAvec(this)/((double)interval);
    }

    public int IntervalleEntre(Point pAv) {
        int inter;
        inter = this.tps - pAv.tps;
        return inter;
    }



    @Override
    public int compareTo(Object o) {
        Point p = (Point)o;
        return this.num-p.num;
    }

    @Override
    public String toString(){
        return "N°"+num+"\tx:"+x+"\ty:"+y+"\tinter:"+inter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNum() {
        return num;
    }

    public int getInter() {
        return inter;
    }

    public int getTps() {
        return tps;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setInter(int inter) {
        this.inter = inter;
    }

    public void setTps(int tps) {
        this.tps = tps;
    }



}
