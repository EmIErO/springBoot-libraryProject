package com.codecool.library.repository;

import com.codecool.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByTitle();

    @Query (value = "SELECT book.id, book.title, book.author FROM specimen s LEFT JOIN borrowing b ON s.id = b.specimen_id LEFT JOIN book ON s.book_id = book.id WHERE b.id IS NULL  OR ( b.id IS NOT NULL AND b.return_date IS NOT NULL ) GROUP BY s.book_id",
    nativeQuery = true)
    List<Book> findMyQuery();



}
