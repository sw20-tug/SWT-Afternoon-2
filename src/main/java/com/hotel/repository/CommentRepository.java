package com.hotel.repository;

import com.hotel.model.Comment;
import com.hotel.model.Hotel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
  Comment findById(long id);

//  List<Comment> findByHotel_id(long hotel_id);

  @Query("FROM Comment WHERE hotel_id = :hotel_id")
  List <Comment> findCommentsByHotelId(@Param("hotel_id")long hotel_id);

  @Query(
    value = "SELECT id FROM swt.comment ORDER BY id DESC LIMIT 1",
    nativeQuery = true)
  Integer findLastId();

  @Transactional
  @Modifying
  @Query(
    value = "INSERT INTO swt.comment(id, comment, user_name, rate, hotel_id)",
    nativeQuery = true)
  void  insertNewComment(@Param("id") long id, @Param("comment") String comment,  @Param("user_name") String user_name, @Param("rate") int rate, @Param("hotel_id") long hotel_id);
}

