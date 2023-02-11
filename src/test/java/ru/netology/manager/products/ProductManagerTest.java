package ru.netology.manager.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {
    Repository repo = new Repository();
    ProductManager manager = new ProductManager(repo);
    Product book1 = new Book(1, "Война и мир", 700, "Достоевский");
    Product book2 = new Book(2, "Благославение небожителей", 2000, "Мосян Тусню");
    Product book3 = new Book(3, "Мы вас построим", 1000, "Филлип К. Дик");
    Product phone1 = new Smartphone(4, "Nokia", 10000, "HMD Global");
    Product phone2 = new Smartphone(5, "Honor", 20000, "Huawei");
    Product phone3 = new Smartphone(6, "iPhone", 30000, "Apple");
    Product phone4 = new Smartphone(7, "Honor", 50000, "Huawei");

    @Test
    public void addProductsTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Product[] expected = {book1, book2, book3, phone1, phone2, phone3};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void getZeroProductsTest() {
        Product[] expected = {};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByIdTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        repo.removeById(phone2.getId());

        Product[] expected = {book1, book2, book3, phone1, phone3};
        Product[] actual = repo.getProducts();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByOneTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Product[] expected = {phone2};
        Product[] actual = manager.searchBy("Honor");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByTwoTest() {
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);
        manager.add(phone4);

        Product[] expected = {phone2, phone4};
        Product[] actual = manager.searchBy("Honor");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByWrongTest() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);
        manager.add(phone3);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Samsung");
        Assertions.assertArrayEquals(expected, actual);
    }
}
