package com.example.droneTech.services;

import com.example.droneTech.RequestsAndResponses.DroneRegistrationRequest;
import com.example.droneTech.RequestsAndResponses.GetMedicationRequest;
import com.example.droneTech.RequestsAndResponses.LoadDroneRequest;
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
        /* Create a Serial Number for the drone */
        String serialNumber = drone.setSerialNumber();
        try {
            //check if the drone already exists.
           Drone d = checkDroneExists(serialNumber);
           if (d != null)
            {
            System.out.println("The Drone with Serial Number: " +serialNumber + "already exists.");
            }
            us = new Drone();
            us.setSerialNumber(serialNumber);
            us.setDroneModel(drone.getDroneModel());
            us.setDroneWeight(drone.getDroneWeight());
            us.setBatteryCapacity(Constants.BATTERY_CAPACITY);
            us.setDroneState(DroneState.IDLE);

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
            System.out.println("The medication for" + code + " is:" + weight + "g");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return weight;
    }
    // get drone state
    public DroneState getSelectedDroneState(String serialNumber)
    {
        DroneState droneState = DroneState.IDLE;
        try
        {
         droneState = eventLogRepository.getCurrentDroneState(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        // query the Event log for the drone state.

        return droneState;
    }

    //get battery level
    public int getBatteryLevel(String serialNumber)
    {
        int batteryLevel = 0;
        try
        {
            batteryLevel = eventLogRepository.getDroneBatteryLevel(serialNumber);
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
            availableDrones = eventLogRepository.getAllAvailableDrones();
        }
       catch(Exception e)
       {
           e.printStackTrace();
       }
        return availableDrones;
    }

    public List<LoadDrone> loadDrone(LoadDroneRequest loadDrone)
    {
        List<LoadDrone> loaded = new ArrayList<>();
        int droneWeight,totalMedicationWeight = 0;
        LoadDrone ld,ld1,ld2,ld3 = null;
        DroneState checkDroneState = getSelectedDroneState(loadDrone.getSerialNumber());
       //System.out.println("Drone state is " + checkDroneState);
        if(checkDroneState == DroneState.IDLE) {
            try
            {
                // Check drone battery level
                int batteryLevel = getBatteryLevel(loadDrone.getSerialNumber());
                //get drone weight from drones table
                droneWeight = droneRepository.getDroneWeight(loadDrone.getSerialNumber());
                   for (int i = 0; i < loadDrone.getMedicineCode().size(); i++) {
                        int medicationWeight = getMedicationWeight(loadDrone.getMedicineCode().get(i));
                        totalMedicationWeight += medicationWeight;
                        // check battery level
                        if (batteryLevel < 25) {
                            System.out.println("Drone battery level is  too low for this operation.");
                        }
                        else if(totalMedicationWeight > droneWeight)
                        {
                            System.out.println("Drone cannot be loaded because the content is too heavy.");
                        }
                        else {
                            ld = new LoadDrone();
                            ld.setSerialNumber(loadDrone.getSerialNumber());
                            ld.setMedicineCode(loadDrone.getMedicineCode().get(i));
                            ld.setDroneState(DroneState.LOADING);
                            ld.setDateCreated(new Date());
                            ld.setDateModified(new Date());

                            //save drone load
                            ld1 = loadDroneRepository.save(ld);

                            if(ld1.getId() > 0)
                            {
                                // change state to loaded
                                ld2 = new LoadDrone();
                                ld2.setSerialNumber(loadDrone.getSerialNumber());
                                ld2.setMedicineCode(loadDrone.getMedicineCode().get(i));
                                ld2.setDroneState(DroneState.LOADED);
                                ld2.setDateCreated(new Date());
                                ld2.setDateModified(new Date());

                                ld3 = loadDroneRepository.save(ld2);
                            }

                            //load the medication onto drone
                            loaded.add(ld);
                            //Set drone state to loading
                            EventLog eLoading = new EventLog();
                            eLoading.setSerialNumber(loadDrone.getSerialNumber());
                            eLoading.setDroneState(DroneState.LOADING);
                            eLoading.setBatteryLevel(recalculateBatteryLevel(batteryLevel, i + 1));
                            eLoading.setDateCreated(new Date());
                            eLoading.setDateModified((new Date()));

                            eventLogRepository.save(eLoading);
                        }
                    }
                // if saved successfully, recalculate  drone battery level and save in event log.
                if (ld3.getId() > 0) {
                    int medicineCount = loadDrone.getMedicineCode().size();
                    int newBatteryLevel = recalculateBatteryLevel(batteryLevel, medicineCount);

                    /*  assign a new Drone state for the battery level*/

                    EventLog el = new EventLog();
                    el.setSerialNumber(loadDrone.getSerialNumber());
                    el.setDroneState(DroneState.LOADED);
                    el.setBatteryLevel(newBatteryLevel);
                    el.setDateCreated(new Date());
                    el.setDateModified((new Date()));

                    EventLog e = eventLogRepository.save(el);
                    System.out.println("New Audit record logged(Load Drone) " + e.getId());
                    // set delivery, delivered and returning logs
                    EventLog eDelivering = new EventLog(loadDrone.getSerialNumber(), DroneState.DELIVERING, newBatteryLevel,
                            new Date(), new Date());
                    eventLogRepository.save(eDelivering);

                    EventLog eDelivered = new EventLog(loadDrone.getSerialNumber(), DroneState.DELIVERED,
                            newBatteryLevel, new Date(), new Date());
                    eventLogRepository.save(eDelivered);

                    EventLog eReturning = new EventLog(loadDrone.getSerialNumber(), DroneState.RETURNING,
                            newBatteryLevel, new Date(), new Date());
                    eventLogRepository.save(eReturning);
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Drone with Serial Number: "+loadDrone.getSerialNumber() + "is currently engaged. Please try " +
                    "again later.");
        }
        return loaded;
    }

    public List<String> getLoadedMedication(GetMedicationRequest getMedicationRequest)
    {
        List<String> loadedMedication = new ArrayList<>();
        try
        {
           loadedMedication = loadDroneRepository.getDroneAvailableMedication(getMedicationRequest.getSerialNumber());
           // if it's not null, run a for loop to retrieve the named list of all the medications loaded, and add to
            // arraylist of type Medication.
        }
        catch(Exception e)
        {
            e.printStackTrace();
       }
        return loadedMedication;
    }

    public Medication registerMedication(MedicationRegistrationRequest medication)
    {
        //call method to save medication object into database and return to the client
        Medication m,me = null;
        try
        {
            //check if the medication already exists.
             m = medicationRepository.checkIfMedicationExists(medication.getCode());
           if (m != null) // medication already exists
            {
              System.out.println("Medication with code " + medication.getCode() + "already exists");
            }
           //esle, save record in MedicationRegister table and log in EventLog
            me = new Medication();
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
        return me;
    }
    public Drone getDroneDetails(String serialNumber)
    {
        Drone droneDetails = null;
        try
        {
            droneDetails = checkDroneExists(serialNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return droneDetails;
    }
}
