package com.ternovsky.model;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 05.05.13
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */
public class Message {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yy hh:mm:ss");

    private final SimpleStringProperty date = new SimpleStringProperty("");
    private final SimpleStringProperty messageType = new SimpleStringProperty("");
    private final SimpleStringProperty fileName = new SimpleStringProperty("");

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(DATE_FORMAT.format(date));
    }


    public SimpleStringProperty messageTypeProperty() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType.set(messageType.toString());
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }
}
