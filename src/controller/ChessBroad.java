package controller;

import util.ChessBroadUtil;

import java.util.*;
import java.util.List;

public class ChessBroad {

    private int roleX;

    private int roleY;

    private Location next;

    private List<Location> enemyLocations;

    private List<Location> itemLocations;

    private int[][] chessBroad = new int[ChessBroadUtil.DEFAULT_SIZE][ChessBroadUtil.DEFAULT_SIZE];

    public ChessBroad(){
        for (int i = 0; i < ChessBroadUtil.DEFAULT_SIZE; i++) {
            for (int j = 0; j < ChessBroadUtil.DEFAULT_SIZE; j++) {
                this.chessBroad[i][j] = ChessBroadUtil.ROAD;
            }
        }
        this.next = this.add(1, ChessBroadUtil.NEXT).get(0);

        Random random = new Random();
        this.roleX = random.nextInt(ChessBroadUtil.DEFAULT_SIZE);
        this.roleY = random.nextInt(ChessBroadUtil.DEFAULT_SIZE);
        this.chessBroad[roleX][roleY] = ChessBroadUtil.ROLE;
        System.out.println(this);
    }

    public Location getNext() {
        return next;
    }

    public void setNext(Location next) {
        this.next = next;
    }

    public int getRoleX() {
        return roleX;
    }

    public int getRoleY() {
        return roleY;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < ChessBroadUtil.DEFAULT_SIZE; i++) {
            for (int j = 0; j < ChessBroadUtil.DEFAULT_SIZE; j++) {
                s += this.getContent(i, j);
            }
            s += "\n";
        }
        return s;
    }

    public int getContent(int positionX, int positionY){
        if(positionX >= 0 && positionY >= 0 && positionX < ChessBroadUtil.DEFAULT_SIZE && positionY < ChessBroadUtil.DEFAULT_SIZE){
            return this.chessBroad[positionX][positionY];
        }
        return ChessBroadUtil.INVALID;
    }

    public int moveRole(int targetX, int targetY){
        int targetType = this.getContent(targetX, targetY);
        if(targetType == ChessBroadUtil.ROAD){
            this.chessBroad[roleX][roleY] = ChessBroadUtil.ROAD;
            this.roleX = targetX;
            this.roleY = targetY;
            this.chessBroad[roleX][roleY] = ChessBroadUtil.ROLE;
        }
        else if(targetType == ChessBroadUtil.ITEM || targetType == ChessBroadUtil.WEAPON){
            this.chessBroad[targetX][targetY] = ChessBroadUtil.ROAD;
            this.itemLocations.remove(new Location(targetX, targetY));
        }
        return targetType;
    }

    public void deleteEnemy(Location location){
        if(this.enemyLocations.contains(location)){
            this.enemyLocations.remove(location);
            this.chessBroad[location.getX()][location.getY()] = ChessBroadUtil.ROAD;
        }
    }

    public List<Location> addEnemy(int numOfEnemy){
        this.enemyLocations = add(numOfEnemy, ChessBroadUtil.ENEMY);
        return this.enemyLocations;
    }

    public List<Location> addItem(int numOfItem){
        this.itemLocations = add(numOfItem, ChessBroadUtil.ITEM);
        return this.itemLocations;
    }

    public void addStone(int numOfStone){
        add(numOfStone, ChessBroadUtil.STONE);
    }

    private List<Location> add(int number, int type){
        Vector<Location> locations = new Vector<>();
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int x, y;
            do {
                x = random.nextInt(ChessBroadUtil.DEFAULT_SIZE);
                y = random.nextInt(ChessBroadUtil.DEFAULT_SIZE);
            } while (this.chessBroad[x][y] != ChessBroadUtil.ROAD);
            this.chessBroad[x][y] = type;
            locations.add(new Location(x, y));
        }
        return locations;
    }

}
