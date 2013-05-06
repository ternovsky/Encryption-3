package com.ternovsky.model;

/**
 * Created with IntelliJ IDEA.
 * User: ternovsky
 * Date: 05.05.13
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public enum MessageType {

    MODIFY("Изменение"),
    CREATE("Создание"),
    DELETE("Удаление");

    private String message;

    private MessageType(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
