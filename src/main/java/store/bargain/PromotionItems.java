package store.bargain;

/**
 * Created by KASW on 2017-07-24.
 */
public class PromotionItems {

    private String itemX;
    private String itemY;

    public PromotionItems(String itemX, String itemY) {
        this.itemX = itemX;
        this.itemY = itemY;
    }

    public String getItemX() {
        return itemX;
    }

    public String getItemY() {
        return itemY;
    }

    @Override
    public String toString() {
        return "PromotionItems{" +
                "itemX='" + itemX + '\'' +
                ", itemY='" + itemY + '\'' +
                '}';
    }
}
