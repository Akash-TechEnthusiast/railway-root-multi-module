package com.india.railway.repository.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.india.railway.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {
	// Need to add all the required methods here
	List<Book> findByCategory(String category);

	Book findByBookId(long bookId);
}
