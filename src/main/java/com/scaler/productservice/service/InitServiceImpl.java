package com.scaler.productservice.service;

import com.scaler.productservice.demo.Author;
import com.scaler.productservice.demo.AuthorRepository;
import com.scaler.productservice.demo.Book;
import com.scaler.productservice.model.Category;
import com.scaler.productservice.model.Order;
import com.scaler.productservice.model.Price;
import com.scaler.productservice.model.Product;
import com.scaler.productservice.repo.CategoryRepository;
import com.scaler.productservice.repo.OrderRepository;
import com.scaler.productservice.repo.PriceRepository;
import com.scaler.productservice.repo.ProductRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InitServiceImpl implements InitService {

    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;

    private final AuthorRepository authorRepository;

    public InitServiceImpl(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            PriceRepository priceRepository,
            CategoryRepository categoryRepository,
            AuthorRepository authorRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.priceRepository = priceRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    @Override
    public void initiallise() {

        Category electronics = new Category();
        electronics.setCategoryName("Electronics");

        electronics = categoryRepository.save(electronics); // save and update

        Price priceIPhone = new Price();
        priceIPhone.setCurrency("INR");
        priceIPhone.setAmount(100000);
        priceIPhone.setDiscount(0);

        Price priceMackBook = new Price();
        priceMackBook.setCurrency("INR");
        priceMackBook.setAmount(200000);
        priceMackBook.setDiscount(0);

        Price priceWatch = new Price();
        priceWatch.setCurrency("INR");
        priceWatch.setAmount(300000);
        priceWatch.setDiscount(0);

        Price pricePS5 = new Price();
        pricePS5.setCurrency("INR");
        pricePS5.setAmount(500000);
        pricePS5.setDiscount(0);

        priceIPhone = priceRepository.save(priceIPhone);
        priceMackBook = priceRepository.save(priceMackBook);
        priceWatch = priceRepository.save(priceWatch);
        pricePS5 = priceRepository.save(pricePS5);

        Product iphone = new Product();
        iphone.setTitle("Iphone 15 PRo");
        iphone.setDescription("fifteen pro");
        iphone.setImage("https://someImageURL");
        iphone.setPrice(priceIPhone);
        iphone.setCategory(electronics);
        iphone.setInventoryCount(707);
        iphone = productRepository.save(iphone);

        Product mackbook = new Product();
        mackbook.setTitle("Mackbook 16  PRo");
        mackbook.setDescription("BEst mackbook");
        mackbook.setImage("https://someImageURL");
        mackbook.setPrice(priceMackBook);
        mackbook.setCategory(electronics);
        mackbook.setInventoryCount(21);
        mackbook = productRepository.save(mackbook);

        Product watch = new Product();
        watch.setTitle("watch Series 10");
        watch.setDescription("BEst watch ever");
        watch.setImage("https://someImageURL");
        watch.setPrice(priceWatch);
        watch.setCategory(electronics);
        watch.setInventoryCount(55);
        watch = productRepository.save(watch);

        Product ps5 = new Product();
        ps5.setTitle("PlayStation5");
        ps5.setDescription("Best Play Station");
        ps5.setImage("https://someImageURL");
        ps5.setPrice(pricePS5);
        ps5.setInventoryCount(13);
        ps5.setCategory(electronics);
        ps5 = productRepository.save(ps5);

        Order order = new Order();
        order.setProducts(List.of(iphone, mackbook, watch));
        order = orderRepository.save(order);

        Author author = new Author("Ashok kumar", null);
        Book book1 = new Book("Book1", author);
        Book book2 = new Book("Book2", author);
        Book book3 = new Book("Book3", author);

        author.setBooks(List.of(book1, book2, book3));
        authorRepository.save(
                author); /// cascade all -> If we save author , all dependent object should also get saved

        Author savedAuthor = authorRepository.findById(1).get();

        // List<Book> books= bookRepo.findByAuthor_Id(savedAuthor.getId();
        // savedAuthor.setBooks(books);
        // System.out.println(savedAuthor.getName());
        List<Book> books = savedAuthor.getBooks();
        System.out.println(books);
        // bookRepo.findByAuthor_Id/(int authorId);
    }
}
