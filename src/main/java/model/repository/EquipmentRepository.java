package model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
}
