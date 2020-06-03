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

  public void insertNewComment(String comm_text, String user_name, Integer rate, long hotel_id)
  {
    Integer new_id = this.cr.findLastId() + 1;
    this.cr.insertNewComment(new_id, comm_text, user_name, rate, hotel_id);
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
