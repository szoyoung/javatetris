package org.nl.javatetris.gameplay;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
public class GamePlayViewTest {

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }

    @Start
    private void start(Stage stage) {
    }

    @Test
    public void GamePlaySceneCreateTest() {
        // Given
        GamePlayView gamePlayView = new GamePlayView(new GameParam(0,0), () -> {}, (v) -> {}, () -> {});
        // When
        Scene scene = gamePlayView.createScene();
        // Then
        // 성공적으로 scene 이 생성되면 테스트 성공
    }
}
