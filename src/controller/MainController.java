package controller;

import ability.*;
import battle.AbilityCommand;
import battle.AttackCommand;
import battle.Battle;
import item.RoleUpItemFactory;
import item.UpgradeItemFactory;
import item.UpgradeItemType;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import role.*;
import util.ChessBroadUtil;
import util.FormatUtil;
import util.InfoPaneUtil;
import util.RoleUtil;
import item.UpgradeItem;
import weapon.NormalWeaponFactory;
import weapon.Weapon;
import weapon.WeaponFactory;
import weapon.WeaponType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    
    @FXML
    private GridPane chessPane;

    @FXML
    private TabPane infoPane;

    @FXML
    private VBox battlePane;

    @FXML
    private HBox controlPane;

    @FXML
    private ScrollPane itemLog;

    private ChessBroad chessBroad;

    private UserRole userRole;

    private Battle battle = null;

    private Location enemyLocation = null;

    private HashMap<Location, Enemy> enemyHashMap;

    private HashMap<Location, UpgradeItem> itemHashMap;

    private int level = 1;

    private ClassType type;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initChessBroad();
        initSelectPane();
    }

    private void initRole(){
        switch (type){
            case MAGE:
                this.userRole = UserRole.getRole(new MageBehavior());
                this.userRole.addAbility(new MageAbilityA("寒冰箭"));
                this.userRole.addAbility(new MageAbilityB("暴风雪"));
                break;
            case ROGUE:
                this.userRole = UserRole.getRole(new RogueBehavior());
                this.userRole.addAbility(new RogueAbilityA("背刺"));
                this.userRole.addAbility(new RogueAbilityB("刺骨"));
                break;
            case WARRIOR:
                this.userRole = UserRole.getRole(new WarriorBehavior());
                this.userRole.addAbility(new WarriorAbilityA("冲锋"));
                this.userRole.addAbility(new WarriorAbilityB("斩杀"));
                break;
            default:
                this.userRole = UserRole.getRole(new PriestBehavior());
                this.userRole.addAbility(new PriestAbilityA("圣光术"));
                this.userRole.addAbility(new PriestAbilityB("惩击"));
        }
        WeaponFactory weaponFactory = new NormalWeaponFactory(this.level);
        this.userRole.addWeapon(weaponFactory.createWeapon(WeaponType.ATTACK));
        this.userRole.addWeapon(weaponFactory.createWeapon(WeaponType.ABILITY));
        this.userRole.setWeapon(this.userRole.getWeapons().get(0));
    }

    private void initChessBroad(){
        this.chessBroad = new ChessBroad();
        this.chessBroad.addStone(30);

        List<Location> enemyLocations = this.chessBroad.addEnemy(10);
        this.enemyHashMap = new HashMap<>();
        EnemyBuilder enemyBuilder = new NormalEnemyBuilder(this.level);
        for (Location location : enemyLocations) {
            Enemy enemy = enemyBuilder.createEnemy();
            enemyHashMap.put(location, enemy);
        }

        Random random = new Random();
        UpgradeItemFactory upgradeItemFactory = new RoleUpItemFactory(this.level);
        List<Location> itemLocations = this.chessBroad.addItem(50);
        this.itemHashMap = new HashMap<>();
        for (Location itemLocation : itemLocations) {
            itemHashMap.put(itemLocation, upgradeItemFactory.createUpgradeItem(UpgradeItemType.getByInt(random.nextInt(6))));
        }
    }

    private void initSelectPane(){
        this.chessPane.setGridLinesVisible(true);
        int count = 0;
        for (int i = 0; i < 2; i++) {
            this.chessPane.getRowConstraints().get(i).setMaxHeight(450);
            for (int j = 0; j < 2; j++) {
                ImageView imageView = null;
                try {
                    final int classNum = count;
                    imageView = new ImageView(new Image(new FileInputStream(ChessBroadUtil.IMAGE_PATH_PREFIX + "/" +ChessBroadUtil.CLASS_IMAGES[count])));
                    imageView.setOnMouseClicked(event -> {
                        initChessBroadPane();
                        type = ClassType.getByInt(classNum);
                        initRole();
                        initBaseInfoPane();
                        initWeaponPane();
                        initAbilityPane();
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                VBox vBox = new VBox();
                vBox.getChildren().addAll(imageView, new Label(ChessBroadUtil.ROLE_DESCRIPTION[count]));
                vBox.setAlignment(Pos.CENTER);
                this.chessPane.add(vBox, i, j);
                this.chessPane.getColumnConstraints().get(j).setMaxWidth(450);
                count++;
            }
        }
    }


    private void initBaseInfoPane(){
        Tab tab = this.infoPane.getTabs().get(0);
        tab.setText(InfoPaneUtil.INFO_PANE_NAMES[0]);
        tab.setOnSelectionChanged(event -> initBaseInfoPane());
        GridPane gridPane1 = (GridPane) tab.getContent();
        gridPane1.getChildren().clear();
        Label[] labels = {
                new Label("血量:" + userRole.getLp() + "/" + userRole.getLpCeiling()),
                new Label("基础攻击:" + userRole.getBaseAttack()),
                new Label("经验:" + userRole.getExp() + "/" + RoleUtil.EXP_PER_LEVEL),
                new Label("等级:" + userRole.getLevel()),
                new Label("技能强度:" + FormatUtil.DECIMAL_FORMAT.format(userRole.getAbilityPower() * 100) + "%"),
                new Label("武器攻击力:" + userRole.getWeaponAttack())
        };
        for (Label label : labels) {
            label.setMinHeight(155);
            label.setMinWidth(200);
            label.setAlignment(Pos.CENTER);
        }
        gridPane1.add(labels[0], 0, 0);
        gridPane1.add(labels[1], 0, 1);
        gridPane1.add(labels[2], 1, 0);
        gridPane1.add(labels[3], 1, 1);
        gridPane1.add(labels[4], 0, 2);
        gridPane1.add(labels[5], 1, 2);
    }

    private void initAbilityPane(){
        Tab tab = this.infoPane.getTabs().get(2);
        tab.setText(InfoPaneUtil.INFO_PANE_NAMES[2]);
        tab.setOnSelectionChanged(event -> initAbilityPane());
        ScrollPane scroll = (ScrollPane) tab.getContent();
        VBox content = (VBox) scroll.getContent();
        scroll.vvalueProperty().bind(content.heightProperty());
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        List<VBox> vBoxes = new Vector<>();
        for (Ability ability : this.userRole.getAbilities()) {
            VBox vBox = new VBox();
            Label nameLabel = new Label(ability.getName());
            Label detailLabel = new Label("damage:" + ability.getDamage() + ";heal:" + ability.getHeal());
            vBox.getChildren().addAll(nameLabel, detailLabel);
            vBoxes.add(vBox);
        }
        content.getChildren().clear();
        content.getChildren().addAll(vBoxes);
    }

    private void initWeaponPane(){
        Tab tab = this.infoPane.getTabs().get(1);
        tab.setText(InfoPaneUtil.INFO_PANE_NAMES[1]);
        tab.setOnSelectionChanged(event -> initWeaponPane());
        ScrollPane scroll = (ScrollPane) tab.getContent();
        VBox content = (VBox) scroll.getContent();
        scroll.vvalueProperty().bind(content.heightProperty());
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        List<HBox> hBoxes = new Vector<>();
        for (Weapon weapon : this.userRole.getWeapons()) {
            VBox vBox = new VBox();
            ImageView weaponImage = null;
            try {
                weaponImage = new ImageView(new Image(new FileInputStream(ChessBroadUtil.getImageURL(
                        ChessBroadUtil.WEAPON, weapon.getType().ordinal()
                ))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            vBox.getChildren().add(new Label(weapon.getName()));
            for (String s : weapon.getEffect()) {
                vBox.getChildren().add(new Label(s));
            }

            Button button;
            if(weapon.equals(this.userRole.getWeapon())){
                button = new Button("已装备");
                button.setDisable(true);
            }else {
                button = new Button("装  备");
                button.setOnAction(event -> {
                    userRole.setWeapon(weapon);
                    initWeaponPane();
                });
            }
            Button dropButton = new Button("丢弃");
            dropButton.setOnAction(event -> {
                userRole.dropWeapon(weapon);
                initWeaponPane();
            });
            if(userRole.getWeapons().size() < 2){
                dropButton.setDisable(true);
            }
            HBox hBox = new HBox();
            hBox.getChildren().addAll(weaponImage, vBox, button, dropButton);
            hBoxes.add(hBox);
        }
        content.getChildren().clear();
        content.getChildren().addAll(hBoxes);
    }

    private void initChessBroadPane(){
        this.chessPane.getChildren().clear();
        this.chessPane.setGridLinesVisible(false);
        this.chessPane.addEventHandler(EventType.ROOT, event -> chessPane.requestFocus());
        ImageView[][] imageViews = new ImageView[ChessBroadUtil.DEFAULT_SIZE][ChessBroadUtil.DEFAULT_SIZE];
        for (int i = 0; i < ChessBroadUtil.DEFAULT_SIZE; i++) {
            for (int j = 0; j < ChessBroadUtil.DEFAULT_SIZE; j++) {
                imageViews[i][j] = new ImageView();
                imageViews[i][j].setFitHeight(75);
                imageViews[i][j].setFitWidth(75);
                try {
                    int content = chessBroad.getContent(i, j);
                    if(content != ChessBroadUtil.ITEM){
                        imageViews[i][j].setImage(new Image(new FileInputStream(ChessBroadUtil.getImageURL(content, -1))));
                    }else{
                        int subClass = this.itemHashMap.get(new Location(i, j)).getType().ordinal();
                        imageViews[i][j].setImage(new Image(new FileInputStream(ChessBroadUtil.getImageURL(content, subClass))));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                this.chessPane.add(imageViews[i][j], i, j);
            }
        }
    }

    public void moveRole(KeyEvent event){
        int roleX = chessBroad.getRoleX();
        int roleY = chessBroad.getRoleY();
        int targetX = roleX;
        int targetY = roleY;
        switch (event.getCode()){
            case A:
                targetX = roleX - 1;
                break;
            case W:
                targetY = roleY - 1;
                break;
            case S:
                targetY = roleY + 1;
                break;
            case D:
                targetX = roleX + 1;
                break;
        }
        int moveResult = chessBroad.moveRole(targetX, targetY);
        if(moveResult == ChessBroadUtil.ROAD){
            try {
                this.chessPane.add(new ImageView(new Image(new FileInputStream(ChessBroadUtil.getImageURL(chessBroad.getContent(roleX, roleY), -1)))), roleX,roleY);
                this.chessPane.add(new ImageView(new Image(new FileInputStream(ChessBroadUtil.getImageURL(chessBroad.getContent(targetX, targetY), -1)))), targetX,targetY);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if(moveResult == ChessBroadUtil.ENEMY){
            this.enemyLocation = new Location(targetX, targetY);
            this.battle = new Battle(userRole, enemyHashMap.get(this.enemyLocation));
            this.initBattlePane(battle.getEnemy());
            this.initControlPane();
            this.chessPane.setDisable(true);
        }else if(moveResult == ChessBroadUtil.ITEM){
            try {
                this.chessPane.add(new ImageView(new Image(new FileInputStream(ChessBroadUtil.getImageURL(chessBroad.getContent(targetX, targetY), -1)))), targetX,targetY);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Location location = new Location(targetX, targetY);
            this.addMessage(this.itemHashMap.get(location).takeEffect(userRole));
            this.itemHashMap.remove(location);
            this.initBaseInfoPane();
            this.initWeaponPane();
        }else if(moveResult == ChessBroadUtil.NEXT){
            this.level += 1;
            initChessBroad();
            initChessBroadPane();
            clearMessage();
            addMessage("进入下一关");
        }
    }

    private void restart(){
        this.userRole.init();
        initRole();
        initChessBroad();
        initChessBroadPane();
        initBaseInfoPane();
        initWeaponPane();
        initAbilityPane();
        clearMessage();
        addMessage("已重新开始");
        this.chessPane.setDisable(false);
        this.controlPane.getChildren().clear();
        this.battlePane.getChildren().clear();
    }

    private void addMessage(String message){
        VBox vBox = (VBox) this.itemLog.getContent();
        vBox.getChildren().add(new Label(message));
        this.itemLog.vvalueProperty().bind(vBox.heightProperty());
    }

    private void clearMessage(){
        VBox vBox = (VBox) this.itemLog.getContent();
        vBox.getChildren().clear();
    }

    private void initControlPane(){
        Button attack = new Button("攻击");
        attack.setOnAction(event -> {
            battle.addCommand(new AttackCommand(userRole, battle.getEnemy()));
            dealBattle();
        });
        this.controlPane.getChildren().add(attack);

        List<Button> abilityButtons = new Vector<>();
        for (Ability ability : this.userRole.getAbilities()) {
            Button button = new Button(ability.getName());
            button.setOnAction(event -> {
                battle.addCommand(new AbilityCommand(userRole, battle.getEnemy(), ability));
                dealBattle();
            });
            abilityButtons.add(button);
        }
        this.controlPane.getChildren().addAll(abilityButtons);
    }

    private void dealBattle(){
        this.initBaseInfoPane();
        if(this.userRole.getLp() == 0){
            this.restart();
            return;
        }
        if(this.battle.isEnd() && this.userRole.getLp() != 0){
            this.chessPane.setDisable(false);
            this.controlPane.getChildren().clear();
            this.battlePane.getChildren().clear();
            this.chessBroad.deleteEnemy(this.enemyLocation);
            try {
                this.chessPane.add(new ImageView(new Image(new FileInputStream(ChessBroadUtil.getImageURL(chessBroad.getContent(this.enemyLocation.getX(), this.enemyLocation.getY()), -1)))), this.enemyLocation.getX(),this.enemyLocation.getY());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            this.userRole.addExp(this.battle.getEnemy().getExp());
            for (Weapon weapon : this.battle.getEnemy().getWeapons()) {
                this.userRole.addWeapon(weapon);
            }
            this.addMessage("--战斗结束--");
            for (UpgradeItem item : this.battle.getEnemy().getItems()) {
                this.addMessage(item.takeEffect(userRole));
            }
            this.addMessage("-------------");
            this.enemyHashMap.remove(this.enemyLocation);
            this.enemyLocation = null;
            this.initBaseInfoPane();
            this.initWeaponPane();
            this.initAbilityPane();
        }else{
            this.initBattlePane(battle.getEnemy());
        }
    }



    private void initBattlePane(Enemy enemy){
        this.battlePane.getChildren().clear();

        HBox roleBox = new HBox();
        ProgressBar roleLp = new ProgressBar();
        Label roleLpTextLabel = new Label("剩余血量:");
        roleLp.setProgress(((double) this.userRole.getLp())/this.userRole.getLpCeiling());
        Label roleLpLabel = new Label(this.userRole.getLp() + "/" + this.userRole.getLpCeiling());
        roleBox.getChildren().addAll(roleLpTextLabel, roleLp, roleLpLabel);

        HBox enemyBox = new HBox();
        ProgressBar enemyLp = new ProgressBar();
        Label enemyLpTextLabel = new Label("敌人血量:");
        enemyLp.setProgress(((double) enemy.getLp())/enemy.getLpCeiling());
        Label enemyLpLabel = new Label(enemy.getLp() + "/" + enemy.getLpCeiling());
        enemyBox.getChildren().addAll(enemyLpTextLabel, enemyLp, enemyLpLabel);

        this.battlePane.getChildren().addAll(roleBox, enemyBox);
    }
}
