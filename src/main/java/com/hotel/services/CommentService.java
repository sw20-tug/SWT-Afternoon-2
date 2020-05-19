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
