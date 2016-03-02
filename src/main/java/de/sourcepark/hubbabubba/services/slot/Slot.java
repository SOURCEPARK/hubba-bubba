package de.sourcepark.hubbabubba.services.slot;

import java.io.IOException;

/**
 * Created by jnaperkowski on 11.12.15.
 */
public class Slot {
    private String slotNo;
    private String prize;
    private boolean btcAllowed;
    private boolean cardAllowed;
    private String btcRcvAddress;
    private String itemsRemaining;

    public String getSlotNo() {
        return slotNo;
    }

    public String getPrize() {
        return prize;
    }

    public Boolean isBtcAllowed() {
        return btcAllowed;
    }

    public Boolean isCardAllowed() {
        return cardAllowed;
    }

    public String getBtcRcvAddress() {
        return btcRcvAddress;
    }

    public String getItemsRemaining() {
        return itemsRemaining;
    }

    public void decreaseItemsRemaining() throws IOException {
        this.itemsRemaining = ((Integer) (Integer.parseInt(this.itemsRemaining) - 1)).toString();
        Slots.writeAll();
    }

    public Slot(String slotNo, String prize, boolean btcAllowed, boolean cardAllowed, String btcRcvAddress, String itemsRemaining) {
        this.slotNo = slotNo;
        this.prize = prize;
        this.btcAllowed = btcAllowed;
        this.cardAllowed = cardAllowed;
        this.btcRcvAddress = btcRcvAddress;
        this.itemsRemaining = itemsRemaining;
    }

    public String[] toArray() {
        return new String[] {
                getSlotNo(),
                getPrize(),
                isBtcAllowed().toString(),
                isCardAllowed().toString(),
                getBtcRcvAddress(),
                getItemsRemaining()
        };
    }
}
