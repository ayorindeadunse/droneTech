package com.example.droneTech.services;

import com.example.droneTech.RequestsAndResponses.DroneRegistrationRequest;
import com.example.droneTech.models.*;
import com.example.droneTech.repositories.DroneRegisterRepository;
import com.example.droneTech.repositories.DroneRepository;
import com.example.droneTech.repositories.EventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DroneService implements IDroneService{

    private EntityManager em;
    @Autowired
    private DroneRepository droneRepository;
    private DroneRegisterRepository droneRegisterRepository;
    private EventLogRepository eventLogRepository;

    public DroneService(DroneRepository droneRepository,DroneRegisterRepository droneRegisterRepository,
                        EventLogRepository eventLogRepository) {
        this.droneRepository = droneRepository;
        this.droneRegisterRepository =  droneRegisterRepository;
        this.eventLogRepository = eventLogRepository;
    }

    public Drone registerDrone(DroneRegistrationRequest drone) {
/****To do
 *
 * check if the drone exists
 */
        Drone d,us,d1= null;
        DroneRegister dr = null;
        try {
            //check if the drone already exists.
            // d = droneRepository.checkDroneExists(drone.getSerialNumber());
         /*   if (d != null) // user already exists
            {
              //  LOGGER.info("This Drone is already registered " + user.getEmail());
               // throw droneNotFoundException (create it as well)
            }*/
            us = new Drone();
            us.setSerialNumber(drone.getSerialNumber());
            us.setDroneModel(drone.getDroneModel());
            us.setDroneWeight(drone.getDroneWeight());
            us.setBatteryCapacity(drone.getBatteryCapacity());
            us.setDroneState(drone.getState());

            d1 = droneRepository.save(us);
            if ( d1.getSerialNumber() != null) {

                /* save in droneregister table */
                dr = new DroneRegister();
                dr.setSerialNumber(d1.getSerialNumber());
                dr.setCreatedDate(new Date());
                dr.setModifiedDate(new Date());

                DroneRegister dr1 = droneRegisterRepository.save(dr);

                if (dr1.getId() > 0) {
                    System.out.println("Drone successfully registered");

                    //log state in event log
                    EventLog ev = new EventLog(d1.getSerialNumber(),d1.getDroneState()
                            ,d1.getBatteryCapacity(),new Date(),new Date());
                    eventLogRepository.save(ev);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return d1;
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
        List<String> availableDrones = new ArrayList<String>();
        // call method from DronesRepository interface to get available drones from  event log and store in availableDrones;
        //return availableDrones.
        try {
            /** keep tabs to see if this query will run */
            Query query = em.createNativeQuery("select serialNumber s from EventLog s where s.droneState = 'IDLE'");
            List<Object[]> resultList = query.getResultList();
           // List<String> availableDrones = new ArrayList<String>();
            for (Object[] r : resultList) {
                // add to the availableDrones variable to send back to client
                availableDrones.add((String) r[0]); // check this
            }
          //  return availableDrones;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return availableDrones;
    }

    public String LoadDrone(LoadDrone loadDrone)
    {
        return "LOADED";
    }

    public List<String> getLoadedMedication(String serialNumber)
    {
        List<String> loadedMedication = new ArrayList<String>();
        try {
            //  Query query = em.createNativeQuery("select serialNumber s from EventLog s where s.serialNumber = :serialNumber");
            //  List<String> loadedMedication = query.getResultList();
            // call method from DronesRepository interface to get a list of loaded medication from  LoadedDrone table and store in loadedMedication;
            //return loadedMedication.

            Query query = em.createNativeQuery("select MedicationId m from loadedDrones s where s.serialNumber :serialNumber").setParameter("serialNumber", serialNumber);

            //  List<String> availableDrones = query.getResultList();
            List<Object[]> resultList = query.getResultList();

            for (Object[] r : resultList) {
                // add to the availableDrones variable to send back to client
                loadedMedication.add((String) r[0]); // check this, might need to deserialize to get individual medication items.
            }
           // return loadedMedication;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
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
