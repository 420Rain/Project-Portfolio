import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomReservationManager {
    private ManageHotelModel MHmodel;
    private ManageHotelView MHview;
    private Room room;

    public RoomReservationManager(ManageHotelModel model, ManageHotelView view, Room room) {
        this.MHmodel = model;
        this.MHview = view;
        this.room = room;
    }

    public void removeOneRsv() {
        this.MHview.setRemoveOneBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MHview.findGuestDisplay();
                MHview.closeRemoveRsvFrame();
                removeGuestRsv();
            }
        });
    }

    public void removeGuestRsv() {
        this.MHview.setGuestBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = MHview.getInputTf();
                int reservationIndex = -1;

                for (int i = 0; i < room.getReservations().size(); i++) {
                    if (name.equals(room.getReservations().get(i).getGuestName())) {
                        reservationIndex = i;
                        break;
                    }
                }

                if (reservationIndex > -1) {
                    MHmodel.removeReservation(room, room.getReservations().get(reservationIndex));
                    MHview.setLogLblText("Successfully Removed " + name + "'s Reservation");
                    MHview.closeGuestFrame();
                } else {
                    MHview.setFeedbackLblText("Guest Name Not Found");
                }
            }
        });
    }

    public void removeAllRsv() {
        this.MHview.setRemoveAllBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < room.getReservations().size(); i++) {
                    MHmodel.removeReservation(room, room.getReservations().get(i));
                }
                MHview.closeRemoveRsvFrame();
                MHview.setLogLblText("Removed All Reservations in " + room.getName());
            }
        });
    }
}
