package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.model.Book;
import org.springframework.stereotype.Repository;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	public List<Book> findByPetId(Integer petId);
	
	//Sin probar
	//public List<Book> findByRoomIdAndCheckinBetweenOrCheckoutBetween(Integer roomId, LocalDate checkIn, LocalDate checkOut, LocalDate checkInBis, LocalDate checkOutBis);
	public List<Book> findByRoomIdAndCheckinBetween(Integer roomId, LocalDate checkIn, LocalDate checkOut);
	public List<Book> findByRoomIdAndCheckoutBetween(Integer roomId, LocalDate checkIn, LocalDate checkOut);
	public List<Book> findByRoomIdAndCheckinBeforeAndCheckoutAfter(Integer roomId, LocalDate checkIn, LocalDate checkOut);
}
