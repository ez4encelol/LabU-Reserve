package model;

public interface SensorObserver {
	public void update(Sensor sensor, String updateMessage);
}