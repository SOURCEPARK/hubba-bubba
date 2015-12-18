package de.sourcepark.hubbabubba.services.slot;

/**
 * Created by jnaperkowski on 11.12.15.
 */
public class Slot {
    private String slotNo;
    private String prize;
    private boolean btcAllowed;
    private String btcRcvAddress;

    public String getSlotNo() {
        return slotNo;
    }

    public String getPrize() {
        return prize;
    }

    public boolean isBtcAllowed() {
        return btcAllowed;
    }

    public String getBtcRcvAddress() {
        return btcRcvAddress;
    }

    public Slot(String slotNo, String prize, boolean btcAllowed, String btcRcvAddress) {
        this.slotNo = slotNo;
        this.prize = prize;
        this.btcAllowed = btcAllowed;
        this.btcRcvAddress = btcRcvAddress;
    }

    public Slot(String slotNo, String prize, boolean btcAllowed) throws Exception {
        if (btcAllowed)
            throw new Exception("If bitcoin is allowed for slot, receiver address has to be set");

        new Slot(slotNo, prize, btcAllowed, null);
    }
}
