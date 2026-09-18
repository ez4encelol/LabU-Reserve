package model.systemFacades;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import model.Equipment;
import model.enums.EquipmentStatus;
import model.repository.EquipmentRepository;

/**
 * Facade for equipment management operations (add, enable, disable, mark maintenance).
 * Backed by Spring Data JPA {@link EquipmentRepository}.
 */
@Component
public class EquipmentManagementFacade {

    private final EquipmentRepository equipmentRepository;

    public EquipmentManagementFacade(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    /** Delegate to Spring for singleton-style access from GUI classes. */
    public static EquipmentManagementFacade getInstance() {
        return model.SpringContext.getBean(EquipmentManagementFacade.class);
    }

    @Transactional
    public void addEquipment(String name, String description, String labLocation) {
        Equipment e = new Equipment(0, name, description, labLocation);
        equipmentRepository.save(e);
    }

    @Transactional
    public void enableEquipment(Equipment equipment) {
        equipment.setStatus(EquipmentStatus.ENABLED);
        equipmentRepository.save(equipment);
    }

    @Transactional
    public void disableEquipment(Equipment equipment) {
        equipment.setStatus(EquipmentStatus.DISABLED);
        equipmentRepository.save(equipment);
    }

    @Transactional
    public void markAsUnavailable(Equipment equipment) {
        equipment.setStatus(EquipmentStatus.MAINTENANCE);
        equipmentRepository.save(equipment);
    }

    /** Returns a list of all equipment for the GUI component to display. */
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }
}
