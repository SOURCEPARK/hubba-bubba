package de.sourcepark.hubbabubba.services.slot;

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

    public boolean isBtcAllowed() {
        return btcAllowed;
    }

    public boolean isCardAllowed() {
        return cardAllowed;
    }

    public String getBtcRcvAddress() {
        return btcRcvAddress;
    }

    public String getItemsRemaining() {
        return itemsRemaining;
    }

    public Slot(String slotNo, String prize, boolean btcAllowed, boolean cardAllowed, String btcRcvAddress, String itemsRemaining) {
        this.slotNo = slotNo;
        this.prize = prize;
        this.btcAllowed = btcAllowed;
        this.cardAllowed = cardAllowed;
        this.btcRcvAddress = btcRcvAddress;
        this.itemsRemaining = itemsRemaining;
    }
}
