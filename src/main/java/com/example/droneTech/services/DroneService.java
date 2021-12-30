package com.example.droneTech.services;

import com.example.droneTech.RequestsAndResponses.DroneRegistrationRequest;
import com.example.droneTech.RequestsAndResponses.MedicationRegistrationRequest;
import com.example.droneTech.models.*;
import com.example.droneTech.repositories.*;
import com.example.droneTech.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DroneService implements IDroneService,IMedicationService{

    @Autowired
    private DroneRepository droneRepository;
    private DroneRegisterRepository droneRegisterRepository;
    private EventLogRepository eventLogRepository;
    private MedicationRepository medicationRepository;
    private LoadDroneRepository loadDroneRepository;

    public DroneService(DroneRepository droneRepository,DroneRegisterRepository droneRegisterRepository,
                        EventLogRepository eventLogRepository,MedicationRepository medicationRepository,
                        LoadDroneRepository loadDroneRepository) {
        this.droneRepository = droneRepository;
        this.droneRegisterRepository =  droneRegisterRepository;
        this.eventLogRepository = eventLogRepository;
        this.medicationRepository = medicationRepository;
        this.loadDroneRepository = loadDroneRepository;
    }

    public Drone registerDrone(DroneRegistrationRequest drone) {
/*To do
 *
 * check if the drone exists
 */
        Drone us,d1= null;
        DroneRegister dr = null;
        try {
            //check if the drone already exists.
           Drone d = checkDroneExists(drone.getSerialNumber());
           if (d != null)
            {
            System.out.println("The Drone with Serial Number: " +drone.getSerialNumber() + "already exists.");
            }
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
                    EventLog ev = new EventLog();
                    ev.setSerialNumber(d1.getSerialNumber());
                    ev.setDroneState(d1.getDroneState());
                    ev.setBatteryLevel(d1.getBatteryCapacity());
                    ev.setDateCreated(new Date());
                    ev.setDateModified(new Date());

                   EventLog ev1 = eventLogRepository.save(ev);
                   System.out.println("New Audit log created.(Drone registered). Log entry: "+ev1.getId());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return d1;
    }

    public Drone checkDroneExists(String serialNumber)
    {
        Drone dd = null;
        try
        {
            dd = droneRepository.checkIfDroneExists(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return dd;
    }
    //get medication weight
    public int getMedicationWeight(String code)
    {
        int weight = 0;
        try
        {
            weight = medicationRepository.medicationWeight(code);
            System.out.println("The medication for" + code + " is:" + weight);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return weight;
    }
    // get drone state
    /*public String getSelectedDroneState(String serialNumber)
    {
        String droneState = "";
        try
        {
           droneState = droneRepository.getCurrentDroneState(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        // query the Event log for the drone state.

        return droneState;
    }*/

    //get battery level
    public int getBatteryLevel(String serialNumber)
    {
        int batteryLevel = 0;
        try
        {
            batteryLevel = droneRepository.getDroneBatteryLevel(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return batteryLevel;
    }

    // recalculate battery level
    public int recalculateBatteryLevel(int currentBatteryLevel, int medicineCount)
    {
        return currentBatteryLevel - medicineCount;
    }

    public List<String> getAvailableDrones()
    {
        List<String> availableDrones = new ArrayList<>();
        try
        {
            availableDrones = droneRepository.getAllAvailableDrones();
        }
       catch(Exception e)
       {
           e.printStackTrace();
       }
        // call method from DronesRepository interface to get available drones from  event log and store in availableDrones;
        //return availableDrones.
       /* try
        {
            Query query = em.createNativeQuery("select s.serialNumber from EventLog s where s.droneState = 'IDLE'");
            List<Object[]> resultList = query.getResultList();
            for (Object[] r : resultList) {
                // add to the availableDrones variable to send back to client
                availableDrones.add((String) r[0]); // check this
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }*/
        return availableDrones;
    }

    public String LoadDrone(String serialNumber,List<LoadDrone> loadDrone)
    {
        int droneWeight,totalMedicationWeight = 0;
        try
        {
            // Check drone battery level
              int batteryLevel = getBatteryLevel(serialNumber);
               droneWeight = Constants.MAX_DRONE_WEIGHT;
              //  List<String> medicationItems = new ArrayList<>();
                //declare an arraylist to hold the number of medications, and check the size of each by getting the
            // data from the database.
            /* implement do-while loop here
                 *get weight of medication
                 *sum the weight up of the medication
                 * Check battery level of drone
                 *check if medicationWeight < droneWeight
                 * if so, load medication onto drone and save record in database.
                 * log battery leve in eventlog
                 *if medicationWeight > droneWeight,  exit the loop
                 *
                 *
             *
             * */
                do {
                    for(int i = 0; i <= loadDrone.size(); i++)
                    {
                       int medicationWeight = getMedicationWeight(loadDrone.get(i).getMedicineCode());
                        totalMedicationWeight += medicationWeight;
                        // check battery level
                        if(batteryLevel < 25)
                        {
                            System.out.println("Drone battery level is  too low for this operation.");
                        }
                        else
                        {
                            LoadDrone ld = new LoadDrone();
                            ld.setSerialNumber(serialNumber);
                            ld.setMedicineCode(loadDrone.get(i).getMedicineCode());
                            ld.setDroneState(loadDrone.get(i).getDroneState());
                            ld.setDateCreated(new Date());
                            ld.setDateModified(new Date());

                            //save drone load
                            LoadDrone ld1 = loadDroneRepository.save(ld);

                           // if saved successfully, recalculate  drone battery level and save in event log.
                            if(ld1.getId() > 0)
                            {
                                int medicineCount = loadDrone.size();
                                int newBatteryLevel =  recalculateBatteryLevel(batteryLevel,medicineCount);

                                EventLog el = new EventLog();
                                el.setSerialNumber(serialNumber);
                                el.setDroneState(ld.getDroneState());
                                el.setBatteryLevel(newBatteryLevel);
                                el.setDateCreated(new Date());
                                el.setDateModified((new Date()));

                                EventLog e = eventLogRepository.save(el);
                                System.out.println("New Audit record logged(Load Drone) "+e.getId());
                            }
                        }
                    }
                }
                while(totalMedicationWeight < droneWeight);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "Drone with Serial Number: "+serialNumber + "has been loaded successfully";
    }

    public List<String> getLoadedMedication(String serialNumber)
    {
        List<String> loadedMedication = new ArrayList<>();
        try
        {
            loadedMedication = droneRepository.getDroneAvailableMedication(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       /* try {
            // call method from DronesRepository interface to get a list of loaded medication from  LoadedDrone table and store in loadedMedication;
            //return loadedMedication.

            Query query = em.createNativeQuery("select m.MedicationId from loadedDrones m where m.serialNumber = :serialNumber").setParameter("serialNumber", serialNumber);
            List<Object[]> resultList = query.getResultList();

            for (Object[] r : resultList) {
                // add to the availableDrones variable to send back to client
                loadedMedication.add((String) r[0]); // check this, might need to deserialize to get individual medication items.
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }*/
        return loadedMedication;
    }

    public Medication registerMedication(MedicationRegistrationRequest medication)
    {
        //call method to save medication object into database and return to the client
        Medication m = null;
        try
        {
            //check if the medication already exists.
             m = medicationRepository.checkIfMedicationExists(medication.getCode());
           if (m != null) // medication already exists
            {
              System.out.println("Medication with code " + medication.getCode() + "already exists");
            }
           //esle, save record in MedicationRegister table and log in EventLog
            Medication me = new Medication();
           me.setName(medication.getName());
           me.setMedicineWeight(medication.getMedicineWeight());
           me.setCode(medication.getCode());
           me.setMedicationImage(medication.getMedicationImage());

           Medication me1 = medicationRepository.save(me);
           if(me1.getId() > 0)
           {
               System.out.println("Medication with code " + medication.getCode() + "has been registered");
           }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return m;
    }
    public List<Drone> getDroneDetails(String serialNumber)
    {
        // call method in DronesRepository to return the drone details from the database
        List<Drone> droneDetails = new ArrayList<Drone>();
        return droneDetails;
    }
}
