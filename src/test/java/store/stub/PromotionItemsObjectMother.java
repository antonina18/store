package store.stub;

import store.bargain.PromotionItems;

public class PromotionItemsObjectMother {

    public static PromotionItems promotionItems(String itemX, String itemY, Integer discount){
        PromotionItems promotionItems = new PromotionItems();
        promotionItems.setItemX(itemX);
        promotionItems.setItemY(itemY);
        promotionItems.setDiscount(discount);

        return promotionItems;

    }
}
