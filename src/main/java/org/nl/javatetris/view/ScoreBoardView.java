package org.nl.javatetris.view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.nl.javatetris.controller.ScoreBoardController;

import org.nl.javatetris.model.score.Score;

import java.io.*;
import java.util.Comparator;
import java.util.List;

import org.nl.javatetris.model.settings.Settings;


public class ScoreBoardView implements View {

    private ScoreBoardController scoreBoardController;
    private static Label[] menuItems = new Label[]{
            new Label("Main Menu"),
    };

    public ScoreBoardView(Runnable onBackToMenu) {
        this.scoreBoardController = new ScoreBoardController(onBackToMenu);
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("ScoreBoard");
        title.setFont(new Font(20));
        layout.getChildren().add(title);

        //json파일에서 읽어오기
        List<Score> scoreboard = scoreBoardController.loadScoreboard("scoreboard.dat");

        //내림차순 정렬
        scoreboard.sort(Comparator.comparingInt(Score::getScore).reversed());

        //스코어 10개 내림차순으로 display
        int count = 0;
        for (Score score : scoreboard) {
            if (count >= 10) {
                break;
            }
            Label scoreLabel = new Label(score.getName() + ": " + score.getScore());
            scoreLabel.setFont(new Font(16));
            layout.getChildren().add(scoreLabel);
            count++;
        }

        for (Label menuItem : menuItems) {
            menuItem.setTextFill(Color.WHITE);
            menuItem.setFont(new Font(16));
            layout.getChildren().add(menuItem);
        }

        Scene scene = new Scene(layout, Settings.getInstance().getScreenSizeSettings().getScreenWidth(), Settings.getInstance().getScreenSizeSettings().getScreenHeight());

        // 키 입력에 따른 액션을 처리합니다.
        scene.setOnKeyPressed(e -> {
            scoreBoardController.handleKeyPress(e);
            // 현재 선택된 항목을 기반으로 UI를 업데이트합니다.
            updateMenuItems(scoreBoardController.getSelectedItemIndex());
        });

        // 초기 선택 상태 업데이트
        updateMenuItems(scoreBoardController.getSelectedItemIndex());

        return scene;
    }
    // 선택된 항목에 따라 UI 업데이트
    private void updateMenuItems(int selectedIndex) {
        for (int i = 0; i < menuItems.length; i++) {
            menuItems[i].setTextFill(i == selectedIndex ? Color.RED : Color.WHITE);
        }
    }
}