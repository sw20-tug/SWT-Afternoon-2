package com.hotel.repository;

import com.hotel.model.Comment;
import com.hotel.model.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
  Comment findById(long id);

//  List<Comment> findByHotel_id(long hotel_id);

  @Query("FROM Comment WHERE hotel_id = :hotel_id")
  List <Comment> findCommentsByHotelId(@Param("hotel_id")long hotel_id);
}

