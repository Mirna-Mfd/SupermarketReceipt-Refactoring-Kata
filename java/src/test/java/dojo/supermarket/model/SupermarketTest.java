package dojo.supermarket.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupermarketTest {

    // Todo: test all kinds of discounts are applied properly

    @Test
    public void toRename() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 0.99);
        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 1.99);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, toothbrush, 10.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2.5);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(4.975, receipt.getTotalPrice(), 0.01);
        assertEquals(Collections.emptyList(), receipt.getDiscounts());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(1.99, receiptItem.getPrice());
        assertEquals(2.5*1.99, receiptItem.getTotalPrice());
        assertEquals(2.5, receiptItem.getQuantity());

    }

    @Test
    public void noDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 1.);

        Teller teller = new Teller(catalog);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(3., receipt.getTotalPrice(), 0.01);
        assertTrue(receipt.getDiscounts().isEmpty());

        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(toothbrush, receiptItem.getProduct());
        assertEquals(1., receiptItem.getPrice(), 0.01);
        assertEquals(3.*1., receiptItem.getTotalPrice(), 0.01);
        assertEquals(3, receiptItem.getQuantity(), 0.01);
    }

    @Test
    public void tenPercentDiscount() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product apples = new Product("apples", ProductUnit.KILO);
        catalog.addProduct(apples, 2.);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, apples, 10.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 2);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(3.6, receipt.getTotalPrice(), 0.01);
        assertEquals(1, receipt.getDiscounts().size());

        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(apples, receiptItem.getProduct());
        assertEquals(2., receiptItem.getPrice(), 0.01);
        assertEquals(2.*2, receiptItem.getTotalPrice(), 0.01);
        assertEquals(2, receiptItem.getQuantity(), 0.01);

    }

    @Test
    public void threeForTwo() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 1.);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.THREE_FOR_TWO, toothbrush, 0.);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 3);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(2., receipt.getTotalPrice(), 0.01);
        assertEquals(1, receipt.getDiscounts().size());

        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(toothbrush, receiptItem.getProduct());
        assertEquals(1., receiptItem.getPrice(), 0.01);
        assertEquals(3.*1., receiptItem.getTotalPrice(), 0.01);
        assertEquals(3, receiptItem.getQuantity(), 0.01);
    }

    @Test
    public void twoForAmount() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 1.);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TWO_FOR_AMOUNT, toothbrush, 1.6);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 2);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(1.6, receipt.getTotalPrice(), 0.01);
        assertEquals(1, receipt.getDiscounts().size());

        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(toothbrush, receiptItem.getProduct());
        assertEquals(1., receiptItem.getPrice(), 0.01);
        assertEquals(2.*1., receiptItem.getTotalPrice(), 0.01);
        assertEquals(2., receiptItem.getQuantity(), 0.01);
    }

    @Test
    public void fiveForAmount() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 1.);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.FIVE_FOR_AMOUNT, toothbrush, 4.);

        ShoppingCart cart = new ShoppingCart();
        for (int i = 0; i < 6; i++) {
            cart.addItem(toothbrush);
        }

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(5., receipt.getTotalPrice(), 0.01);
        assertEquals(1, receipt.getDiscounts().size());

        assertEquals(6, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(toothbrush, receiptItem.getProduct());
        assertEquals(1., receiptItem.getPrice(), 0.01);
        assertEquals(1.*1., receiptItem.getTotalPrice(), 0.01);
        assertEquals(1., receiptItem.getQuantity(), 0.01);
    }

    @Test
    public void twoDifferentItemsInCart() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 1.);
        Product apples = new Product("apples", ProductUnit.KILO);

        catalog.addProduct(apples, 2);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TEN_PERCENT_DISCOUNT, toothbrush, 10.0);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 1.);
        cart.addItemQuantity(apples, 1.5);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT

        assertEquals(ProductUnit.KILO, apples.getUnit());
        assertEquals(ProductUnit.EACH, toothbrush.getUnit());

        assertEquals(3.9, receipt.getTotalPrice(), 0.01);
        assertEquals(1, receipt.getDiscounts().size());
        assertEquals(2, receipt.getItems().size());
        ReceiptItem firstItem = receipt.getItems().get(0);
        assertEquals(toothbrush, firstItem.getProduct());

        ReceiptItem secondItem = receipt.getItems().get(1);
        assertEquals(apples, secondItem.getProduct());
    }

    @Test
    public void quantityAsDecimal() {
        SupermarketCatalog catalog = new FakeCatalog();
        Product apples = new Product("apples", ProductUnit.KILO);

        catalog.addProduct(apples, 1);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.THREE_FOR_TWO, apples, 2);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(apples, 3.5);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT

        assertEquals(ProductUnit.KILO, apples.getUnit());

        assertEquals(2, receipt.getTotalPrice(), 0.01);
        assertEquals(1, receipt.getDiscounts().size());
        assertEquals(1, receipt.getItems().size());
        ReceiptItem firstItem = receipt.getItems().get(0);
        assertEquals(apples, firstItem.getProduct());

    }

    @Test
    public void twoForOne() {
        SupermarketCatalog catalog = new FakeCatalog();

        Product toothbrush = new Product("toothbrush", ProductUnit.EACH);
        catalog.addProduct(toothbrush, 1.);

        Teller teller = new Teller(catalog);
        teller.addSpecialOffer(SpecialOfferType.TWO_FOR_ONE, toothbrush, 0.);

        ShoppingCart cart = new ShoppingCart();
        cart.addItemQuantity(toothbrush, 4);

        // ACT
        Receipt receipt = teller.checksOutArticlesFrom(cart);

        // ASSERT
        assertEquals(2., receipt.getTotalPrice(), 0.01);
        assertEquals(1, receipt.getDiscounts().size());

        assertEquals(1, receipt.getItems().size());
        ReceiptItem receiptItem = receipt.getItems().get(0);
        assertEquals(toothbrush, receiptItem.getProduct());
        assertEquals(1., receiptItem.getPrice(), 0.01);
        assertEquals(4.*1., receiptItem.getTotalPrice(), 0.01);
        assertEquals(4, receiptItem.getQuantity(), 0.01);
    }


}
