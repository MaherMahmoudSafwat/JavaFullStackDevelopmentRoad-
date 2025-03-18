package com.example.buttons;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    private TargetDataLine line;
    private AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private File audioFile = new File("recorded_audio.wav");

    @Override
    public void start(Stage primaryStage) {
        Text statusText = new Text("Click to Start Recording");

        Button recordButton = new Button("Record");
        recordButton.setOnAction(event -> {
            if (recordButton.getText().equals("Record")) {
                recordButton.setText("Stop");
                startRecording();
                statusText.setText("Recording...");
            } else {
                recordButton.setText("Record");
                stopRecording();
                statusText.setText("Recording Stopped");
                playAudio();  // Play the recorded audio after stopping the recording
            }
        });

        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(statusText, recordButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Audio Recorder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Start recording
    private void startRecording() {
        try {
            AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
            line = AudioSystem.getTargetDataLine(format);
            line.open(format);
            line.start();

            // Create a new thread to record audio
            new Thread(() -> {
                try {
                    AudioInputStream audioStream = new AudioInputStream(line);
                    AudioSystem.write(audioStream, fileType, audioFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Stop recording
    private void stopRecording() {
        if (line != null) {
            line.stop();
            line.close();
        }
    }

    // Play the recorded audio
    private void playAudio() {
        try {
            // Load the audio file into a Clip
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Play the audio
            clip.start();

            // Optionally, loop the audio (set to 0 for no loop)
            // clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
