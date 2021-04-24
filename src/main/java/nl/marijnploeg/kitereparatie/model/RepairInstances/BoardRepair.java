package nl.marijnploeg.kitereparatie.model.RepairInstances;

import nl.marijnploeg.kitereparatie.model.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "BOARD")
public class BoardRepair extends Repair {

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    public BoardRepair() {
    }

    public BoardRepair(BoardType boardType) {
        this.boardType = boardType;
    }

//    Constructor
    public BoardRepair(long repairID, Customer customerID, List<Employee> employeeList, DeliveryOption deliveryOption, boolean expressRepair, boolean cleanProduct, String productImage, Brand brand, String model, int size, int buildYear, Date dateCreated, String repairNote, BoardType boardType) {
        super(repairID, customerID, employeeList, deliveryOption, expressRepair, cleanProduct, productImage, brand, model, size, buildYear, dateCreated, repairNote);
        this.boardType = boardType;
    }

//    Getter and setter
    public BoardType getBoardType() {
        return boardType;
    }
    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }
}
