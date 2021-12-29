package com.example.droneTech.services;

import com.example.droneTech.models.Drone;
import com.example.droneTech.models.LoadDrone;
import com.example.droneTech.models.Medication;
import com.example.droneTech.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DroneService implements IDroneService{

    private EntityManager em;
    @Autowired
    private DroneRepository droneRepository;

    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public String registerDrone(Drone drone)
    {
        return "IDLE";
    }

    // get drone state
    public String getDroneState(String serialNumber)
    {
        return "Drone state is:";
    }

    //get battery level
    public int getBatteryLevel(String serialNumber)
    {
        return 1;
    }

    public List<String> getAvailableDrones()
    {
       // List<String> availableDrones = new ArrayList<String>();
        // call method from DronesRepository interface to get available drones from  event log and store in availableDrones;
        //return availableDrones.
        Query query = em.createNativeQuery("'select serialNumber s from EventLog s where s.serialNumber = :serialNumber and s.droneState = 'IDLE''");
        List<String> availableDrones = query.getResultList();
        return availableDrones;
    }

    public String LoadDrone(LoadDrone loadDrone)
    {
        return "LOADED";
    }

    public List<String> getLoadedMedication(String serialNumber)
    {
        List<String> loadedMedication = new ArrayList<String>();
     //  Query query = em.createNativeQuery("select serialNumber s from EventLog s where s.serialNumber = :serialNumber");
     //  List<String> loadedMedication = query.getResultList();
        // call method from DronesRepository interface to get a list of loaded medication from  LoadedDrone table and store in loadedMedication;
        //return loadedMedication.
        return loadedMedication;
    }

    public List<Medication> registerMedication(Medication medication)
    {
        //call method to save medication object into database and return to the client
        List<Medication> registeredMedication = new ArrayList<Medication>();
        // call method from DronesRepository interface to save the registeredMedication object into the Medication table;
        //return registeredMedication.
        return registeredMedication;
    }
    public List<Drone> getDroneDetails(String serialNumber)
    {
        // call method in DronesRepository to return the drone details from the database
        List<Drone> droneDetails = new ArrayList<Drone>();
        return droneDetails;
    }
}
