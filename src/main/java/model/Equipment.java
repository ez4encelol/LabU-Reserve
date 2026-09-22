package model;

import javax.persistence.*;
import model.enums.EquipmentStatus;

/**
 * JPA entity for equipment inventory items.
 * Each piece of equipment has a {@link Sensor} (runtime-only, not persisted).
 */
@Entity
@Table(name = "equipment")
@Access(AccessType.FIELD)
public class Equipment implements SensorObserver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    @Column(name = "lab_location")
    private String labLocation;

    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

    @Transient
    private Sensor sensor;

    /** JPA no-arg constructor */
    protected Equipment() {}

    public Equipment(int id, String name, String description, String labLocation, EquipmentStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.labLocation = labLocation;
        this.status = status;
    }

    public Equipment(int id, String name, String description, String labLocation) {
        this(id, name, description, labLocation, EquipmentStatus.ENABLED);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabLocation() {
        return this.labLocation;
    }

    public void setLabLocation(String labLocation) {
        this.labLocation = labLocation;
    }

    public EquipmentStatus getStatus() {
        return this.status;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }

    /** Sensor is created lazily so it works whether the entity is
     *  constructed in code or loaded by JPA reflection. */
    public Sensor getSensor() {
        if (this.sensor == null) {
            this.sensor = new Sensor(this);
        }
        return this.sensor;
    }

    @Override
    public void update(Sensor sensor, String updateMessage) {
        // Equipment does not need to react to sensor events currently
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", id, name, description, labLocation, status.toString());
    }
}
