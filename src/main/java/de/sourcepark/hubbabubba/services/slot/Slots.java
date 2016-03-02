/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sourcepark.hubbabubba.services.slot;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import de.sourcepark.hubbabubba.Config;
import de.sourcepark.hubbabubba.services.authorization.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
        boolean cardAllowed;
        String btcReceiverAddress;
        String itemsRemaining;

        String[] split;
        while ((split = slotReader.readNext()) != null) {
            orderNo = split[0].toUpperCase();
            prize = split[1];
            btcAllowed = Boolean.parseBoolean(split[2]);
            cardAllowed = Boolean.parseBoolean(split[3]);
            btcReceiverAddress = split[4];
            itemsRemaining = split[5];

            Slot slot = new Slot(orderNo, prize, btcAllowed, cardAllowed, btcReceiverAddress, itemsRemaining);
            slots.put(slot.getSlotNo(), slot);
        }
    }

    public static Slot get(String slotNo) throws IOException, SlotNotFoundException, SlotNotFilledException {
        if (!initialized) {
            Slots.initialize();
            initialized = true;
        }

        Slot slot = slots.getOrDefault(slotNo, null);

        if (slot == null)
            throw new SlotNotFoundException("Slot " + slotNo + " does not exist");
        else if (Integer.parseInt(slot.getItemsRemaining()) == 0)
            throw new SlotNotFilledException("Slot " + slotNo + " is not filled");
        else
            return slot;
    }

    protected static void writeAll() throws IOException {
        if (!initialized)
            return;

        CSVWriter writer = new CSVWriter(new FileWriter(Config.slotInfoFilePath), ';', '"');
        for(Slot slot: slots.values()) {
            writer.writeNext(slot.toArray());
        }

        writer.close();
    }
}
