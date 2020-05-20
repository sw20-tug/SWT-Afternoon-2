package com.hotel.services;

import com.hotel.repository.CommentRepository;
import com.hotel.model.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

  @Autowired
  CommentRepository cr;

  public void insertNewComment(String comment, String user_name, Integer rating, long hotel_id)
  {
    //generate new id
    Integer new_id = this.cr.findLastId() + 1;
    System.out.println("new_id is " + new_id);
    this.cr.insertNewComment(new_id, comment, user_name, rating, hotel_id);
  }

  public CommentService(CommentRepository cr)
  {
    this.cr = cr;
  }

  public Comment getCommentById(long id) {
    return this.cr.findById(id);
  }

  public List<Comment> getCommentsByHotelID(long id)
  {
    return this.cr.findCommentsByHotelId(id);
  }
//  public List<Comment> getCommentByHotelId(long id) {
//    return this.cr.findByHotel_id(id);
//  }
}
