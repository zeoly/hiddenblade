package com.yahacode.iot.protocol.box.dina.data;

import com.yahacode.iot.protocol.box.dina.data.sub.DrivingScene;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DrivingSceneData {

    LocalDateTime timestamp;

    Integer preInterval;

    Integer midInterval;

    Integer preNumber;

    Integer midNumber;

    Integer duration;

    List<DrivingScene> scenes = new LinkedList<>();

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getPreInterval() {
        return preInterval;
    }

    public void setPreInterval(Integer preInterval) {
        this.preInterval = preInterval;
    }

    public Integer getMidInterval() {
        return midInterval;
    }

    public void setMidInterval(Integer midInterval) {
        this.midInterval = midInterval;
    }

    public Integer getPreNumber() {
        return preNumber;
    }

    public void setPreNumber(Integer preNumber) {
        this.preNumber = preNumber;
    }

    public Integer getMidNumber() {
        return midNumber;
    }

    public void setMidNumber(Integer midNumber) {
        this.midNumber = midNumber;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<DrivingScene> getScenes() {
        return scenes;
    }

    public void setScenes(List<DrivingScene> scenes) {
        this.scenes = scenes;
    }
}
