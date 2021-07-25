package dto.Product;

import lombok.Getter;
import lombok.Setter;

public class Product {
    private int idx;
    private String productName;
    private int ordCnt;
    private int totalCnt;

    public int getIdx() {
        return idx;
    }

    public String getProductName() {
        return productName;
    }

    public int getOrdCnt() {
        return ordCnt;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public Product(int idx, String productName, int ordCnt, int totalCnt) {
        this.idx = idx;
        this.productName = productName;
        this.ordCnt = ordCnt;
        this.totalCnt = totalCnt;
    }
}
