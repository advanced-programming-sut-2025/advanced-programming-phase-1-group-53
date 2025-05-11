package Models;

import java.util.ArrayList;

public class Store extends Building {
    ArrayList<Product> products;

    public Store(Position position, Position doorPosition) {
        super(position, doorPosition);
    }

    //put some clone methods to clone any product in the store(it is kind of hardcoded)
}
