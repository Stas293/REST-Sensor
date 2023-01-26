package org.springapp.projectrest.repostitories;

import org.springapp.projectrest.models.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    int countByRaining(boolean b);
}
