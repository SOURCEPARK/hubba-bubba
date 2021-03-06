/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.services.slot;

import com.opencsv.CSVReader;
import de.sourcepark.hubbabubba.Config;
import de.sourcepark.hubbabubba.services.authorization.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Slots {
    private static final transient Logger LOG = LoggerFactory.getLogger(Slots.class);

    private static Map<String, Slot> slots;

    private static boolean initialized = false;

    private static void initialize() throws IOException {
        CSVReader slotReader = new CSVReader(new FileReader(Config.slotInfoFilePath), ';', '"');
        slots = new HashMap<>();

        String orderNo;
        String prize;
        boolean btcAllowed;
        String btcReceiverAddress;

        String[] split;
        while ((split = slotReader.readNext()) != null) {
            orderNo = split[0].toUpperCase();
            prize = split[1];
            btcAllowed = Boolean.parseBoolean(split[2]);
            btcReceiverAddress = split[3];

            Slot slot = new Slot(orderNo, prize, btcAllowed, btcReceiverAddress);
            slots.put(slot.getSlotNo(), slot);
        }
    }

    public static Slot get(String slotNo) throws IOException, SlotNotFoundException {
        if (!initialized) {
            Slots.initialize();
            initialized = true;
        }

        Slot slot = slots.getOrDefault(slotNo, null);

        if (slot == null)
            throw new SlotNotFoundException("Slot " + slotNo + " does not exist");
        else
            return slot;
    }
}
