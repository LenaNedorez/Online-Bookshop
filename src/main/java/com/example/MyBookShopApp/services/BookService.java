package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.BookBrowsing;
import com.example.MyBookShopApp.data.google.api.books.Item;
import com.example.MyBookShopApp.data.google.api.books.Root;
import com.example.MyBookShopApp.errs.BookstoreApiWrongParameterException;
import com.example.MyBookShopApp.repositories.BookBrowsingRepository;
import com.example.MyBookShopApp.repositories.BookRepository;
import com.example.MyBookShopApp.security.BookstoreUser;
import com.example.MyBookShopApp.security.BookstoreUserRegister;
import io.swagger.v3.oas.models.headers.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookstoreUserRegister bookstoreUserRegister;
    private final BookBrowsingRepository bookBrowsingRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public BookService(BookRepository bookRepository, BookstoreUserRegister bookstoreUserRegister, BookBrowsingRepository bookBrowsingRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.bookstoreUserRegister = bookstoreUserRegister;
        this.bookBrowsingRepository = bookBrowsingRepository;
        this.restTemplate = restTemplate;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    //NEW BOOK SERVICE METHODS

    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    public Book getBookById(Integer id){
        return bookRepository.findBookById(id);
    }

    public List<Book> getBooksByTitle(String title) throws BookstoreApiWrongParameterException {
        if (title.equals("") || title.length() <= 1) {
            throw new BookstoreApiWrongParameterException("Wrong values passed to one or more parameters");
        } else {
            List<Book> data = bookRepository.findBooksByTitleContaining(title);
            if (data.size() > 0) {
                return data;
            } else {
                throw new BookstoreApiWrongParameterException("No data found with specified parameters...");
            }
        }
    }

    public List<Book> getBooksWithPriceBetween(Integer min, Integer max) {
        return bookRepository.findBooksByPriceOldBetween(min, max);
    }

    public List<Book> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestsellers() {
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageofRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }

    @Value("${google.books.api.key}")
    private String apiKey;

    public List<Book> getPageOfGoogleBooksApiSearchResult(String searchWord, Integer offset, Integer limit) {
        String REQUEST_URL = "https://www.googleapis.com/books/v1/volumes" +
                "?q=" + searchWord +
                "&key=" + apiKey +
                "&filter=paid-ebooks" +
                "&startIndex=" + offset +
                "&maxResult=" + limit;

        Root root =restTemplate.getForEntity(REQUEST_URL,Root.class).getBody();
        ArrayList<Book> list = new ArrayList<>();
        if(root != null){
            for (Item item:root.getItems()){
                Book book = new Book();
                if(item.getVolumeInfo()!=null){
                    book.setAuthor(new Author(item.getVolumeInfo().getAuthors()));
                    book.setTitle(item.getVolumeInfo().getTitle());
                    book.setImage(item.getVolumeInfo().getImageLinks().getThumbnail());
                }
                if(item.getSaleInfo()!=null){
                    book.setPrice(item.getSaleInfo().getRetailPrice().getAmount());
                    Double oldPrice = item.getSaleInfo().getListPrice().getAmount();
                    book.setPriceOld(oldPrice.intValue());
                }
                list.add(book);
            }
        }
        return list;
    }

    public List<Book> getBooksWithPubDateBetween(Date firstDate, Date secondDate){
        return bookRepository.findBooksByPubDateBetween(firstDate,secondDate);
    }

    public Page<Book> getPageofPopularBooks(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return bookBrowsingRepository.getPopularBooks(pageable);
    }

    public Page<Book> getRecentlyViewedBooksPage(BookstoreUser bookstoreUser, Integer offset, Integer limit) {
        Sort.TypedSort<BookBrowsing> bookBrowsingSort = Sort.sort(BookBrowsing.class);
        Sort sort = bookBrowsingSort.by(BookBrowsing::getId).descending();
        Pageable pageable = PageRequest.of(offset, limit, sort);
        List<BookBrowsing> recentBookBrowsings = bookBrowsingRepository.findFirst10ByBookstoreUser(bookstoreUser, pageable);
        return new PageImpl<Book>(recentBookBrowsings.stream().map(BookBrowsing::getBook).collect(Collectors.toList()));
    }

    public Page<Book> getRecentBooks(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return bookRepository.findFirst20ByOrderByPubDateDesc(pageable);
    }
}