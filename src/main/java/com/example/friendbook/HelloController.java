package com.example.friendbook;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HelloController {
    @FXML
    private TextField newName;
    @FXML
    private TextField newNumber;
    @FXML
    private TextField newEmail;
    @FXML
    private Button addFriend;
    @FXML
    private Button loadFriends;
    @FXML
    private ListView<Friend> friendList;
    @FXML
    private Label currentName;
    @FXML
    private Label currentNumber;
    @FXML
    private Label currentEmail;

    @FXML
    public void newFriend() throws IOException {
        Friend friend = new Friend(newName.getText(), newNumber.getText(), newEmail.getText());
        friendList.getItems().add(friend);
        try {
            FileWriter writer = new FileWriter("Friends", true);
            writer.write(friend.getName() + "\n");
            writer.write(friend.getPhoneNumber() + "\n");
            writer.write(friend.getEmail() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newName.clear();
        newNumber.clear();
        newEmail.clear();
    }

    @FXML
    public void showFriends() {
        try {
            friendList.getItems().clear();
            BufferedReader reader = new BufferedReader(new FileReader("Friends"));
            String line = reader.readLine();
            while (line != null) {
                String temp1 = line;
                line = reader.readLine();
                String temp2 = line;
                line = reader.readLine();
                String temp3 = line;
                Friend friend = new Friend(temp1, temp2, temp3);
                friendList.getItems().add(friend);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void displayFriend() {
        Friend temp;
        temp = (Friend) friendList.getSelectionModel().getSelectedItem();
        currentName.setText(temp.getName());
        currentNumber.setText(String.valueOf(temp.getPhoneNumber()));
        currentEmail.setText(String.valueOf(temp.getEmail()));
    }
}